package com.threepartnersinteractive.dodgehole;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.threepartnersinteractive.dodgehole.states.GameStateManager;
import com.threepartnersinteractive.dodgehole.states.MenuState;

public class DodgeHole extends ApplicationAdapter {

	public static final int WIDTH = 513;
	public static final int HEIGHT = 912;

	public static final String TITLE = "Dodge Hole";
	public static GameEssentials essentials;

	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;

	public DodgeHole() {}

	public DodgeHole(GameEssentials essentials){ this.essentials = essentials; }

	@Override
	public void create () {
        Gdx.gl.glClearColor(1, 0, 0, 1);

		this.batch = new SpriteBatch();
		this.gsm = new GameStateManager();
		this.gsm.push(new MenuState(gsm));

		if(essentials != null){
			if(!essentials.isSignedIn()) essentials.onSignInButtonClicked();
			else essentials.signInSilently();
		}

		this.music = Gdx.audio.newMusic(Gdx.files.internal("Music/UTheme.mp3"));
		this.music.setLooping(true);
		this.music.setVolume(0.1f);
		this.music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		music.dispose();
	}
}
