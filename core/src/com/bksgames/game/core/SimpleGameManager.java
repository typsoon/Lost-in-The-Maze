package com.bksgames.game.core;

import com.bksgames.game.core.actionsHandlers.ActionHandler;
import com.bksgames.game.core.actionsHandlers.ActionHandlerFactory;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.MoveTypes;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.updateData.Update;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;


public class SimpleGameManager implements GameManager {
    private final Board board;
    private final Map<PlayerColor, Player> players;
    private final Map<MoveTypes, ActionHandler> moveHandlers;
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


    public SimpleGameManager(Parameters parameters) {
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        moveHandlers = new HashMap<>();
        for(MoveTypes moveType : MoveTypes.values()) {
            moveHandlers.put(moveType,ActionHandlerFactory.CreateActionHandler(moveType,this));
        }
        players = new HashMap<>();
        activePlayer = PlayerColor.BLUE;
        players.put(PlayerColor.BLUE,new Player(new Point(board.getNexus(PlayerColor.BLUE).get(0).getX(),board.getNexus(PlayerColor.BLUE).get(0).getY())));
        players.put(PlayerColor.RED,new Player(new Point(board.getNexus(PlayerColor.RED).get(0).getX(),board.getNexus(PlayerColor.RED).get(0).getY())));
    }
}
