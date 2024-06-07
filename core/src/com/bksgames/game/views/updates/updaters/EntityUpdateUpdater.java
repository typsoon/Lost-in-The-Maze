package com.bksgames.game.views.updates.updaters;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;



public class EntityUpdateUpdater extends Updater<EntityUpdate> {
    @Override
    public void process(EntityUpdate entityUpdate) {
        playerViewModel.minionMoved(
                new Point(entityUpdate.relativeX(), entityUpdate.relativeY()), entityUpdate.direction());

        TiledMapTileLayer minionLayer = (TiledMapTileLayer) map.getLayers().get("minions");

        TiledMapTileLayer.Cell minionCell = minionLayer.getCell(MazeMapFactory.maxBoardWidth + entityUpdate.relativeX(),
                MazeMapFactory.maxBoardHeight + entityUpdate.relativeY());

        if (minionCell != null) {
            minionLayer.setCell(MazeMapFactory.maxBoardWidth + entityUpdate.relativeX(),
                    MazeMapFactory.maxBoardHeight + entityUpdate.relativeY(), null);
        }

//        TODO: make animations in here

        Point whereToPutIt = new Point(entityUpdate.relativeX(), entityUpdate.relativeY());
        entityUpdate.direction().next(whereToPutIt);

//        minionCell = minionLayer.getCell(whereToPutIt.x, whereToPutIt.y);
//
//        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(entityUpdate.getDisplayable());
//        TextureRegion region = atlas.findRegion(info.visible());
//        minionCell = new TiledMapTileLayer.Cell();
//        minionCell.setTile(new StaticTiledMapTile(region));
    }

    public EntityUpdateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {super(map, atlas, playerViewModel);}
}
