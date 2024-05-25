package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.legalMovesHandling.LegalMoves;


import java.util.List;

public class MinionClickReceiver extends InputAdapter {
    private final Camera gameCamera;
    private final LegalMoves legalMoves;
    private final PlayerViewModel playerViewModel;

    MinionClickReceiver(Camera camera, LegalMoves legalMoves, PlayerViewModel playerViewModel) {
        this.gameCamera = camera;
        this.legalMoves = legalMoves;
        this.playerViewModel = playerViewModel;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Convert screen coordinates to world coordinates
//        float worldX = gameCamera.unproject(new Vector3(screenX, screenY, 0)).x;
//        float worldY = gameCamera.unproject(new Vector3(screenX, screenY, 0)).y;

        Point minionCoords = MazeMapFactory.unproject(gameCamera.unproject(new Vector3(screenX, screenY, 0)));
        // Output the tile coordinates
//        Gdx.app.log("ClickedTile", "Clicked tile coordinates: (" + (tileX - MazeMapFactory.maxBoardWidth) + ", " + (tileY - MazeMapFactory.maxBoardHeight) + ")");
//
        if (playerViewModel.hasPlayableMinion(minionCoords)) {
            legalMoves.displayLegalMoves(playerViewModel.getMinionId(minionCoords));
        }

        return true; // Indicate that the input event was handled
    }

    @Override
    public boolean keyDown(final int keycode) {
        if (!(keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9)) {
            return false;
        }

        List<Point> minionsPositions = playerViewModel.getMinionsPositions();

        int minionId = keycode - Input.Keys.NUM_1;

        if (minionId >= minionsPositions.size()) {
            return false;
        }

        legalMoves.displayLegalMoves(minionId);

        return true;
    }
}
