package com.bksgames.game.core.test.main;

import com.bksgames.game.core.test.boards.Board;
import com.bksgames.game.core.test.utils.Parameters;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.Update;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MinionEvent;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.globalClasses.enums.PlayerColor;


import java.awt.*;
import java.util.Collection;
import java.util.Map;

public interface GameManager  {
    Boolean makeMove(Move move);
    Collection<Move> getLegalMoves(int x, int y, PlayerColor color);
    Board getBoard();
    Map<PlayerColor, Player> getPlayers();
    Boolean sendUpdate(PlayerColor color, Update update);
    Boolean sendUpdates(PlayerColor color, Collection<Update> updates);
    Parameters getParameters();
    PlayerColor getCurrentPlayer();
    void setCurrentPlayer(PlayerColor player);
    void playerVisionUpdate(PlayerColor color);
    void minionUpdate(PlayerColor color, Point minionLocation, Direction direction, MinionEvent minionEvent, MoveTypes minionMove);
}
