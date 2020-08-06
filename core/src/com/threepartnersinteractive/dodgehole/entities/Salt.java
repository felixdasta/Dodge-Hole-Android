package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class Salt extends EntityBase{

        public Salt(int xPosition, int yPosition){
            this.setX(xPosition);
            this.setY(yPosition);
            this.setWidth(Images.salt.getRegionWidth());
            this.setHeight(Images.salt.getRegionHeight());
        }

        @Override
        public void render(SpriteBatch sb){
            sb.draw(Images.salt, getX(), getY());
        }
}
