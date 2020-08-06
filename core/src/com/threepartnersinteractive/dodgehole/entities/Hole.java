package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.threepartnersinteractive.dodgehole.MyRandom;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class Hole extends EntityBase {

    private Texture hole_texture;
    public boolean underVehicle;

    public Hole(float xPosition, float yPosition) {
        this.hole_texture = Images.street_hole[MyRandom.nextInt(Images.street_hole.length)];
        this.underVehicle = false;

        this.setX(xPosition);
        this.setY(yPosition);

        this.width = this.height =  35 + MyRandom.nextInt(10);
        this.rectangle = new Rectangle(getX() + this.getWidth()/7, getY() + this.getWidth()/5, (int)((this.getWidth()/2)*1.45), (int)((this.getHeight()/2)*1.45));
    }

    @Override
    public void render(SpriteBatch sb){
        sb.draw(hole_texture, x, y, width, height);
        rectangle.setX(getX() + this.getWidth()/7);
        rectangle.setY(getY() + this.getWidth()/5);
    }
}
