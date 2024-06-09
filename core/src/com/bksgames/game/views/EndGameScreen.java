package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;

public class EndGameScreen extends ScreenAdapter {
	private TextureAtlas atlas;
	private Stage stage;
	private Skin skin;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;

	private final LostInTheMaze game;
	OrthographicCamera gameCamera;

	private final boolean isWinner; // true - blue, false - red

	public EndGameScreen(LostInTheMaze game, boolean isWinner) {
		this.game = game;
		this.isWinner = isWinner;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		stage.act(delta);
		stage.draw();
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
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas(Gdx.files.internal("MainMenu.atlas"));
		skin = new Skin(atlas);
		Table table = new Table(skin);

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Harrington_SHAREWARE.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);


		Label.LabelStyle headingStyleBlue = new Label.LabelStyle(font, Color.BLUE);
		Label.LabelStyle headingStyleRed = new Label.LabelStyle(font, Color.RED);


		Label.LabelStyle currStyle = isWinner ? headingStyleBlue : headingStyleRed;
		Label heading = new Label("Winner", currStyle);
		heading.setFontScale(1.5f);
		heading.setAlignment(10);

		table.add(heading);
		table.getCell(heading).spaceBottom(80);
		table.row();
		table.row();

		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		stage.addActor(table);
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
