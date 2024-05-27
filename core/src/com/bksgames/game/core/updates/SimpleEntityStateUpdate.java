package com.bksgames.game.core.updates;

import com.bksgames.game.common.EntityEvent;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.EntityStateUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.core.utils.Point;

public class SimpleEntityStateUpdate implements EntityStateUpdate {
    private final boolean isMinion;
    private final EntityEvent event;
    private final ActionToken moveType;
    private final Point position;
    @Override
    public boolean isMinion() {
        return isMinion;
    }

    @Override
    public EntityEvent entityEventType() {
        return event;
    }

    @Override
    public ActionToken getMoveType() {
        return moveType;
    }

    @Override
    public UpdateIDs getID() {
        return UpdateIDs.ENTITY_STATE_UPDATE;
    }

    @Override
    public int getRelativeX() {
        return position.x;
    }

    @Override
    public int getRelativeY() {
        return position.y;
    }
    public  SimpleEntityStateUpdate(boolean isMinion, EntityEvent event, ActionToken moveType, Point position){

        this.isMinion = isMinion;
        this.event = event;
        this.moveType = moveType;
        this.position = position;
    }
}
