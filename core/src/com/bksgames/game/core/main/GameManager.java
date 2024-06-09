package com.bksgames.game.core.main;

import com.bksgames.game.core.moves.Action;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.PlayerColor;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public interface GameManager  {

//    boolean makeMove(Action acceptMove);

    @SuppressWarnings({"UnusedReturnValue", "SameReturnValue"})
    <T extends Update> boolean sendUpdate(UpdateHolder<T> updateHolder, PlayerColor playerColor);

    @SuppressWarnings({"UnusedReturnValue", "SameReturnValue"})
    default <T extends Update> boolean sendUpdate(UpdateHolder<T> updateHolder){
        for(PlayerColor playerColor: getPlayers().keySet()){
            sendUpdate(updateHolder, playerColor);
        }
        return true;
    }

    Collection<Action> getLegalMoves(Point position);


    Board getBoard();
    Map<PlayerColor, Player> getPlayers();
    Parameters getParameters();
    PlayerColor getCurrentPlayer();
    DamageManager getDamageManager();
    VisionManager getVisionManager();

    PlayerColor getWinner();
    void endTurn();
}
