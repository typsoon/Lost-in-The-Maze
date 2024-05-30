package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.services.GameService;
import com.bksgames.game.services.SimpleGameService;
import com.bksgames.game.views.gameScreen.EndTheTurnStage;

public class TurnTransitionScreen extends ScreenAdapter {

	private TextureAtlas atlas;
	private Stage stage;
	private Skin skin;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;

	private final LostInTheMaze game;
	OrthographicCamera gameCamera;

	public TurnTransitionScreen(LostInTheMaze game) {
		this.game = game;
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

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("buttonBackground");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;

		Button buttonNext = new TextButton("Next player's turn", textButtonStyle);

		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Input.Keys.ENTER) {
					game.displayGameScreen();
				}

				return super.keyDown(event, keycode);
			}
		});

		buttonNext.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.displayGameScreen();

				return true;
			}
		});

		Label.LabelStyle headingStyle = new Label.LabelStyle(font, Color.WHITE);
		Label heading = new Label("Turn is over", headingStyle);
		heading.setFontScale(1.5f);
		heading.setAlignment(10);

		table.add(heading);
		table.getCell(heading).spaceBottom(80);
		table.row();
		table.add(buttonNext).size(500,100);
		table.getCell(buttonNext).spaceBottom(40);
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
