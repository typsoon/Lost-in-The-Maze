package com.bksgames.game.globalClasses;

import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public record Move (int x, int y, MoveTypes type, Direction direction) {}
