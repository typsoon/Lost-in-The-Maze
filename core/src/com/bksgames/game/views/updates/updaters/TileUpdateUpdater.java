package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class TileUpdateUpdater extends Updater<TileUpdate> {
    @Override
    public void process(TileUpdate tileUpdate) {
        playerViewModel.tileRevealed(new Point(tileUpdate.relativeX(), tileUpdate.relativeY()));

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(tileUpdate.whatToDisplay());

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(info.layer());

        TextureRegion region = atlas.findRegion(tileUpdate.isVisible() ? info.visible() : info.revealed());

        TiledMapTileLayer.Cell myCell = layer.getCell(MazeMapFactory.maxBoardWidth + tileUpdate.relativeX(), MazeMapFactory.maxBoardWidth + tileUpdate.relativeY());
        if (myCell == null) {
            myCell = new TiledMapTileLayer.Cell();
            myCell.setTile(new StaticTiledMapTile(region));
        }
        else { myCell.getTile().setTextureRegion(region);}

        layer.setCell(MazeMapFactory.maxBoardWidth + tileUpdate.relativeX(), MazeMapFactory.maxBoardHeight + tileUpdate.relativeY(), myCell);
    }

    public TileUpdateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }
}
