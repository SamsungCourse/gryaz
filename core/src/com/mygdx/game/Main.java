package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.screens.GameScreen;

public class Main extends Game {//этот клass загружает ассеты при запуске приложения, чтобы потом не тормозить игру, еще он запускается первым

	private AssetManager manager;

	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void create () {

		manager = new AssetManager();
		manager.load("background.jpg", Texture.class);
		manager.load("ball.png", Texture.class);
		manager.load("platform.png", Texture.class);
		manager.finishLoading();

		setScreen(new GameScreen(this));
	}

}
