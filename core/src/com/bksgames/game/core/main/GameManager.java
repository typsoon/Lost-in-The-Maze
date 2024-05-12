package com.bksgames.game.core.main;

import com.bksgames.game.core.utils.Move;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.updateData.Update;

import java.util.Collection;
import java.util.Map;

public interface GameManager  {
    Boolean makeMove(Move move);
    Collection<Move> getLegalMoves(int x, int y, PlayerColor color);
    Board getBoard();
    Map<PlayerColor, Player> getPlayers();
    Boolean SendUpdate(PlayerColor color,Update update);
    Boolean SendUpdates(PlayerColor color,Collection<Update> updates);
}
