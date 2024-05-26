package com.bksgames.game;

import com.badlogic.gdx.Game;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.core.main.Player;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.services.GameService;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.services.SimpleGameService;
import com.bksgames.game.views.MainMenuScreen;
import com.bksgames.game.views.TurnTransitionScreen;
import com.bksgames.game.views.gameScreen.GameScreen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LostInTheMaze extends Game {
	public String gameTitle;
	private PlayerService activePlayer;
	private final TurnTransitionScreen turnTransitionScreen = new TurnTransitionScreen(this);
	Map<PlayerService,GameScreen> playerScreens = new HashMap<>();


	@Override
	public void create () {
		gameTitle = "Lost in The Maze";

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {}

	public void startGame(){
//		setScreen(turnTransitionScreen);
		GameService gameService = new SimpleGameService(new Parameters());
		PlayerService bluePlayer = gameService.connect(PlayerColor.BLUE);
		PlayerService redPlayer = gameService.connect(PlayerColor.RED);
		activePlayer = bluePlayer;
		playerScreens.put(bluePlayer, new GameScreen(this,bluePlayer));
		playerScreens.put(redPlayer, new GameScreen(this,redPlayer));
		gameService.startGame();
		displayGameScreen();
	}

	public void endTurn(){
		setScreen(turnTransitionScreen);

		Iterator<PlayerService> iter = playerScreens.keySet().iterator();
		if(activePlayer == iter.next()){
			activePlayer = iter.next();
		}
		else activePlayer = playerScreens.keySet().iterator().next();
	}

	public void displayGameScreen(){
		setScreen(playerScreens.get(activePlayer));
	}
}
