package com.bksgames.game.core.main;

import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.MinionEvent;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.PlayerColor;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public interface GameManager  {
    boolean makeMove(Move move);
    Collection<Move> getLegalMoves(Point position, PlayerColor color);
    default Collection<Move> getLegalMoves(int x, int y, PlayerColor color){
        return getLegalMoves(new Point(x,y), color);
    }
    Board getBoard();
    Map<PlayerColor, Player> getPlayers();
    Parameters getParameters();
    PlayerColor getCurrentPlayer();

    void setCurrentPlayer(PlayerColor player);

    void playerVisionUpdate(PlayerColor color);
    void minionUpdate(PlayerColor color, Point minionLocation, Direction direction, MinionEvent minionEvent, ActionToken minionMove);
    void laserUpdate(Direction direction, Direction deflected, Point position);

    void endTurn();
}
