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

public class RulesScreen extends ScreenAdapter {
	private TextureAtlas atlas;
	private Stage stage;
	private Skin skin;
	private BitmapFont font;
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
		Table table = new Table(skin);

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Harrington_SHAREWARE.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;

		font = generator.generateFont(parameter);

		Texture texture = font.getRegion().getTexture();
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		Label.LabelStyle headingStyle = new Label.LabelStyle(font, Color.WHITE);
		Label heading = new Label("Game Rules", headingStyle);
		heading.setFontScale(1.5f);
		heading.setAlignment(10);

		Label.LabelStyle rulesStyle = new Label.LabelStyle(font, Color.WHITE);
		Label rules = new Label("Here are the rules of the game:\n\n1. Rule 1\n2. Rule 2\n3. Rule 3\n\n4. Rule 4\n5. Rule 5\n6. Rule 6\n\n7. Rule 7\n8. Rule 8\n9. Rule 9", rulesStyle);
		rules.setFontScale(0.8f);
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
}
