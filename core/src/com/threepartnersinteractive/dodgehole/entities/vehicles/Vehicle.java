package com.threepartnersinteractive.dodgehole.entities.vehicles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.threepartnersinteractive.dodgehole.entities.EntityBase;

public class Vehicle extends EntityBase {

    private TextureRegion image;

    public Vehicle(float xPosition, float yPosition, TextureRegion image) {
        this.image = image;
        this.setWidth(image.getRegionWidth());
        this.setHeight(image.getRegionHeight());
        this.setX(xPosition);
        this.setY(yPosition);
        this.rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(SpriteBatch sb){
        sb.draw(image, getX(), getY());
        rectangle.setX(getX());
        rectangle.setY(getY());
    }
}
