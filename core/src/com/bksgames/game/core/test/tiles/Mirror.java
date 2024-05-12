package com.bksgames.game.core.test.tiles;

import com.bksgames.game.core.test.utils.Owned;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.PlayerColor;

public class Mirror implements Owned {
	private PlayerColor owner;
	Orientation orientation;

	@Override
	public PlayerColor getOwner() {
		return owner;
	}

	public enum Orientation {
		SLASH, BACKSLASH;
	}

	public Direction deflect(Direction direction){
		if(orientation==Orientation.SLASH) {
			return Direction.values()[(direction.ordinal()-1)%4];
		}
		else{
			return Direction.values()[(direction.ordinal()+1)%4];
		}
	}

	public Mirror(Orientation orientation){
		this.orientation=orientation;
	}
}
