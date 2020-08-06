package com.threepartnersinteractive.dodgehole.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class StreetArea extends BaseArea{

    public StreetArea(float yPosition) { super(yPosition); }

    @Override
    public void render(SpriteBatch sb){
        sb.draw(Images.street, 0, yPosition);
    }
}

