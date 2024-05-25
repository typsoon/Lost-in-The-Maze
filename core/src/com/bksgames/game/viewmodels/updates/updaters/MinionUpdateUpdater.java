package com.bksgames.game.viewmodels.updates.updaters;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.MinionUpdate;
import com.bksgames.game.common.Update;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;



public class MinionUpdateUpdater extends Updater {
    @Override
    public void process(Update update) {
        if (!(update instanceof MinionUpdate minionUpdate)) {
            throw new IllegalArgumentException("Minion update must be an instance of MinionUpdate");
        }

        playerViewModel.minionMoved(
                new Point(minionUpdate.getRelativeX(), minionUpdate.getRelativeY()), minionUpdate.getDirection());

        TiledMapTileLayer minionLayer = (TiledMapTileLayer) map.getLayers().get("minions");

        TiledMapTileLayer.Cell minionCell = minionLayer.getCell(MazeMapFactory.maxBoardWidth + minionUpdate.getRelativeX(),
                MazeMapFactory.maxBoardHeight + minionUpdate.getRelativeY());

        if (minionCell != null) {
            minionLayer.setCell(MazeMapFactory.maxBoardWidth + minionUpdate.getRelativeX(),
                    MazeMapFactory.maxBoardHeight + minionUpdate.getRelativeY(), null);
        }

//        TODO: make animations in here

        Point whereToPutIt = new Point(minionUpdate.getRelativeX(), minionUpdate.getRelativeY());
        minionUpdate.getDirection().next(whereToPutIt);

//        minionCell = minionLayer.getCell(whereToPutIt.x, whereToPutIt.y);
//
//        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(minionUpdate.getDisplayable());
//        TextureRegion region = atlas.findRegion(info.visible());
//        minionCell = new TiledMapTileLayer.Cell();
//        minionCell.setTile(new StaticTiledMapTile(region));
    }

    public MinionUpdateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {super(map, atlas, playerViewModel);}
}
