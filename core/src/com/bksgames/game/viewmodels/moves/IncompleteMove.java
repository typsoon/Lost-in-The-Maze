package com.bksgames.game.viewmodels.moves;

import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public record IncompleteMove(MoveTypes type, Direction direction) {
}
