package com.bksgames.game.core;

import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.PlayerColor;

public class Mirror{
	PlayerColor color;
	Orientation orientation;

	enum Orientation {
		SLASH, BACKSLASH;
	}

	Direction deflect(Direction direction){
		Direction afterDirection = direction;

		if(orientation==Orientation.SLASH) {
//			if(direction==Direction.RIGHT) afterDirection = Direction.UP;
//			if(direction==Direction.UP) afterDirection = Direction.LEFT;
//			if(direction==Direction.LEFT) afterDirection = Direction.DOWN;
//			if(direction==Direction.DOWN) afterDirection = Direction.RIGHT;

			return Direction.values()[(direction.ordinal()-1)%4];
		}
		else{
//			if(direction==Direction.RIGHT) afterDirection = Direction.DOWN;
//			if(direction==Direction.DOWN) afterDirection = Direction.LEFT;
//			if(direction==Direction.UP) afterDirection = Direction.RIGHT;
//			if(direction==Direction.LEFT) afterDirection = Direction.UP;

			return Direction.values()[(direction.ordinal()+1)%4];
		}
	}
}
