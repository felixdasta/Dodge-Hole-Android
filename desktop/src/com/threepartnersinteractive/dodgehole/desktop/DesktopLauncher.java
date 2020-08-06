package com.threepartnersinteractive.dodgehole.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.threepartnersinteractive.dodgehole.DodgeHole;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		config.width = (int)(gd.getDisplayMode().getHeight()/2.25);
		config.height = (int)(gd.getDisplayMode().getWidth()/2.25);
		config.title = DodgeHole.TITLE;

		config.resizable = false;

		new LwjglApplication(new DodgeHole(), config);
	}
}
