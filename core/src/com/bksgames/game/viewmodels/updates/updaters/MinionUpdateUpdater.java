package com.bksgames.game.viewmodels.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.globalClasses.MinionUpdate;
import com.bksgames.game.globalClasses.Update;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

import java.awt.*;

public class MinionUpdateUpdater extends Updater {
    @Override
    public void process(Update update) {
        if (!(update instanceof MinionUpdate minionUpdate)) {
            throw new IllegalArgumentException("Minion update must be an instance of MinionUpdate");
        }

        TiledMapTileLayer minionLayer = (TiledMapTileLayer) map.getLayers().get("minions");

        TiledMapTileLayer.Cell minionCell = minionLayer.getCell(MazeMapFactory.maxBoardWidth + minionUpdate.getRelativeX(),
                MazeMapFactory.maxBoardHeight + minionUpdate.getRelativeY());

        if (minionCell != null) {
            minionCell = null;
        }

        Point whereToPutIt = new Point(minionUpdate.getRelativeX(), minionUpdate.getRelativeY());
        Direction.next(whereToPutIt, minionUpdate.getDirection());

        minionCell = minionLayer.getCell(whereToPutIt.x, whereToPutIt.y);

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(minionUpdate.getDisplayable());
        TextureRegion region = atlas.findRegion(info.visible());
        minionCell = new TiledMapTileLayer.Cell();
        minionCell.setTile(new StaticTiledMapTile(region));
    }

    public MinionUpdateUpdater(TiledMap map, TextureAtlas atlas) {super(map, atlas);}
}
