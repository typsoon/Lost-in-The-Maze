package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RulesScreen extends ScreenAdapter {
	private TextureAtlas atlas;
	private Stage stage;
	private Skin skin;
	private BitmapFont font, font1;
	private FreeTypeFontGenerator generator;
    private final LostInTheMaze game;

	public RulesScreen(final LostInTheMaze game) {
		this.game = game;
	}

	@Override
	public void show() {
        OrthographicCamera gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho(false, 800, 480);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas(Gdx.files.internal("MainMenu.atlas"));
		skin = new Skin(atlas);
		Table table = new Table(skin).padLeft(20).padTop(25);

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Harrington_SHAREWARE.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;

		font = generator.generateFont(parameter);
		font1 = new BitmapFont();

		Texture texture = font.getRegion().getTexture();
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		Label.LabelStyle headingStyle = new Label.LabelStyle(font, Color.WHITE);
		Label heading = new Label("Game Rules", headingStyle);
		heading.setFontScale(1.5f);
		heading.setAlignment(10);

		String rulesText = loadRulesFromFile("rules.txt");

		Label.LabelStyle rulesStyle = new Label.LabelStyle(font1, Color.WHITE);
		Label rules = new Label(rulesText, rulesStyle);
		rules.setFontScale(1.2f);
		rules.setWrap(true);

		ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
		ScrollPane scrollPane = new ScrollPane(rules, scrollPaneStyle);
		scrollPane.setFadeScrollBars(false);
		scrollPane.setScrollbarsVisible(true);

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("buttonBackground");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;

		TextButton backButton = new TextButton("Back", textButtonStyle);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenuScreen(game));
			}
		});

		table.add(heading).expandX().center().spaceBottom(10);
		table.row();
		table.add(scrollPane).expand().fill().spaceBottom(20);
		table.row();
		table.add(backButton).center().padBottom(20);

		table.setFillParent(true);

		stage.addActor(table);
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
	public void dispose() {
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		font.dispose();
		generator.dispose();
	}

	private String loadRulesFromFile(String filePath) {
		StringBuilder rules = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Gdx.files.internal(filePath).read()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				rules.append(line).append("\n");
			}
		} catch (IOException e) {
			Gdx.app.error("RulesScreen", "Error reading rules file", e);
		}
		return rules.toString();
	}
}
