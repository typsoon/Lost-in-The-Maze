package com.bksgames.game.core.utils;

import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.MoveTypes;

public record Move (int x, int y, MoveTypes type, Direction direction) {}
