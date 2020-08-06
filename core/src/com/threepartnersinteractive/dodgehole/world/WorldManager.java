package com.threepartnersinteractive.dodgehole.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.DodgeHole;
import com.threepartnersinteractive.dodgehole.MyRandom;
import com.threepartnersinteractive.dodgehole.entities.vehicles.Car;
import com.threepartnersinteractive.dodgehole.entities.Cow;
import com.threepartnersinteractive.dodgehole.entities.EntityBase;
import com.threepartnersinteractive.dodgehole.entities.Hole;
import com.threepartnersinteractive.dodgehole.entities.Score;
import com.threepartnersinteractive.dodgehole.entities.Sign;
import com.threepartnersinteractive.dodgehole.entities.vehicles.Vehicle;
import com.threepartnersinteractive.dodgehole.resources.Images;
import com.threepartnersinteractive.dodgehole.states.GameOverState;
import com.threepartnersinteractive.dodgehole.states.GameStateManager;

import java.util.ArrayList;
import java.util.LinkedList;

public class WorldManager {

    private LinkedList<BaseArea> spawned_areas;
    private ArrayList<EntityBase> spawned_hazards;
    private GameStateManager gsm;

    private Car car;									// How do we find the car coordinates? How do we find the Collisions? This bad boy.

    private static int movement_speed;	// Movement of the tiles going downwards.

    private int dynamic_timer; //timer to spawn a particular dynamic entity
    private int static_timer; //timer to spawn a particular static entity

    private EntityBase latest_spawned_hazard;

    private Score score; //player score
    private Path path; //random generated path for the player
    private Sign sign; //current sign

    public WorldManager(GameStateManager gsm){
        this.gsm = gsm;

        this.car = new Car(DodgeHole.WIDTH/2 - Images.car.getRegionWidth()/2, DodgeHole.HEIGHT/2 - Images.car.getRegionHeight(),
                Images.car.getRegionWidth(), Images.car.getRegionHeight());


        this.spawned_areas = new LinkedList<>();

        this.spawned_hazards = new ArrayList<>();

        this.movement_speed = 300;
        this.path = new Path();

        this.score = new Score();

        for(int i = 0; i<(DodgeHole.HEIGHT/57)+1; i++) {
            spawned_areas.add(new StreetArea((i)*57));
        }
    }

    public void update(float dt){
        dynamic_timer++;
        static_timer++;

        Sign.timer++;

        if(car.getY() > DodgeHole.HEIGHT){ gsm.set(new GameOverState(gsm, score));}

        for (EntityBase hazard : spawned_hazards) { hazard.update(dt); }

        //Moves every spawned area a delta Y position and updates animation, if it haves...
        for (BaseArea area: spawned_areas) {
            area.setYPosition(area.getYPosition() - Math.round(movement_speed * dt));
            area.update(dt);
        }

        if(spawned_areas.getFirst().yPosition <= -57){

            spawned_areas.removeFirst();

            if(sign != null && sign.getTown().getName().equals("Cabo Rojo")){
                if(Sign.timer/60.0 > 9.45) BeachArea.leavingBeach();

                spawned_areas.addLast(new BeachArea(spawned_areas.getLast().yPosition + 57));
            }
            else{
                spawned_areas.addLast(new StreetArea(spawned_areas.getLast().yPosition + 57));
            }



            SpawnHazard(DodgeHole.HEIGHT);
        }

        HazardMovement(dt);

        car.update(dt);
    }

    private void HazardMovement(float dt) {

        for (int i = 0; i < spawned_hazards.size(); i++) {
            EntityBase e = spawned_hazards.get(i);

            // Moves hazard down
            e.setY(e.getY() -  Math.round(movement_speed * dt));

            //if the player passes the hole, update their score
            if(e instanceof Hole && e.getY() < car.getY() && !((Hole) e).underVehicle){
                ((Hole) e).underVehicle = true;
                score.update(dt);
            }


            //If the player hits one of the following, then it is game over
            if ((e instanceof Hole || e instanceof Cow || e instanceof Sign)
                    && e.getCollision() != null && car.getCollision().overlaps(e.getCollision())) {
                gsm.set(new GameOverState(gsm, score));
            }

            if (e instanceof Vehicle) {
                if(e.getCollision() != null && car.getCollision().overlaps(e.getCollision())){
                    if(car.getY() >= e.getY() + e.getHeight()){ car.setCrashed(true); }
                    else if(!car.isCrashed()){  gsm.set(new GameOverState(gsm, score)); }
                }
                e.setY(e.getY() + (dt * 710 - Math.round(WorldManager.getWorldSpeed() * dt)));
            }

            // if hazard is out of the screen bounds, then remove this hazard.
            if (e.getY() > DodgeHole.HEIGHT * 2|| e.getY() < -DodgeHole.HEIGHT
                || e.getX() > DodgeHole.WIDTH || e.getX() < -e.getWidth()) {
                spawned_hazards.remove(i).dispose();
            }
        }
    }

    public void render(SpriteBatch sb){
        for(BaseArea area : spawned_areas){ area.render(sb); }

        ArrayList<Sign>signs = new ArrayList<Sign>();
        ArrayList<Vehicle>vehicles = new ArrayList<>();
        ArrayList<Cow>cows = new ArrayList<>();

        for (EntityBase hazards: spawned_hazards) {
            if(hazards instanceof Sign) signs.add((Sign)hazards);
            else if (hazards instanceof Cow) cows.add((Cow)hazards);
            else if (hazards instanceof Vehicle) vehicles.add((Vehicle)hazards);
            else hazards.render(sb);
        }

        for(Cow cow: cows) cow.render(sb);

        car.render(sb);

        for(Vehicle vehicle: vehicles) vehicle.render(sb);
        for (Sign sign : signs) sign.render(sb);

        score.render(sb);
    }

    /*
     * Given a yPositionm this method will add a new hazard to the SpawnedHazards ArrayList
     */
    private void SpawnHazard(float yPosition) {
        if(Sign.timer >= 600){
            Sign.timer = 0;
            sign = new Sign(224, yPosition, path.moveTown());
            spawned_hazards.add(sign);
        }

        if(sign.getTown().getDynamicHazardTiming() != null &&
                dynamic_timer >= sign.getTown().getDynamicHazardTiming()* 60){

            spawned_hazards.add(sign.getTown().spawnDynamicHazard());
            dynamic_timer = 0;

        }

        if(sign.getTown().getStaticHazardTiming() != null && static_timer >= sign.getTown().getStaticHazardTiming()* 60
        && (latest_spawned_hazard == null || sign.getTown().getStaticHazard().getY() > latest_spawned_hazard.getY() + latest_spawned_hazard.getHeight() - 57)){

            latest_spawned_hazard = sign.getTown().removeStaticHazard();
            spawned_hazards.add(latest_spawned_hazard);
            static_timer = 0;

        }

        int hole_position = 145 + MyRandom.nextInt(181);

        int random_hole = MyRandom.nextInt(10);

        if(random_hole < sign.getTown().getHoleChances()) spawned_hazards.add(new Hole(hole_position, yPosition));

    }

    public static int getWorldSpeed(){ return movement_speed; }
}
