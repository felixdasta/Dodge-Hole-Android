package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.threepartnersinteractive.dodgehole.MyRandom;
import com.threepartnersinteractive.dodgehole.resources.Animation;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class Cow extends EntityBase {

    private Animation anim;

    public Cow(float xPosition, float yPosition) {
        this.anim = new Animation(15000, Images.cow, MyRandom.nextInt(Images.cow.length));
        this.setWidth(anim.getCurrentFrame().getRegionWidth() * 1.5f);
        this.setHeight(anim.getCurrentFrame().getRegionHeight() * 1.5f);
        this.setX(xPosition);
        this.setY(yPosition);
        this.rectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void update(float dt) {
        anim.update(dt);
        x += 44f * dt;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(anim.getCurrentFrame(),getX(), getY(), getWidth(), getHeight());
        rectangle.setX(getX());
        rectangle.setY(getY());
    }
}
