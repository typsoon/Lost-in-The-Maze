package com.bksgames.game.viewmodels.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.updateData.TileUpdate;
import com.bksgames.game.updateData.Update;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.views.GameScreen;

public class TileUpdateUpdater extends Updater {
    @Override
    public void process(Update update) {
        if (!(update instanceof TileUpdate tileUpdate)) {
            throw new IllegalArgumentException("Invalid update type: " + update.getClass().getSimpleName());
        }

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(tileUpdate.whatToDisplay());

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(info.layer());

        TextureRegion region = atlas.findRegion(tileUpdate.isVisible() ? info.visible() : info.revealed());

//        layer.setCell(GameScreen.maxBoardLength+ tileUpdate.getRelativeX(), GameScreen.maxBoardLength + tileUpdate.getRelativeY(), myCell);
        layer.getCell(GameScreen.maxBoardLength+ tileUpdate.getRelativeX(), GameScreen.maxBoardLength + tileUpdate.getRelativeY()).getTile().setTextureRegion(region);
    }


    public TileUpdateUpdater(TiledMap map, TextureAtlas atlas) {
        super(map, atlas);
    }
}
