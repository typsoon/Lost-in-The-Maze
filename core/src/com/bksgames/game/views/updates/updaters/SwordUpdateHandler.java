package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class SwordUpdateHandler extends Updater<SwordUpdate> {
    @Override
    public void process(SwordUpdate swordUpdate) {
         Point pos = swordUpdate.direction().getNext(new Point(swordUpdate.relativeX(), swordUpdate.relativeY()));

//        TiledMapTileLayer swordLayer = (TiledMapTileLayer) map.getLayers().get("sword");
        playerViewModel.swordSwung(pos);

        TextureRegion region = atlas.findRegion("Hit");

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("sword");

        TiledMapTileLayer.Cell myCell = new TiledMapTileLayer.Cell();
        myCell.setTile(new StaticTiledMapTile(region));

        layer.setCell(MazeMapFactory.maxBoardWidth + pos.x, MazeMapFactory.maxBoardHeight + pos.y, myCell);
    }

    public SwordUpdateHandler(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }
}
