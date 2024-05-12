package com.bksgames.game.viewmodels.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.globalClasses.TileUpdate;
import com.bksgames.game.globalClasses.Update;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.views.gameScreen.GameScreen;

public class TileUpdateUpdater extends Updater {
    @Override
    public void process(Update update) {
        if (!(update instanceof TileUpdate tileUpdate)) {
            throw new IllegalArgumentException("Invalid update type: " + update.getClass().getSimpleName());
        }

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(tileUpdate.whatToDisplay());

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(info.layer());

        TextureRegion region = atlas.findRegion(tileUpdate.isVisible() ? info.visible() : info.revealed());

        TiledMapTileLayer.Cell myCell = layer.getCell(GameScreen.maxBoardWidth + tileUpdate.getRelativeX(), GameScreen.maxBoardWidth + tileUpdate.getRelativeY());
        if (myCell == null) {
            myCell = new TiledMapTileLayer.Cell();
            myCell.setTile(new StaticTiledMapTile(region));
        }
        else { myCell.getTile().setTextureRegion(region);}

        layer.setCell(GameScreen.maxBoardWidth + tileUpdate.getRelativeX(), GameScreen.maxBoardHeight + tileUpdate.getRelativeY(), myCell);
    }


    public TileUpdateUpdater(TiledMap map, TextureAtlas atlas) {
        super(map, atlas);
    }
}
