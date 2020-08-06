package com.threepartnersinteractive.dodgehole.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.threepartnersinteractive.dodgehole.DodgeHole;
import com.threepartnersinteractive.dodgehole.entities.Score;
import com.threepartnersinteractive.dodgehole.entities.Sign;
import com.threepartnersinteractive.dodgehole.resources.Images;
import com.threepartnersinteractive.dodgehole.world.BeachArea;

public class GameOverState extends State {

    private Texture background;
    private FreeTypeFontGenerator generator; //used for custom font
    private BitmapFont font;

    private GlyphLayout layout_1; //used to calculate "YOUR SCORE" text width
    private GlyphLayout layout_2; //used to calculate "HIGHEST SCORE" text width

    private String player_score;
    private String highest_score;

    public GameOverState(GameStateManager gsm, Score player_score){
        super(gsm);

        //used to access highest score
        Preferences preferences = Gdx.app.getPreferences("DodgeHole");

        //checks if the player beated the high score
        this.checkScore(player_score, preferences);

        //game over background
        this.background = Images.game_over;

        //generates custom font
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/A25-SQUANOVA.ttf"));

        //used to manipulate custom font
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderWidth = 3;
        parameter.size = 32;


        //passes to the instance variable the result of the modified custom font
        this.font = generator.generateFont(parameter);
        this.font.setColor(Color.LIME);

        this.player_score = "YOUR SCORE: " + player_score.getScore();
        this.highest_score = "HIGHEST SCORE: " + preferences.getInteger("highscore");

        //Do you have an android? Let's upload your highest score on google play!
        if(DodgeHole.essentials != null) {
            DodgeHole.essentials.submitScore(preferences.getInteger("highscore"));
            DodgeHole.essentials.showLeaderboard();
        }

        this.layout_1 = new GlyphLayout(font, this.player_score);
        this.layout_2 = new GlyphLayout(font, this.highest_score);

        Sign.timer = 600;

        BeachArea.enteringBeach(); //resets beaches to a smooth entering transition
    }
    @Override
    public void update(float dt) {
        if(Gdx.input.justTouched()){
            //Do you have an android? Let's show you some ads!
            if(DodgeHole.essentials != null) {
                DodgeHole.essentials.showInterstitialAd();
            }
            gsm.set(new MenuState(gsm));
        }
}

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, DodgeHole.WIDTH, DodgeHole.HEIGHT);
        font.draw(sb, player_score, DodgeHole.WIDTH/2- layout_1.width/2F, DodgeHole.HEIGHT/2);
        font.draw(sb, highest_score, DodgeHole.WIDTH/2- layout_2.width/2F, (DodgeHole.HEIGHT/2)-layout_1.height - 20);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        generator.dispose();
        font.dispose();
    }

    private void checkScore(Score player_score, Preferences preferences){
        if(preferences.getInteger("highscore") < player_score.getScore()){
            preferences.putInteger("highscore", player_score.getScore());
            preferences.flush();
        }
    }
}
