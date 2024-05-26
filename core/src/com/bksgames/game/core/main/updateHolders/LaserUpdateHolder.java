package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.LaserUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.Player;

public class LaserUpdateHolder extends UpdateHolder<LaserUpdate> {

    @Override
    public LaserUpdate encode(Player player) {
        if(!player.isVisible(content.getRelativeX(),content.getRelativeY())) {
            return null;
        }
        return new LaserUpdate() {
            @Override
            public Direction getDirection() {
                return content.getDirection();
            }

            @Override
            public Direction getDeflectedDirection() {
                return content.getDeflectedDirection();
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

    LaserUpdateHolder(LaserUpdate content) {
        super(content);
    }
}
