package com.bksgames.game;

import com.bksgames.game.core.Player;
import com.bksgames.game.views.PlayerService;
import com.bksgames.game.views.Update;

public class Testing {

    static Update genUpdate(final String str, int relX, int relY) {
        return new Update() {

            @Override
            public String whatToDisplay() {
                return str;
            }

            @Override
            public int getRelativeX(Player.PlayerColor watched) {
                return relX;
            }

            @Override
            public int getRelativeY(Player.PlayerColor watched) {
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
