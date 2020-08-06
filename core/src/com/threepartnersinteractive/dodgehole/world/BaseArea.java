package com.threepartnersinteractive.dodgehole.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BaseArea {

    float yPosition;

    boolean canStepOn = true;

    /*
     * Constructs the yPosition of the Tile.
     */
    BaseArea(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getYPosition() { return this.yPosition; }
    public void setYPosition(float position) { this.yPosition = position; }
    public void render(SpriteBatch sb) {}
    public void update(float dt) {}
}
