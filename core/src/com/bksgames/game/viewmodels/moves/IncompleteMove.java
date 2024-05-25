package com.bksgames.game.viewmodels.moves;

import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.ActionToken;

public record IncompleteMove(ActionToken type, Direction direction) {
}
