package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.updates.EntityStateUpdate;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.viewmodels.PlayerViewModel;

public class EntityStateUpdater extends Updater {
    public EntityStateUpdater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }

    @Override
    public void process(Update update) {
        if (!(update instanceof EntityStateUpdate entityStateUpdate)) {
            throw new IllegalArgumentException("EntityStateUpdater only works with EntityStateUpdate objects");
        }

        Point position = new Point(entityStateUpdate.getRelativeX(), entityStateUpdate.getRelativeY());
        if (entityStateUpdate.entityEventType() != null) {
            switch (entityStateUpdate.entityEventType()) {
                case KILLED -> {
                    if (playerViewModel.hasPlayableMinion(position)) {
                        playerViewModel.minionKilled(position);
                        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(DisplayableToImage.getDisplayInfo(Displayable.BLUE_MINION).layer());
                        layer.setCell(position.x, position.y, null);
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
