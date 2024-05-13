package com.bksgames.game;

import com.badlogic.gdx.Game;
import com.bksgames.game.views.MainMenuScreen;

public class LostInTheMaze extends Game {
	public String gameTitle;

	@Override
	public void create () {
		gameTitle = "Lost in The Maze";

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {}
}
