package com.threepartnersinteractive.dodgehole.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.threepartnersinteractive.dodgehole.DodgeHole;
import com.threepartnersinteractive.dodgehole.resources.Images;

public class MenuState extends State {

    private Texture background;
    private float f = 0.1f; //value to manipulate start button transparency
    private boolean was_touched;
    private FreeTypeFontGenerator generator; //used for custom font
    private BitmapFont font;
    private Viewport viewport; //used to scale the screen
    private GlyphLayout layout; //used to calculate the text width

    public MenuState(GameStateManager gsm) {
        super(gsm);

        Images.init(); //initializes images

        this.viewport = new ScalingViewport(Scaling.fit, DodgeHole.WIDTH, DodgeHole.HEIGHT, cam);
        this.cam.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        this.background = Images.title;

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;

        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/GelPenHeavy.ttf"));
        this.font = generator.generateFont(parameter);
        this.layout = new GlyphLayout(font, "TOUCH ANYWHERE TO START");
    }

    @Override
    public void update(float dt) {
        if(font.getColor().a >= 0.99 || font.getColor().a <= 0.1) f = -f;
        font.getColor().a += f * dt* 6f;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, DodgeHole.WIDTH, DodgeHole.HEIGHT);

        if(!was_touched) {
            if(Gdx.input.justTouched()){
                was_touched = true;

                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.shadowOffsetX = 2;
                parameter.shadowOffsetY = 2;
                parameter.borderGamma = 5f;
                parameter.size = 28;

                this.generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/A25-SQUANOVA.ttf"));
                this.font = generator.generateFont(parameter);
                this.layout.setText(font, "LOADING");

                font.draw(sb, "LOADING", DodgeHole.WIDTH / 2 - layout.width / 2F, DodgeHole.HEIGHT / 2F);

                //Do you have an android? Let's show you some ads!
                if(DodgeHole.essentials != null){
                    DodgeHole.essentials.showBannerAd();
                }
            }
            else{
                font.draw(sb, "TOUCH ANYWHERE TO START", DodgeHole.WIDTH / 2 - layout.width / 2F, 64);
            }
        }
        else {
            gsm.set(new GameState(gsm));
            dispose();
        }

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
        generator.dispose();
    }
}
