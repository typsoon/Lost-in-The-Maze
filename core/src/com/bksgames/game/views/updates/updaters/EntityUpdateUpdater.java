package com.bksgames.game.views.updates.updaters;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;



public class EntityUpdateUpdater extends Updater {
    @Override
    public void process(Update update) {
        if (!(update instanceof EntityUpdate entityUpdate)) {
            throw new IllegalArgumentException("Minion update must be an instance of EntityUpdate");
        }

        playerViewModel.minionMoved(
                new Point(entityUpdate.getRelativeX(), entityUpdate.getRelativeY()), entityUpdate.getDirection());

        TiledMapTileLayer minionLayer = (TiledMapTileLayer) map.getLayers().get("minions");

        TiledMapTileLayer.Cell minionCell = minionLayer.getCell(MazeMapFactory.maxBoardWidth + entityUpdate.getRelativeX(),
                MazeMapFactory.maxBoardHeight + entityUpdate.getRelativeY());

        if (minionCell != null) {
            minionLayer.setCell(MazeMapFactory.maxBoardWidth + entityUpdate.getRelativeX(),
                    MazeMapFactory.maxBoardHeight + entityUpdate.getRelativeY(), null);
        }

//        TODO: make animations in here

        Point whereToPutIt = new Point(entityUpdate.getRelativeX(), entityUpdate.getRelativeY());
        entityUpdate.getDirection().next(whereToPutIt);

//        minionCell = minionLayer.getCell(whereToPutIt.x, whereToPutIt.y);
//
//        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(entityUpdate.getDisplayable());
//        TextureRegion region = atlas.findRegion(info.visible());
//        minionCell = new TiledMapTileLayer.Cell();
//        minionCell.setTile(new StaticTiledMapTile(region));
    }

    public EntityUpdateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {super(map, atlas, playerViewModel);}
}
