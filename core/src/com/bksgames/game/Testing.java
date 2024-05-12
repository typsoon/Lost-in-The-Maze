package com.bksgames.game;

import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.globalClasses.enums.UpdateIDs;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.globalClasses.TileUpdate;

public class Testing {

    static TileUpdate genUpdate(final UpdateIDs IDs, final Displayable whatToDisplay, int relX, int relY) {
        return new TileUpdate() {

            @Override
            public UpdateIDs getID() {
                return IDs;
            }

            @Override
            public int getRelativeX() {
                return relX;
            }

            @Override
            public int getRelativeY() {
                return relY;
            }

            @Override
            public Displayable whatToDisplay() {
                return whatToDisplay;
            }

            @Override
            public boolean isVisible() {
                return true;
            }
        };
    }

    public static void dummyUpdater(PlayerService playerService) {
        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_NEXUS, 0, 0));

        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_MINION, 1, 0));
        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_MINION,-1, 0));
        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_MINION,0, -1));

        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.TUNNEL, 1, 0));
        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.TUNNEL,-1, 0));
        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.TUNNEL,0, -1));

//        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_MINION,-300, -300));
////        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.RED_NEXUS,-300, -300+2));
//        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_NEXUS,-300-1, -300-1));
//        playerService.pushUpdate(genUpdate(UpdateIDs.TILE_UPDATE, Displayable.BLUE_NEXUS,-300-1, -300+1));
    }
}
