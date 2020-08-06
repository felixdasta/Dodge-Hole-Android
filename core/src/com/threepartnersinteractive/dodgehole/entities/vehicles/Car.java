package com.threepartnersinteractive.dodgehole.entities.vehicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.threepartnersinteractive.dodgehole.entities.EntityBase;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class Car extends EntityBase {

    private Sprite car_sprite;

    private boolean isLeft = false;
    private boolean isRight = false;

    private boolean got_crashed = false;

    private float crash_roation = 0;

    private final int swtich_speed = 180;
    private Sound car_crash_sound;

    public Car(float x, float y, int width, int height){
        this.car_sprite = new Sprite(Images.car);

        this.width = width;
        this.height = height;

        this.rectangle = new Rectangle(getX() + 5, getY() + 4, this.width - 12, this.height - 8);

        this.car_sprite.setRegionWidth(Images.car.getRegionWidth());
        this.car_sprite.setRegionHeight(Images.car.getRegionHeight());

        this.x = x;
        this.y = y;

        this.car_sprite.setX(x);
        this.car_sprite.setY(y);

        this.car_crash_sound = Gdx.audio.newSound(Gdx.files.internal("Music/Car Braking Skid Sound.mp3"));

    }

    @Override
    public void update(float dt){
        if(((Gdx.input.isTouched() && Gdx.input.getDeltaX() > 0) || Gdx.input.isKeyPressed(Input.Keys.D))
                && this.getX() + this.getWidth() <= 366){

            this.setX(this.getX() +  Math.round(swtich_speed * dt));

            if(!isRight){
                this.car_sprite.rotate(-3);
                isRight = true;
                isLeft = false;
            }

            this.car_sprite.setX(this.getX());

        }

        else if(((Gdx.input.isTouched() && Gdx.input.getDeltaX() < 0) || Gdx.input.isKeyPressed(Input.Keys.A))
                && this.getX() >= 146){

            this.setX(this.getX() - Math.round(swtich_speed * dt));

            if(!isLeft){
                this.car_sprite.rotate(3);
                isLeft = true;
                isRight = false;
            }

            this.car_sprite.setX(this.getX());

        }

        else if(car_sprite.getRotation() != 0){
            this.car_sprite.setRotation(0);
        }

        if(got_crashed){
            this.crash_roation += 5;
            this.car_sprite.setRotation(crash_roation);
            this.setY(this.getY() + Math.round(dt * 355));
            this.car_sprite.setY(this.getY());
        }
    }

    @Override
    public void render(SpriteBatch sb){
        car_sprite.draw(sb);
        rectangle.setX(getX() + 5);
        rectangle.setY(getY() + 4);
    }

    public boolean isCrashed() {
        return got_crashed;
    }

    public void setCrashed(boolean got_crashed) {
        this.got_crashed = got_crashed;
        this.car_crash_sound.play();
    }
}
