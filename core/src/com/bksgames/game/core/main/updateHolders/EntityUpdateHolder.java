package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.Player;

public class EntityUpdateHolder extends UpdateHolder<EntityUpdate> {

    @Override
    public EntityUpdate encode(Player player) {
        if(!player.isVisible(content.getRelativeX(),content.getRelativeY())) {
            return null;
        }
        return new EntityUpdate() {
            @Override
            public Direction getDirection() {
                return content.getDirection();
            }

            @Override
            public Displayable getDisplayable() {
                return content.getDisplayable();
            }

            @Override
            public UpdateIDs getID() {
                return content.getID();
            }

            @Override
            public int getRelativeX() {
                return content.getRelativeX()- player.getMainNexus().x;
            }

            @Override
            public int getRelativeY() {
                return content.getRelativeY()- player.getMainNexus().y;
            }
        };
    }
    EntityUpdateHolder(EntityUpdate content) {
        super(content);
    }
}
