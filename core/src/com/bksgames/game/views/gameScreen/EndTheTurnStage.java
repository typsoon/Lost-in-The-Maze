package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bksgames.game.LostInTheMaze;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;

public class EndTheTurnStage extends Stage {
	final Skin skin;
	final Table table;

	final LostInTheMaze game;

	public EndTheTurnStage(TextureAtlas atlas, LostInTheMaze game, Viewport viewport, PlayerService playerService) {
		super(viewport);

		this.game = game;
		skin = new Skin(atlas);
		table = new Table(skin);
//		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.addActor(table);

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = new BitmapFont();
		textButtonStyle.up = skin.getDrawable("buttonBackground");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;

		Button endTheTurnButton = new TextButton("End Turn", textButtonStyle);

		table.add().expand();
		table.add(endTheTurnButton).top().padTop(DisplayPropertiesSingleton.getInstance().endTheTurnScreenButtonTopPadding());
		table.add().expand();

		endTheTurnButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				playerService.endTurn();
				game.endTurn();
				return true;
			}
		});
	}
}
