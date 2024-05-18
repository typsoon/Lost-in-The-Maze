package com.bksgames.game.core.main;

import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.main.actionsHandlers.ActionHandler;
import com.bksgames.game.core.main.actionsHandlers.ActionHandlerFactory;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.updates.SimpleLaserUpdate;
import com.bksgames.game.core.updates.SimpleMinionUpdate;
import com.bksgames.game.core.updates.SimpleTileUpdate;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.utils.PlayerEnums;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.Update;
import com.bksgames.game.globalClasses.enums.*;
import com.bksgames.game.services.GameService;

import java.util.*;


public class SimpleGameManager implements GameManager {
    private final Board board;
    private final Map<PlayerColor, Player> players;
    private final Map<MoveTypes, ActionHandler> moveHandlers;
    private final GameService gameService;
    private final Parameters parameters;
    PlayerColor activePlayer;

    @Override
    public boolean makeMove(Move move) {
        Player actPlayer = players.get(activePlayer);
        if (actPlayer.getMinion(move.position()) == null || !getLegalMoves(move.position(), activePlayer).contains(move)) {
            return false;
        }
        moveHandlers.get(move.type()).handle(move);
        return true;
    }

    @Override
    public Collection<Move> getLegalMoves(Point position, PlayerColor color) {
        Minion minion = players.get(color).getMinion(position);
        if (minion == null) {
            return null;
        }
        Collection<Move> legalMoves = new ArrayList<>();
        for(Direction direction : Direction.values()) {
            if(board.getTile(direction.getNext(position)).getTunnel()!=null
                    && board.getTile(direction.getNext(position)).getTunnel().getEntities().isEmpty() ) {
                legalMoves.add(new Move(position.getPosition(),MoveTypes.MOVE, direction));
            }
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

    public boolean sendUpdate(PlayerColor color, Update update) {
        return gameService.ForwardUpdate(color, update);
    }

    public boolean sendUpdates(PlayerColor color, Collection<Update> updates) {
        return gameService.ForwardUpdates(color, updates);
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
    public void setCurrentPlayer(PlayerColor player) {
        activePlayer = player;
    }

    private void playerSetup(PlayerColor color) {
        Player player = players.get(color);
        int i=0;
        for(Direction direction : Direction.values()) {
            if(i>=parameters.minionCount){break;}
            Minion minion = new Minion(direction.getNext(player.getMainNexus()), parameters.minionHitPoints, color);
            player.addMinion(minion);
            board.getTile(direction.getNext(player.getMainNexus())).getTunnel().addEntity(minion);
            i++;
        }
    }

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
            sendUpdate(color, new SimpleTileUpdate(actTile.getDisplayable(), player.isVisible(point), player.getRelativeCoordinates(point)));
            if (actTile.isHollow()) {
                if (actTile.getDisplayable() != Displayable.TUNNEL)
                    sendUpdate(color, new SimpleTileUpdate(Displayable.TUNNEL, player.isVisible(point), player.getRelativeCoordinates(point)));
                for (Entity entity : actTile.getTunnel().getEntities()) {
                    sendUpdate(color, new SimpleTileUpdate(entity.getDisplayable(), player.isVisible(point), player.getRelativeCoordinates(point)));
                }
            }
        }
    }

    @Override
    public void minionUpdate(PlayerColor color, Point minionLocation, Direction direction, MinionEvent minionEvent, MoveTypes minionMove) {
        for (PlayerColor playerColor : players.keySet()) {
            if (players.get(playerColor).isVisible(direction.getNext(minionLocation))) {
                sendUpdate(playerColor,
                        new SimpleMinionUpdate(direction, PlayerEnums.getMinionColor(color), minionEvent, minionMove,
                                players.get(playerColor).getRelativeCoordinates(minionLocation)));
                sendUpdate(playerColor,
                        new SimpleTileUpdate(PlayerEnums.getMinionColor(color), true,
                                players.get(playerColor).getRelativeCoordinates(direction.getNext(minionLocation))));
            }
        }
    }

    @Override
    public void laserUpdate(Direction direction, Direction deflected, Point position) {
        for (PlayerColor playerColor : players.keySet()) {
            if (players.get(playerColor).isVisible(position)) {
                sendUpdate(playerColor, new SimpleLaserUpdate(direction, deflected, players.get(playerColor).getRelativeCoordinates(position)));
            }
        }
    }

    public SimpleGameManager(GameService gameService, Parameters parameters) {
        this.parameters = parameters;
        this.gameService = gameService;
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        moveHandlers = new HashMap<>();
        for (MoveTypes moveType : MoveTypes.values()) {
            moveHandlers.put(moveType, ActionHandlerFactory.CreateActionHandler(moveType, this));
        }
        players = new HashMap<>();
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
        mapTesting();
    }

    private void mapTesting() {
        for (PlayerColor playerColor : players.keySet()) {
            for (int y = 0; y <= parameters.mapSize; y++) {
                for (int x = 0; x <= getParameters().mapSize; x++) {
                    sendUpdate(playerColor,
                            new SimpleTileUpdate(board.getTile(x, y).getDisplayable(), true,
                                    players.get(playerColor).getRelativeCoordinates(new Point(x, y))));
                }
            }
        }
    }
}
