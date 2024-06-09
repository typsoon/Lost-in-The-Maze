package com.bksgames.game.core.main;

import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.core.moves.*;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.core.entities.Minion;
//import com.bksgames.game.core.main.actionsHandlers.ActionHandler;
//import com.bksgames.game.core.main.actionsHandlers.ActionHandlerFactory;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.services.GameService;

import java.util.*;

/**
 * Class managing game
 *
 * @author riper
 * @author jajko
 */

public class SimpleGameManager implements GameManager {
    private final Board board;
    private final EnumMap<PlayerColor, Player> players;
//    private final EnumMap<ActionToken, ActionHandler> moveHandlers;
    private final GameService gameService;
    private final Parameters parameters;
    private PlayerColor activePlayer;
    private Iterator<PlayerColor> nextPlayer;
    private final DamageManager damageManager;
    private final VisionManager visionManager;

//    @Override
//    public boolean makeMove(Action acceptMove) {
//        if (!getLegalMoves(acceptMove.position()).contains(acceptMove)) {
//            return false;
//        }
//        players.get(activePlayer).getMinion(acceptMove.position()).makeAction(acceptMove.type());
//        moveHandlers.get(acceptMove.type()).handle(acceptMove);
//        return true;
//    }

    @Override
    public <T extends Update> boolean sendUpdate(UpdateHolder<T> updateHolder, PlayerColor playerColor) {
        Update update = updateHolder.encode(players.get(playerColor));
        if (update != null)
            gameService.forwardUpdate(playerColor, update);
        return true;
    }

    //TODO acceptMove somewhere
    @Override
    public Collection<Action> getLegalMoves(Point position) {
        Minion minion = players.get(getCurrentPlayer()).getMinion(position);
        Collection<Action> legalActions = new ArrayList<>();
        if (minion == null) {
            return legalActions;
        }
        if (minion.canMakeAction(ActionToken.MOVE)) {
            for (Direction direction : Direction.values()) {
                if (board.getTile(direction.getNext(position)).getTunnel() != null
                        && board.getTile(direction.getNext(position)).getTunnel().getEntities().isEmpty()) {
                    legalActions.add(new MoveAction(direction, position.copy(), this));
                }
            }
        }
        if (minion.canMakeAction(ActionToken.MIRROR)) {
            if (board.getTile(position).getTunnel().getMirror() == null) {
                legalActions.add(new MirrorAction(Direction.LEFT, position.copy(), this));
                legalActions.add(new MirrorAction(Direction.RIGHT, position.copy(), this));
            }
        }
        if (minion.canMakeAction(ActionToken.LASER)) {
            for (Direction direction : Direction.values()) {
                legalActions.add(new LaserAction(direction, position.copy(), this));
            }
        }
        if (minion.canMakeAction(ActionToken.SWORD)) {
            for (Direction direction : Direction.values()) {
                legalActions.add(new SwordAction(direction, position.copy(), this));
            }
        }
        return legalActions;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Map<PlayerColor, Player> getPlayers() {
        return players;
    }

    @Override
    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public PlayerColor getCurrentPlayer() {
        return activePlayer;
    }

    @Override
    public DamageManager getDamageManager() {
        return damageManager;
    }

    @Override
    public VisionManager getVisionManager() {
        return visionManager;
    }

    @Override
    public void endTurn() {
        if (!nextPlayer.hasNext())
            nextPlayer = players.keySet().iterator();
        activePlayer = nextPlayer.next();
        players.get(activePlayer).nextTurn();
        damageManager.nextTurn();
    }

    public SimpleGameManager(GameService gameService, Parameters parameters) {
        this.parameters = parameters;
        this.gameService = gameService;
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        this.damageManager = new DamageManager(this);
        this.visionManager = new VisionManager(this);

//        moveHandlers = new EnumMap<>(ActionToken.class);
//        for (ActionToken moveType : ActionToken.values()) {
//            moveHandlers.put(moveType, ActionHandlerFactory.CreateActionHandler(moveType, this));
//        }
        players = new EnumMap<>(PlayerColor.class);

        players.put(PlayerColor.BLUE,
                new Player(board.getNexus(PlayerColor.BLUE).getFirst().getPosition()));
        players.put(PlayerColor.RED,
                new Player(board.getNexus(PlayerColor.RED).getFirst().getPosition()));
        nextPlayer = players.keySet().iterator();
        activePlayer = PlayerColor.BLUE;


        for (PlayerColor playerColor : players.keySet()) {
            playerSetup(playerColor);
        }
        for (PlayerColor playerColor : players.keySet()) {
            visionManager.playerVisionUpdate(playerColor);
        }
        //mapTesting();
    }

    private void playerSetup(PlayerColor color) {
        Player player = players.get(color);
        int i = 0;
        for (Direction direction : Direction.values()) {
            if (i >= parameters.minionCount()) {
                break;
            }
            Minion minion = new Minion(direction.getNext(player.getMainNexus()), parameters.minionHitPoints(), parameters.actionsNumber(), color);
            player.addMinion(minion);
            // damageManager.subscribe(minion);
            board.getTile(direction.getNext(player.getMainNexus())).getTunnel().addEntity(minion);
            i++;
        }
    }

    //for debug
    @SuppressWarnings("unused")
    private void mapTesting() {
        for (PlayerColor playerColor : players.keySet()) {
            for (int y = 0; y <= parameters.mapSize(); y++) {
                for (int x = 0; x <= getParameters().mapSize(); x++) {
//                    TODO: look at this and make it better if possible
                    var point = players.get(playerColor).getRelativeCoordinates(new Point(x,y));
                    gameService.forwardUpdate(playerColor,
                            new TileUpdate(board.getTile(x, y).getDisplayable(), true,
                                    point.x, point.y));
                }
            }
        }
    }

}
