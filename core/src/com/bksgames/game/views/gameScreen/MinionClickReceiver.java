package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.PlayerViewModel;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class MinionClickReceiver extends InputAdapter {
    private final PlayerService playerService;
//    private final TiledMap map;
    private final TiledMapTileLayer minionMapLayer;
    private final Camera gameCamera;
    private final LegalMoves legalMoves;
    private final PlayerViewModel playerViewModel;

    MinionClickReceiver(PlayerService playerService, TiledMapTileLayer minionMapLayer, Camera camera, LegalMoves legalMoves, PlayerViewModel playerViewModel) {
        this.playerService = playerService;
//        this.map = map;
        this.minionMapLayer = minionMapLayer;
        this.gameCamera = camera;
        this.legalMoves = legalMoves;
        this.playerViewModel = playerViewModel;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Convert screen coordinates to world coordinates
        float worldX = gameCamera.unproject(new Vector3(screenX, screenY, 0)).x;
        float worldY = gameCamera.unproject(new Vector3(screenX, screenY, 0)).y;

        // Convert world coordinates to map coordinates
        int tileX = (int) (worldX / minionMapLayer.getTileWidth());
        int tileY = (int) (worldY / minionMapLayer.getTileHeight());

        // Output the tile coordinates
        System.out.println("Clicked tile coordinates: (" + (tileX - MazeMapFactory.maxBoardWidth) + ", " + (tileY - MazeMapFactory.maxBoardHeight) + ")");
//
        TiledMapTileLayer.Cell myCell = minionMapLayer.getCell(tileX, tileY);

        if (myCell != null) {
            Point minionCoords = new Point(tileX - MazeMapFactory.maxBoardWidth, tileY - MazeMapFactory.maxBoardHeight);
            Collection<Move> moves = playerService.getLegalMoves(minionCoords.x, minionCoords.y);

            legalMoves.displayLegalMoves(moves, minionCoords);
        }

        return true; // Indicate that the input event was handled
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!(keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9)) {
            return false;
        }

        List<Point> minionsPositions = playerViewModel.getMinionsPositions();

        keycode -= Input.Keys.NUM_1;

        if (keycode >= minionsPositions.size()) {
            return false;
        }

        Point position = minionsPositions.get(keycode);

        System.out.println("Minion position: " + position);

        Collection<Move> moves = playerService.getLegalMoves(position.x, position.y);
        legalMoves.displayLegalMoves(moves, minionsPositions.get(keycode));

        return true;
    }
}
