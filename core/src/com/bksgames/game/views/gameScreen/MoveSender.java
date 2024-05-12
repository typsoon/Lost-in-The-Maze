package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.services.PlayerService;

import java.awt.*;
import java.util.Collection;

public class MoveSender extends InputAdapter {
    private final PlayerService playerService;
    private final TiledMap map;
    private final TiledMapTileLayer minionMapLayer;
    private final Camera gameCamera;
    private LegalMovesHandler legalMovesHandler;

    MoveSender(PlayerService playerService, TiledMap map, TiledMapTileLayer minionMapLayer, Camera camera) {
        this.playerService = playerService;
        this.map = map;
        this.minionMapLayer = minionMapLayer;
        this.gameCamera = camera;
    }

    public void setLegalMovesHandler(LegalMovesHandler legalMovesHandler) { this.legalMovesHandler = legalMovesHandler; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Convert screen coordinates to world coordinates
        float worldX = gameCamera.unproject(new Vector3(screenX, screenY, 0)).x;
        float worldY = gameCamera.unproject(new Vector3(screenX, screenY, 0)).y;

        // Convert world coordinates to map coordinates
        int tileX = (int) (worldX / minionMapLayer.getTileWidth());
        int tileY = (int) (worldY / minionMapLayer.getTileHeight());

        // Output the tile coordinates
        System.out.println("Clicked tile coordinates: (" + (tileX - GameScreen.maxBoardWidth) + ", " + (tileY - GameScreen.maxBoardHeight) + ")");
//
        TiledMapTileLayer.Cell myCell = minionMapLayer.getCell(tileX, tileY);

        if (myCell != null) {
            Point minionCoords = new Point(tileX - GameScreen.maxBoardWidth, tileY - GameScreen.maxBoardHeight);
            Collection<Move> moves = playerService.getLegalMoves(minionCoords.x, minionCoords.y);

            legalMovesHandler.displayLegalMoves(moves, minionCoords);
        }

        return true; // Indicate that the input event was handled
    }
}
