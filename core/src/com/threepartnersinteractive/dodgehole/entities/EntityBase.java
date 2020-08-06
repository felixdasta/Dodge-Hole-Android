package com.threepartnersinteractive.dodgehole.entities;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class EntityBase {

    protected float height=64,width=64;
    protected float x,y;
    protected com.badlogic.gdx.math.Rectangle rectangle;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void render(SpriteBatch sb){  }

    public void update(float dt){}

    public Rectangle getCollision() { return rectangle; }

    public void dispose(){}

}
