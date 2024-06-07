package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.updates.LaserUpdate;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.Displayable;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.gameScreen.MazeMapFactory;

public class LaserUpdateHandler extends Updater<LaserUpdate> {
    public LaserUpdateHandler(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }

//    private Texture mergeTextures(Texture tex1, Texture tex2) {
//        SpriteBatch spriteBatch = new SpriteBatch();
//
//        int width = Math.max(tex1.getWidth(), tex2.getWidth());
//        int height = Math.max(tex1.getHeight(), tex2.getHeight());
//
//        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
//        frameBuffer.begin();
//
//        Gdx.gl.glClearColor(0, 0, 0, 0);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        spriteBatch.begin();
//        spriteBatch.draw(tex1, 0, 0);
//        spriteBatch.draw(tex2, 0, 0);
//        spriteBatch.end();
//
//        frameBuffer.end();
//
//        Texture result = frameBuffer.getColorBufferTexture();
//        result.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//
//        // Dispose of the FrameBuffer after use
//        frameBuffer.dispose();
//
//        return result;
//    }

    @Override
    public void process(LaserUpdate laserUpdate) {
        playerViewModel.laserFired(new Point(laserUpdate.relativeX(), laserUpdate.relativeY()));

        Displayable laserKind;
        if (laserUpdate.deflectedDirection() == null)
            laserKind = Displayable.STRAIGHT_LASER;
        else laserKind = Displayable.BENT_LASER;

        DisplayableToImage.DisplayInfo info = DisplayableToImage.getDisplayInfo(laserKind);

        Sprite texture = new Sprite(atlas.findRegion(info.visible()));

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(info.layer());
        TiledMapTileLayer.Cell myCell = new TiledMapTileLayer.Cell();
        myCell.setTile(new StaticTiledMapTile(texture));

        int rotation;
        if (laserKind == Displayable.STRAIGHT_LASER) {
            rotation = switch (laserUpdate.direction()) {
                case LEFT, RIGHT -> TiledMapTileLayer.Cell.ROTATE_0;
                case UP, DOWN -> TiledMapTileLayer.Cell.ROTATE_90;
            };
        }
        else {
            if (laserUpdate.direction() == Direction.UP && laserUpdate.deflectedDirection() == Direction.LEFT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_180;
            } else if (laserUpdate.direction() == Direction.UP && laserUpdate.deflectedDirection() == Direction.RIGHT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_270;
            } else if (laserUpdate.direction() == Direction.DOWN && laserUpdate.deflectedDirection() == Direction.LEFT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_90;
            } else if (laserUpdate.direction() == Direction.DOWN && laserUpdate.deflectedDirection() == Direction.RIGHT) {
                rotation = TiledMapTileLayer.Cell.ROTATE_0;
            } else if (laserUpdate.direction() == Direction.LEFT && laserUpdate.deflectedDirection() == Direction.UP) {
                rotation = TiledMapTileLayer.Cell.ROTATE_0;
            } else if (laserUpdate.direction() == Direction.LEFT && laserUpdate.deflectedDirection() == Direction.DOWN) {
                rotation = TiledMapTileLayer.Cell.ROTATE_270;
            } else if (laserUpdate.direction() == Direction.RIGHT && laserUpdate.deflectedDirection() == Direction.UP) {
                rotation = TiledMapTileLayer.Cell.ROTATE_90;
            } else {
//            else if (laserUpdate.direction() == Direction.RIGHT && laserUpdate.getDeflectedDirection() == Direction.DOWN) {
                rotation = TiledMapTileLayer.Cell.ROTATE_180;
            }
        }

        myCell.setRotation(rotation);

        layer.setCell(MazeMapFactory.maxBoardWidth + laserUpdate.relativeX(), MazeMapFactory.maxBoardHeight + laserUpdate.relativeY(),
                myCell);
//        layer.getCell(MazeMapFactory.maxBoardWidth + )
    }
}
