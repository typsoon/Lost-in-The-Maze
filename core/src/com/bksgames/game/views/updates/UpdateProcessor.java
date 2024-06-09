package com.bksgames.game.views.updates;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.bksgames.game.common.updates.*;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.updates.updaters.*;

public class UpdateProcessor implements UpdateVisitor {
    private final Updater<EntityStateUpdate> entityStateUpdater;
    private final Updater<EntityUpdate> entityUpdateUpdater;
    private final Updater<LaserUpdate> laserUpdateHandler;
    private final Updater<SwordUpdate> swordUpdateHandler;
    private final Updater<TileUpdate> tileUpdateUpdater;


    public void process(Update update) {
        update.visit(this);
    }

    public UpdateProcessor(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {

        entityStateUpdater = new EntityStateUpdater(map, atlas, playerViewModel);
        entityUpdateUpdater = new EntityUpdateUpdater(map, atlas, playerViewModel);
        laserUpdateHandler = new LaserUpdateHandler(map, atlas, playerViewModel);
        swordUpdateHandler = new SwordUpdateHandler(map, atlas, playerViewModel);
        tileUpdateUpdater = new TileUpdateUpdater(map, atlas, playerViewModel);
    }

    @Override
    public void visit(EntityStateUpdate entityStateUpdate) {
        entityStateUpdater.process(entityStateUpdate);
    }

    @Override
    public void visit(EntityUpdate entityUpdate) {
        entityUpdateUpdater.process(entityUpdate);
    }

    @Override
    public void visit(LaserUpdate laserUpdate) {
        laserUpdateHandler.process(laserUpdate);
    }

    @Override
    public void visit(SwordUpdate swordUpdate) {
        swordUpdateHandler.process(swordUpdate);
    }

    @Override
    public void visit(TileUpdate tileUpdate) {
        tileUpdateUpdater.process(tileUpdate);
    }
}
