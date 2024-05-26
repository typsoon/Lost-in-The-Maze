package com.bksgames.game.core.main;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.PlayerColor;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public interface GameManager  {

    boolean makeMove(Move move);

    <T extends Update> boolean sendUpdate(UpdateHolder<T> updateHolder, PlayerColor playerColor);
    default <T extends Update> boolean sendUpdate(UpdateHolder<T> updateHolder){
        for(PlayerColor playerColor: getPlayers().keySet()){
            sendUpdate(updateHolder, playerColor);
        }
        return true;
    }

    Collection<Move> getLegalMoves(Point position);
    default Collection<Move> getLegalMoves(int x, int y){
        return getLegalMoves(new Point(x,y));
    }


    Board getBoard();
    Map<PlayerColor, Player> getPlayers();
    Parameters getParameters();
    PlayerColor getCurrentPlayer();

    void playerVisionUpdate(PlayerColor color);

    void endTurn();
}
