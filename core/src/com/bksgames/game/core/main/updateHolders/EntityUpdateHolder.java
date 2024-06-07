package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.Player;

public class EntityUpdateHolder extends UpdateHolder<EntityUpdate> {

    @Override
    public EntityUpdate encode(Player player) {
        if(!player.isVisible(content.relativeX(),content.relativeY())) {
            return null;
        }
        return new EntityUpdate(content.direction(), content.displayable(), encodedX(player), encodedY(player));
    }
    EntityUpdateHolder(EntityUpdate content) {
        super(content);
    }
}
