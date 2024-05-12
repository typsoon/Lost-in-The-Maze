package com.bksgames.game.core.utils;

import com.bksgames.game.globalClasses.LaserUpdate;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.UpdateIDs;

public class SimpleLaserUpdate implements LaserUpdate {

	Direction direction;
	Direction deflectedDirection;
	int x,y;

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public Direction getDeflectedDirection() {
		return deflectedDirection;
	}

	@Override
	public UpdateIDs getID() {
		return UpdateIDs.LASER_UPDATE;
	}

	@Override
	public int getRelativeX() {
		return x;
	}

	@Override
	public int getRelativeY() {
		return y;
	}

	SimpleLaserUpdate(Direction direction, Direction deflectedDirection, int x, int y){
		this.direction = direction;
		this.deflectedDirection = deflectedDirection;
		this.x = x;
		this.y = y;
	}
}
