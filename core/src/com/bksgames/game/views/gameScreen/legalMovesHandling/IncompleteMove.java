package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.utils.Direction;

public record IncompleteMove(ActionToken type, Direction direction) {
}
