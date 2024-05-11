package com.bksgames.game.core;

import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.MoveTypes;
import com.bksgames.game.enums.PlayerColor;

public record Move (int x, int y, MoveTypes type, Direction direction) {}
