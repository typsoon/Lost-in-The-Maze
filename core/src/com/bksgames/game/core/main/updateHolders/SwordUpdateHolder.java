package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.Player;

public class SwordUpdateHolder extends UpdateHolder<SwordUpdate> {

    @Override
    public SwordUpdate encode(Player player) {
        if(!player.isVisible(content.getRelativeX(),content.getRelativeY()))
            return null;
        return new SwordUpdate() {
            @Override
            public Direction getDirection() {
                return content.getDirection();
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
    SwordUpdateHolder(SwordUpdate content) {
        super(content);
    }
}
