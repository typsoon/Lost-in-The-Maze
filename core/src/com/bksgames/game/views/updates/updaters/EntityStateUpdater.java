package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.updates.EntityStateUpdate;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class EntityStateUpdater extends Updater<EntityStateUpdate> {
    public EntityStateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }

    @Override
    public void process(EntityStateUpdate entityStateUpdate) {
        Point position = new Point(entityStateUpdate.relativeX(), entityStateUpdate.relativeY());
        if (entityStateUpdate.entityEventType() != null) {
            switch (entityStateUpdate.entityEventType()) {
                case KILLED -> {
                    if (entityStateUpdate.isMinion()) {
                        if(playerViewModel.hasPlayableMinion(position))
                            playerViewModel.minionKilled(position);

                        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(DisplayableToImage.getDisplayInfo(Displayable.BLUE_MINION).layer());
                        layer.setCell(MazeMapFactory.maxBoardWidth + position.x, MazeMapFactory.maxBoardHeight + position.y, null);
                    }
                }
                case SPAWNED -> {
                    if (entityStateUpdate.isMinion())
                        playerViewModel.minionSpawned(position);
                }
            }
        }
    }
}
