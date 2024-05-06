package com.bksgames.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bksgames.game.views.MainMenuScreen;

public class LostInTheMaze extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public String gameTitle;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		gameTitle = "Lost in The Maze";

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
