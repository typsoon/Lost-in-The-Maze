package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.LaserUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.Player;

public class LaserUpdateHolder extends UpdateHolder<LaserUpdate> {

    @Override
    public LaserUpdate encode(Player player) {
        if(!player.isVisible(content.relativeX(),content.relativeY())) {
            return null;
        }
        return new LaserUpdate(content.direction(), content.deflectedDirection(), encodedX(player), encodedY(player));
    }

    LaserUpdateHolder(LaserUpdate content) {
        super(content);
    }
}
