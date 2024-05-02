package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;
import sun.awt.X11.XSystemTrayPeer;


public class MainMenuScreen implements Screen {

    private TextureAtlas atlas;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;

    final LostInTheMaze game;

    OrthographicCamera gameCamera;

    public MainMenuScreen(final LostInTheMaze game) {
        this.game = game;

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, 800, 480);

    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        atlas = new TextureAtlas(Gdx.files.internal("MainMenu.atlas"));
        skin = new Skin(atlas);
        Table table = new Table(skin);

        Color goldenrod = new Color(218f /255f, 165f /255f, 32f / 255f, 1f);
        Color sunflower = new Color(1f, 215f/ 255f, 0f, 1f);
        Color amber = new Color(255, 191, 0, 1);
        Color mustard = new Color(204, 204, 0, 1);
        Color lemon = new Color(255, 255, 102, 1);

        System.out.println(Color.GOLDENROD);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Harrington_SHAREWARE.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
//        parameter.color.set(sunflower);

        font = generator.generateFont(parameter);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("buttonBackground");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;

        Button buttonPlay = new TextButton("Play", textButtonStyle);
        buttonPlay.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
            }
        });

        Button buttonExit = new TextButton("Exit", textButtonStyle);
        buttonExit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Label.LabelStyle headingStyle = new Label.LabelStyle(font, Color.WHITE);
        Label heading = new Label(game.gameTitle, headingStyle);
        heading.setFontScale(1.5f);
        heading.setAlignment(10);

        table.add(heading);
        table.getCell(heading).spaceBottom(80);
        table.row();
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(40);
        table.row();
        table.add(buttonExit);

        table.debug();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
//        ScreenUtils.clear(255, 255, 255, 1);

        stage.act(delta);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

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
