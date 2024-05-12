package com.bksgames.game.core.main;

import com.bksgames.game.core.utils.Move;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.actionsHandlers.ActionHandler;
import com.bksgames.game.core.actionsHandlers.ActionHandlerFactory;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.tiles.SimpleTileUpdate;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.enums.*;
import com.bksgames.game.services.GameService;
import com.bksgames.game.updateData.Update;

import java.awt.*;
import java.util.*;


public class SimpleGameManager implements GameManager {
    private final Board board;
    private final Map<PlayerColor, Player> players;
    private final Map<MoveTypes, ActionHandler> moveHandlers;
    private final GameService gameService;
    private final Parameters parameters;
    PlayerColor activePlayer;
    @Override
    public Boolean makeMove(Move move) {
        if( players.get(activePlayer).getMinion(move.x(),move.y())==null
        ||
        !getLegalMoves(move.x(), move.y(), activePlayer).contains(move))
        {return false;}
        moveHandlers.get(move.type()).handle(move);
        return true;
    }
    @Override
    public Collection<Move> getLegalMoves(int x, int y, PlayerColor color) {
        Minion minion = players.get(color).getMinion(x, y);
        if(minion==null) {return  null;}
        Collection<Move> legalMoves = new ArrayList<>();
        if(board.getTile(x+1,y).isHollow()) {
            legalMoves.add(new Move(x,x+1,MoveTypes.MOVE,Direction.RIGHT));}
        if(board.getTile(x-1,y).isHollow()) {
            legalMoves.add(new Move(x-1,y,MoveTypes.MOVE,Direction.LEFT));}
        if(board.getTile(x,y+1).isHollow()) {
            legalMoves.add(new Move(x,y+1,MoveTypes.MOVE,Direction.UP));}
        if(board.getTile(x,y-1).isHollow()) {
            legalMoves.add(new Move(x,y-1,MoveTypes.MOVE,Direction.DOWN));}

        if(!minion.onCooldown()) {
            legalMoves.add(new Move(x+1,y,MoveTypes.LASER, Direction.RIGHT));
            legalMoves.add(new Move(x,y-1,MoveTypes.LASER, Direction.DOWN));
            legalMoves.add(new Move(x,y+1,MoveTypes.LASER, Direction.UP));
            legalMoves.add(new Move(x-1,y,MoveTypes.LASER, Direction.LEFT));
        }
        legalMoves.add(new Move(x-1,y,MoveTypes.SWORD,Direction.LEFT));
        legalMoves.add(new Move(x,y+1,MoveTypes.SWORD,Direction.UP));
        legalMoves.add(new Move(x+1,y,MoveTypes.SWORD,Direction.RIGHT));
        legalMoves.add(new Move(x,y-1,MoveTypes.SWORD,Direction.DOWN));
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
    public Boolean SendUpdate(PlayerColor color,Update update) {
        return gameService.ForwardUpdate(color,update);
    }
    @Override
    public Boolean SendUpdates(PlayerColor color,Collection<Update> updates) {
        return gameService.ForwardUpdates(color,updates);
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

    private void PlayerSetup(PlayerColor color) {
        Player player = players.get(color);
        Point tmp = new Point(player.mainNexus);

        tmp.x++;
        Minion minion = new Minion(tmp,parameters.minionHitPoints,color);
        player.addMinion(minion);
        board.getTile(player.mainNexus.x+1,player.mainNexus.y).getTunnel().addEntity(minion);

        tmp.x-=2;
        minion = new Minion(tmp,parameters.minionHitPoints,color);
        player.addMinion(minion);
        board.getTile(player.mainNexus.x-1,player.mainNexus.y).getTunnel().addEntity(minion);

        tmp.x++;
        tmp.y++;
        minion = new Minion(tmp,parameters.minionHitPoints,color);
        player.addMinion(minion);
        board.getTile(player.mainNexus.x,player.mainNexus.y+1).getTunnel().addEntity(minion);
    }

    private void PlayerVisionUpdate(PlayerColor color) {
        Player player = players.get(color);
        Set<Point> visible = new HashSet<>(board.getNexusesVision(color));
        for(Minion minion:player.minions){
            visible.addAll(board.getVisible(minion));
        }
        Point orientation = player.mainNexus.getLocation();
        Set<Point> changes = player.updateVisibleTiles(visible);
        for(Point point:changes){
            Tile actTile = board.getTile(point.x,point.y);
            point.x-=orientation.x;
            point.y-=orientation.y;
            SendUpdate(color,new SimpleTileUpdate(actTile.getDisplayable(),player.isVisible(point),point.x,point.y));
            if(actTile.isHollow()) {
                if(actTile.getDisplayable()!=Displayable.TUNNEL)
                    SendUpdate(color,new SimpleTileUpdate(Displayable.TUNNEL,player.isVisible(point),point.x,point.y));
                for(Entity entity:actTile.getTunnel().getEntities()){
                    SendUpdate(color,new SimpleTileUpdate(entity.getDiplayable(),player.isVisible(point),point.x,point.y));
                }
            }
        }
    }

    public SimpleGameManager(GameService gameService, Parameters parameters) {
        this.parameters=parameters;
        this.gameService=gameService;
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        moveHandlers = new HashMap<>();
        for(MoveTypes moveType : MoveTypes.values()) {
            moveHandlers.put(moveType,ActionHandlerFactory.CreateActionHandler(moveType,this));
        }
        players = new HashMap<>();
        activePlayer = PlayerColor.BLUE;
        players.put(PlayerColor.BLUE,new Player(new Point(board.getNexus(PlayerColor.BLUE).get(0).getX(),board.getNexus(PlayerColor.BLUE).get(0).getY())));
        players.put(PlayerColor.RED,new Player(new Point(board.getNexus(PlayerColor.RED).get(0).getX(),board.getNexus(PlayerColor.RED).get(0).getY())));

        for(PlayerColor playerColor : players.keySet()) {PlayerSetup(playerColor);}
        for(PlayerColor playerColor : players.keySet()) {PlayerVisionUpdate(playerColor);}
    }
}
