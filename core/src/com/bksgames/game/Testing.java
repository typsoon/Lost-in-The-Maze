package com.bksgames.game;

import com.bksgames.game.core.Player;
import com.bksgames.game.service.PlayerService;
import com.bksgames.game.views.Updates.Update;
import com.bksgames.game.views.Updates.UpdateKinds.TileUpdate;

public class Testing {

    static TileUpdate genUpdate(final String str, int relX, int relY) {
        return new TileUpdate() {

            @Override
            public String whatToDisplay() {
                return str;
            }

            @Override
            public String getKey() {
                return "tileUpdate";
            }

            @Override
            public int getRelativeX() {
                return relX;
            }

            @Override
            public int getRelativeY() {
                return relY;
            }
        };
    }

    public static void dummyUpdater(PlayerService playerService) {
        playerService.pushUpdate(genUpdate("BlueNexus", 0, 0));
        playerService.pushUpdate(genUpdate("BlueMinion", 1, 0));
        playerService.pushUpdate(genUpdate("BlueMinion", -1, 0));
        playerService.pushUpdate(genUpdate("BlueMinion", 0, -1));

    }
}
