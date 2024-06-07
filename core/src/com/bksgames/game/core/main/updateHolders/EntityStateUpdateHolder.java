package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.EntityStateUpdate;
import com.bksgames.game.core.main.Player;

public class EntityStateUpdateHolder extends UpdateHolder<EntityStateUpdate> {
    @Override
    public EntityStateUpdate encode(Player player) {
        return new EntityStateUpdate(content.isMinion(), content.entityEventType(), content.getMoveType(), encodedX(player), encodedY(player));
    }

    EntityStateUpdateHolder(EntityStateUpdate content) {
        super(content);
    }
}
