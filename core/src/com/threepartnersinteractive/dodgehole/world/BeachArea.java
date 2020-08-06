package com.threepartnersinteractive.dodgehole.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.resources.Animation;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class BeachArea extends BaseArea {

    private Animation anim;
    private int current; //the representaiton of the current beach image
    private static int index = 6;
    private static boolean isLeaving;

    public BeachArea(float yPosition) {
        super(yPosition);
        this.anim = new Animation(18000, Images.water_anim, 0);
        this.current = index;

        if(index > 3 && !isLeaving || (index > 0 && isLeaving)){
            index--;
        }
        if(index == 0){
            enteringBeach(); //for the next beaches
        }
    }

    @Override
    public void render(SpriteBatch sb){
        int x = 389;

        sb.draw(Images.street, 0, yPosition);

        for(int i = 0; i < 3; i++){
            sb.draw(anim.getCurrentFrame(), x, yPosition, 57, 57);
            x+=57;
        }

        sb.draw(Images.sand[current], 389, yPosition, Images.sand[current].getRegionWidth(), 57);
    }

    public void update(float dt){
        anim.update(dt);
    }

    public static void enteringBeach(){
        index = 6;
        isLeaving = false;
    }

    public static void leavingBeach(){
        isLeaving = true;
    }
}
