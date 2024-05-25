package com.bksgames.game.viewmodels.updates.updaters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.LaserUpdate;
import com.bksgames.game.globalClasses.Update;
import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class LaserUpdateHandler extends Updater {
    protected LaserUpdateHandler(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }

    @Override
    public void process(Update update) {
        if (!(update instanceof LaserUpdate laserUpdate))
            throw new IllegalArgumentException("Laser update must be an instance of LaserUpdate");

        playerViewModel.laserFired(new Point(laserUpdate.getRelativeX(), laserUpdate.getRelativeY()));

        Displayable laserKind;
        if (laserUpdate.getDeflectedDirection() == null)
            laserKind = Displayable.STRAIGHT_LASER;
        else laserKind = Displayable.BENT_LASER;

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(laserKind);

        Sprite texture = new Sprite(atlas.findRegion(info.visible()));

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(info.layer());

        TiledMapTileLayer.Cell myCell = new TiledMapTileLayer.Cell();
        myCell.setTile(new StaticTiledMapTile(texture));

        layer.setCell(MazeMapFactory.maxBoardWidth + laserUpdate.getRelativeX(), MazeMapFactory.maxBoardHeight + laserUpdate.getRelativeY(),
                myCell);
    }
}
