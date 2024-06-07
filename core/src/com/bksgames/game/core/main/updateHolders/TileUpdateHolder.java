package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.core.main.Player;

public class TileUpdateHolder extends UpdateHolder<TileUpdate> {
    @Override
    public TileUpdate encode(Player player) {
        if(player.isVisible(content.relativeX(),content.relativeY()) != content.isVisible()) {
            return null;
        }
        return new TileUpdate(content.whatToDisplay(), content.isVisible(),
                encodedX(player), encodedY(player));
    }

    TileUpdateHolder(TileUpdate content) {
        super(content);
    }
}
