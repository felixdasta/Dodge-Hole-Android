package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class House extends EntityBase {

    private TextureRegion texture;

    public House(int xPosition, int yPosition, boolean goes_left){
        this.x = xPosition;
        this.y = yPosition;

        if(goes_left) this.texture = Images.left_house;
        else this.texture = Images.right_house;

        this.width = texture.getRegionWidth() * 0.90f;
        this.height = texture.getRegionHeight() * 0.90f;
    }

    @Override
    public void render(SpriteBatch sb){
        sb.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
