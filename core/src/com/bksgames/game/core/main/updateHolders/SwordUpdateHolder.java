package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.Player;

public class SwordUpdateHolder extends UpdateHolder<SwordUpdate> {

    @Override
    public SwordUpdate encode(Player player) {
        if(!player.isVisible(content.relativeX(),content.relativeY()))
            return null;
        return new SwordUpdate(content.direction(), encodedX(player), encodedY(player));
    }
    SwordUpdateHolder(SwordUpdate content) {
        super(content);
    }
}
