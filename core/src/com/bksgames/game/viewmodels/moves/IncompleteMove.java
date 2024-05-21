package com.bksgames.game.viewmodels.moves;

import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.ActionToken;

public record IncompleteMove(ActionToken type, Direction direction) {
}
