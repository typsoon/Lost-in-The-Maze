package com.bksgames.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.services.GameService;
import com.bksgames.game.services.SimpleGameService;
import com.bksgames.game.views.gameScreen.GameScreen;

public class MainMenuScreen extends ScreenAdapter {

    private TextureAtlas atlas;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;

    final LostInTheMaze game;

    OrthographicCamera gameCamera;

    public MainMenuScreen(final LostInTheMaze game) {
        this.game = game;
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

//        Color goldenrod = new Color(218f /255f, 165f /255f, 32f / 255f, 1f);
//        Color sunflower = new Color(1f, 215f/ 255f, 0f, 1f);
//        Color amber = new Color(255, 191, 0, 1);
//        Color mustard = new Color(204, 204, 0, 1);
//        Color lemon = new Color(255, 255, 102, 1);

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

        GameService gameService = new SimpleGameService(new Parameters());

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    displayGameScreen(gameService);
//                    game.startGame();
                }

                return super.keyDown(event, keycode);
            }
        });

        buttonPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                game.startGame();

                displayGameScreen(gameService);
                return true;
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

//        table.debug();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
//        ScreenUtils.clear(255, 255, 255, 0);

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

    private void displayGameScreen(GameService gameService) {
        GameScreen bluePlayerScreen = new GameScreen(game,gameService.connect(PlayerColor.BLUE));
        GameScreen redPlayerScreen = new GameScreen(game, gameService.connect(PlayerColor.RED));
        gameService.startGame();
        game.setScreen(bluePlayerScreen);
    }
}
