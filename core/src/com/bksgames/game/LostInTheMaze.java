package com.bksgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
