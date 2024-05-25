package com.bksgames.game.viewmodels.moves;

import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.moves.ActionToken;

public record IncompleteMove(ActionToken type, Direction direction) {
}
