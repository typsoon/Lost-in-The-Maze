package com.bksgames.game.core.actions;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.utils.Point;

public class DoorAction extends Action{

	public DoorAction(Direction direction, Point minionPosition) {
		super(direction, minionPosition);
	}

	@Override
	protected void handle() {

	}

	@Override
	protected ActionToken getActionToken() {
		return ActionToken.DOOR;
	}
}
