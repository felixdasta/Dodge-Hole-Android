package com.threepartnersinteractive.dodgehole.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.threepartnersinteractive.dodgehole.DodgeHole;
import com.threepartnersinteractive.dodgehole.world.WorldManager;

public class GameState extends State {

    private WorldManager world_manager;
    private Viewport viewport;

    public GameState(GameStateManager gsm) {
        super(gsm);
        this.viewport = new ScalingViewport(Scaling.fit, DodgeHole.WIDTH, DodgeHole.HEIGHT, cam);
        this.cam.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        this.world_manager = new WorldManager(gsm);
    }

    @Override
    public void update(float dt) {
        world_manager.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        world_manager.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
