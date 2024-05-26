package com.bksgames.game.core.main;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.main.actionsHandlers.ActionHandler;
import com.bksgames.game.core.main.actionsHandlers.ActionHandlerFactory;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.updates.SimpleTileUpdate;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.common.utils.*;
import com.bksgames.game.services.GameService;

import java.util.*;

/**
 * Class managing game
 * @author riper
 * @author jajko
 */

public class SimpleGameManager implements GameManager {
    private final Board board;
    private final EnumMap<PlayerColor, Player> players;
    private final EnumMap<ActionToken, ActionHandler> moveHandlers;
    private final GameService gameService;
    private final Parameters parameters;
    private PlayerColor activePlayer;
    private Iterator<PlayerColor> nextPlayer;

    @Override
    public boolean makeMove(Move move) {
        if (!getLegalMoves(move.position()).contains(move)) {
            return false;
        }
        moveHandlers.get(move.type()).handle(move);
        return true;
    }

    @Override
    public <T extends Update> boolean sendUpdate(UpdateHolder<T> updateHolder, PlayerColor playerColor) {
            Update update = updateHolder.encode(players.get(playerColor));

            if(update != null)
                gameService.forwardUpdate(playerColor,update);
        return true;
    }

    //TODO move somewhere
    @Override
    public Collection<Move> getLegalMoves(Point position) {
        Minion minion = players.get(getCurrentPlayer()).getMinion(position);
        Collection<Move> legalMoves = new ArrayList<>();
        if (minion == null) {
            return legalMoves;
        }
        for(Direction direction : Direction.values()) {
            if(board.getTile(direction.getNext(position)).getTunnel()!=null
                    && board.getTile(direction.getNext(position)).getTunnel().getEntities().isEmpty() ) {
                legalMoves.add(new Move(position.getPosition(), ActionToken.MOVE, direction));
            }
        }
        if(board.getTile(position).getTunnel().getMirror()==null) {
            legalMoves.add(new Move(position,ActionToken.MIRROR,Direction.LEFT));
            legalMoves.add(new Move(position,ActionToken.MIRROR,Direction.RIGHT));
        }
        for(Direction direction : Direction.values()){
            legalMoves.add(new Move(position,ActionToken.LASER,direction));
        }
        return legalMoves;
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

    //TODO move to somewhere
    @Override
    public void playerVisionUpdate(PlayerColor color) {
        Player player = players.get(color);
        Set<Point> visible = new HashSet<>(board.getNexusesVision(color));
        for (Minion minion : player.getMinions()) {
            visible.addAll(board.getVisible(minion));
        }
        Set<Point> changes = player.updateVisibleTiles(visible);
        for (Point point : changes) {
            Tile actTile = board.getTile(point);
            gameService.forwardUpdate(color, new SimpleTileUpdate(actTile.getDisplayable(), player.isVisible(point), player.getRelativeCoordinates(point)));
            if (actTile.isHollow()) {
                if (actTile.getDisplayable() != Displayable.TUNNEL)
                    gameService.forwardUpdate(color, new SimpleTileUpdate(Displayable.TUNNEL, player.isVisible(point), player.getRelativeCoordinates(point)));
                for (Entity entity : actTile.getTunnel().getEntities()) {
                    gameService.forwardUpdate(color, new SimpleTileUpdate(entity.getDisplayable(), player.isVisible(point), player.getRelativeCoordinates(point)));
                }
            }
        }
    }


    @Override
    public void endTurn() {
        if(!nextPlayer.hasNext())
            nextPlayer = players.keySet().iterator();
        activePlayer = nextPlayer.next();
        players.get(activePlayer).nextTurn();
    }

    public SimpleGameManager(GameService gameService, Parameters parameters) {
        this.parameters = parameters;
        this.gameService = gameService;
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        moveHandlers = new EnumMap<>(ActionToken.class);
        for (ActionToken moveType : ActionToken.values()) {
            moveHandlers.put(moveType, ActionHandlerFactory.CreateActionHandler(moveType, this));
        }
        players = new EnumMap<>(PlayerColor.class);
        activePlayer = PlayerColor.BLUE;
        players.put(PlayerColor.BLUE,
                new Player(board.getNexus(PlayerColor.BLUE).get(0).getPosition()));
        players.put(PlayerColor.RED,
                new Player(board.getNexus(PlayerColor.RED).get(0).getPosition()));

        for (PlayerColor playerColor : players.keySet()) {
            playerSetup(playerColor);
        }
        for (PlayerColor playerColor : players.keySet()) {
            playerVisionUpdate(playerColor);
        }
        //mapTesting();
    }
    private void playerSetup(PlayerColor color) {
        Player player = players.get(color);
        int i=0;
        for(Direction direction : Direction.values()) {
            if(i>=parameters.minionCount){break;}
            Minion minion = new Minion(direction.getNext(player.getMainNexus()), parameters.minionHitPoints, parameters.actionsNumber, color);
            player.addMinion(minion);
            board.getTile(direction.getNext(player.getMainNexus())).getTunnel().addEntity(minion);
            i++;
        }
    }
    //for debug
    private void mapTesting() {
        for (PlayerColor playerColor : players.keySet()) {
            for (int y = 0; y <= parameters.mapSize; y++) {
                for (int x = 0; x <= getParameters().mapSize; x++) {
                    gameService.forwardUpdate(playerColor,
                            new SimpleTileUpdate(board.getTile(x, y).getDisplayable(), true,
                                    players.get(playerColor).getRelativeCoordinates(new Point(x, y))));
                }
            }
        }
    }

}
