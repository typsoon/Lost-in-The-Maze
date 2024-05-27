package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.EntityEvent;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.EntityStateUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.core.main.Player;

public class EntityStateUpdateHolder extends UpdateHolder<EntityStateUpdate> {
    @Override
    public EntityStateUpdate encode(Player player) {
        return new EntityStateUpdate() {
            @Override
            public boolean isMinion() {
                return content.isMinion();
            }

            @Override
            public EntityEvent entityEventType() {
                return content.entityEventType();
            }

            @Override
            public ActionToken getMoveType() {
                return content.getMoveType();
            }

            @Override
            public UpdateIDs getID() {
                return content.getID();
            }

            @Override
            public int getRelativeX() {
                return content.getRelativeX()-player.getMainNexus().x;
            }

            @Override
            public int getRelativeY() {
                return content.getRelativeY()-player.getMainNexus().y;
            }
        };
    }

    EntityStateUpdateHolder(EntityStateUpdate content) {
        super(content);
    }
}
