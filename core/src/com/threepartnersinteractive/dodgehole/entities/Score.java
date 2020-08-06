package com.threepartnersinteractive.dodgehole.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.threepartnersinteractive.dodgehole.DodgeHole;

public class Score extends EntityBase {

    private int score;
    private FreeTypeFontGenerator generator;
    private BitmapFont font;

    public Score() {
        this.score = 0;
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/GelPenHeavy.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;

        this.font = generator.generateFont(parameter);
    }

    @Override
    public void update(float dt){
        this.score++;
    }

    @Override
    public void render(SpriteBatch sb){
        font.draw(sb, "Score: " + score, 20, DodgeHole.HEIGHT - 20);
    }

    @Override
    public void dispose(){
        font.dispose();
        generator.dispose();
    }

    public int getScore(){ return score; }

}