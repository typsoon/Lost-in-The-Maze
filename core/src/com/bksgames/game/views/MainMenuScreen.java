package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;

import javax.swing.event.ChangeListener;

public class MainMenuScreen implements Screen {

    private TextureAtlas atlas;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private Label heading;

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
        atlas = new TextureAtlas("assets/example.atlas");
        skin = new Skin(atlas);
        Table table = new Table(skin);
        font = new BitmapFont();

        font.setColor(255, 255, 255, 1);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("badlogic");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;


        Button buttonPlay = new TextButton("Play", textButtonStyle);
        buttonPlay.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
            }
        });
        buttonPlay.setPosition(300, 400);


        Button buttonExit = new TextButton("Exit", textButtonStyle);
        buttonExit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Label.LabelStyle headingStyle = new Label.LabelStyle(font, Color.WHITE);
        heading = new Label("BIIIIG TITLE", headingStyle);
        heading.setFontScale(4);
        heading.setAlignment(10);

        buttonExit.setPosition(300, 200);

        table.add(heading);
        table.row();
        table.add(buttonExit);
        table.add(buttonPlay);

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

    }
}
