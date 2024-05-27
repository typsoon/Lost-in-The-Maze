package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class TileUpdateUpdater extends Updater {
    @Override
    public void process(Update update) {
        if (!(update instanceof TileUpdate tileUpdate)) {
            throw new IllegalArgumentException("Invalid update type: " + update.getClass().getSimpleName());
        }

        playerViewModel.tileRevealed(new Point(update.getRelativeX(), update.getRelativeY()));

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(tileUpdate.whatToDisplay());

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(info.layer());

        TextureRegion region = atlas.findRegion(tileUpdate.isVisible() ? info.visible() : info.revealed());

        TiledMapTileLayer.Cell myCell = layer.getCell(MazeMapFactory.maxBoardWidth + tileUpdate.getRelativeX(), MazeMapFactory.maxBoardWidth + tileUpdate.getRelativeY());
        if (myCell == null) {
            myCell = new TiledMapTileLayer.Cell();
            myCell.setTile(new StaticTiledMapTile(region));
        }
        else { myCell.getTile().setTextureRegion(region);}

        layer.setCell(MazeMapFactory.maxBoardWidth + tileUpdate.getRelativeX(), MazeMapFactory.maxBoardHeight + tileUpdate.getRelativeY(), myCell);
    }

    public TileUpdateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }
}
