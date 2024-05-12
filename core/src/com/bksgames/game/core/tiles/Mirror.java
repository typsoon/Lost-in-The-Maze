package com.bksgames.game.core.tiles;

import com.bksgames.game.core.utils.Owned;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.PlayerColor;

public class Mirror implements Owned {
	private PlayerColor owner;
	Orientation orientation;

	@Override
	public PlayerColor getOwner() {
		return owner;
	}

	enum Orientation {
		SLASH, BACKSLASH;
	}

	Direction deflect(Direction direction){
		if(orientation==Orientation.SLASH) {
			return Direction.values()[(direction.ordinal()-1)%4];
		}
		else{
			return Direction.values()[(direction.ordinal()+1)%4];
		}
	}
}
