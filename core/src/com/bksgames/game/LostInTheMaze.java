package com.bksgames.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.bksgames.game.common.ConfigManagerSingleton;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.services.GameService;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.services.SimpleGameService;
import com.bksgames.game.views.EndGameScreen;
import com.bksgames.game.views.MainMenuScreen;
import com.bksgames.game.views.TurnTransitionScreen;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;
import com.bksgames.game.views.gameScreen.GameScreen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LostInTheMaze extends Game {
	public String gameTitle;
	private PlayerService activePlayer;
	private final TurnTransitionScreen turnTransitionScreen = new TurnTransitionScreen(this);

	final Map<PlayerService,GameScreen> playerScreens = new HashMap<>();
	private Music backgroundMusic;

	@Override
	public void create () {
		gameTitle = "Lost in The Maze";

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/LostInTheMaze.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(DisplayPropertiesSingleton.getInstance().backgroungMusicVolume());
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				backgroundMusic.play();
			}
		}, 5);

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		if (backgroundMusic != null) {
			backgroundMusic.dispose();
		}
	}

	public void startGame(){
//		setScreen(turnTransitionScreen);
		GameService gameService = new SimpleGameService(ConfigManagerSingleton.getInstance());
		PlayerService bluePlayer = gameService.connect(PlayerColor.BLUE);
		PlayerService redPlayer = gameService.connect(PlayerColor.RED);
		activePlayer = bluePlayer;
		playerScreens.put(bluePlayer, new GameScreen(this,bluePlayer));
		playerScreens.put(redPlayer, new GameScreen(this,redPlayer));
		gameService.startGame();
		displayGameScreen();
	}

	public void endTurn() {
		setScreen(turnTransitionScreen);

		Iterator<PlayerService> iter = playerScreens.keySet().iterator();
		if(activePlayer == iter.next()){
			activePlayer = iter.next();
		}
		else activePlayer = playerScreens.keySet().iterator().next();
	}

	public void endGame(PlayerColor winner) {
		setScreen(new EndGameScreen(this, winner));
	}

	public void displayGameScreen(){
		setScreen(playerScreens.get(activePlayer));
	}
}
