package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;
import com.bksgames.game.common.PlayerColor;

public class EndGameScreen extends ScreenAdapter {
	private TextureAtlas atlas;
	private Stage stage;
	private Skin skin;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;

	private final LostInTheMaze game;
	OrthographicCamera gameCamera;

	final PlayerColor winner;

	public EndGameScreen(LostInTheMaze game, PlayerColor winner) {
		this.game = game;
		this.winner = winner;
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void show() {
		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho(false, 800, 480);

		stage = new Stage();
		atlas = new TextureAtlas(Gdx.files.internal("MainMenu.atlas"));
		skin = new Skin(atlas);
		Table table = new Table(skin);

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Harrington_SHAREWARE.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);

		Texture texture = font.getRegion().getTexture();
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


		Label.LabelStyle headingStyleBlue = new Label.LabelStyle(font, Color.BLUE);
		Label.LabelStyle headingStyleRed = new Label.LabelStyle(font, Color.RED);

		Label.LabelStyle currStyle = (winner==PlayerColor.RED) ? headingStyleRed : headingStyleBlue;

		Label heading = new Label("Winner", currStyle);
		heading.setFontScale(1.5f);
		heading.setAlignment(10);

		table.add(heading);
		table.getCell(heading).spaceBottom(80);
		table.row();
		table.row();

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("buttonBackground");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;

		Button buttonNewGame = new TextButton("New Game", textButtonStyle);
		buttonNewGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.startGame();
				return true;
			}
		});

		Button buttonExit = new TextButton("Exit", textButtonStyle);
		buttonExit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});

		table.add(buttonNewGame).spaceBottom(40);
		table.row();
		table.add(buttonExit).spaceBottom(40);
		table.row();

		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void dispose() {
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		font.dispose();
		generator.dispose();
	}
}
