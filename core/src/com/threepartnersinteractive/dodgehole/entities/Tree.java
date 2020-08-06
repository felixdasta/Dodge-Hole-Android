package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class Tree extends EntityBase {

    public Tree(int xPosition, int yPosition){
        this.setX(xPosition);
        this.setY(yPosition);
        this.setWidth(Images.tree.getRegionWidth());
        this.setHeight(Images.tree.getRegionHeight());
    }

    @Override
    public void render(SpriteBatch sb){
        sb.draw(Images.tree, getX(), getY());
    }
}
