package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.core.main.Player;

public class TileUpdateHolder extends UpdateHolder<TileUpdate> {
    @Override
    public TileUpdate encode(Player player) {
        if(player.isVisible(content.getRelativeX(),content.getRelativeY()) != content.isVisible()) {
            return null;
        }
        return new TileUpdate() {

            @Override
            public Displayable whatToDisplay() {
                return content.whatToDisplay();
            }

            @Override
            public boolean isVisible() {
                return content.isVisible();
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

    TileUpdateHolder(TileUpdate content) {
        super(content);
    }
}
