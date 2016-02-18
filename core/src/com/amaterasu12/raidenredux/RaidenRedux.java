package com.amaterasu12.raidenredux;

import com.amaterasu12.raidenredux.Screens.MenuScreen;
import com.amaterasu12.raidenredux.Screens.PlayScreen;
import com.amaterasu12.raidenredux.Systems.RenderingSystem;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RaidenRedux extends Game {
	public static final int W_WIDTH = 480;
	public static final int W_HEIGHT = 800;

	public static Engine engine;

	@Override
	public void create () {
		Manager.create();
		Manager.load();
		Manager.done();

		engine = new Engine();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		Manager.dispose();
		super.dispose();
	}

	@Override
	public void render () {
		super.render();
	}


}
