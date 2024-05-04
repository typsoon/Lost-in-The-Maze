package com.bksgames.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bksgames.game.views.MainMenuScreen;

public class LostInTheMaze extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public String gameTitle;

//	public GlyphLayout layout = new GlyphLayout();
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		gameTitle = "Lost in The Maze";
		img = new Texture(Gdx.files.internal("badlogic.jpg"));
//		layout.setText(font, gameTitle, Color.WHITE, Gdx.graphics.getWidth(), Align.center,true);

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
