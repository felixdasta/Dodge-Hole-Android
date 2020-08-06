package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.resources.Images;
import com.threepartnersinteractive.dodgehole.world.Town;

public class Sign extends EntityBase {

    public static int timer = 600; //sign timer

    private BitmapFont font; //sign text
    private GlyphLayout layout; //used for sign text alignment
    private Town current_town; //represents the town where the sign belongs

    public Sign(float xPosition, float yPosition, Town town) {
        this.font = new BitmapFont();
        this.setX(xPosition);
        this.setY(yPosition);
        this.width = Images.sign.getWidth();
        this.height = Images.sign.getHeight();
        this.current_town = town;
        this.layout = new GlyphLayout(font, town.getName());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(Images.sign, this.getX(), this.getY());
        sb.setColor(Color.WHITE);
        font.draw(sb, current_town.getName(),(2 * getX() + 3 + 118)/2- (layout.width/2F), getY() + getHeight() - 20);
    }

    @Override
    public void dispose(){
        font.dispose();
    }

    public Town getTown() {
        return current_town;
    }

    }

