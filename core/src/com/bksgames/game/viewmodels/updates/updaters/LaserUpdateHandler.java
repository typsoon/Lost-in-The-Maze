package com.bksgames.game.viewmodels.updates.updaters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.LaserUpdate;
import com.bksgames.game.common.Update;
import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.Displayable;
import com.bksgames.game.viewmodels.DisplayableToImage;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class LaserUpdateHandler extends Updater {
    public LaserUpdateHandler(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
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

        int rotation;
        if (laserKind == Displayable.STRAIGHT_LASER) {
            rotation = switch (laserUpdate.getDirection()) {
                case LEFT, RIGHT -> TiledMapTileLayer.Cell.ROTATE_0;
                case UP, DOWN -> TiledMapTileLayer.Cell.ROTATE_90;
            };
        }
        else {
            if (laserUpdate.getDirection() == Direction.UP && laserUpdate.getDeflectedDirection() == Direction.LEFT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_180;
            } else if (laserUpdate.getDirection() == Direction.UP && laserUpdate.getDeflectedDirection() == Direction.RIGHT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_270;
            } else if (laserUpdate.getDirection() == Direction.DOWN && laserUpdate.getDeflectedDirection() == Direction.LEFT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_180;
            } else if (laserUpdate.getDirection() == Direction.DOWN && laserUpdate.getDeflectedDirection() == Direction.RIGHT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_0;
            } else if (laserUpdate.getDirection() == Direction.LEFT && laserUpdate.getDeflectedDirection() == Direction.UP) {
                rotation = TiledMapTileLayer.Cell.ROTATE_90;
            } else if (laserUpdate.getDirection() == Direction.LEFT && laserUpdate.getDeflectedDirection() == Direction.DOWN) {
                rotation = TiledMapTileLayer.Cell.ROTATE_270;
            } else if (laserUpdate.getDirection() == Direction.RIGHT && laserUpdate.getDeflectedDirection() == Direction.UP) {
                rotation = TiledMapTileLayer.Cell.ROTATE_90;
            } else {
//            else if (laserUpdate.getDirection() == Direction.RIGHT && laserUpdate.getDeflectedDirection() == Direction.DOWN) {
                rotation = TiledMapTileLayer.Cell.ROTATE_0;
            }
        }

        myCell.setRotation(rotation);

        layer.setCell(MazeMapFactory.maxBoardWidth + laserUpdate.getRelativeX(), MazeMapFactory.maxBoardHeight + laserUpdate.getRelativeY(),
                myCell);
//        layer.getCell(MazeMapFactory.maxBoardWidth + )
    }
}
