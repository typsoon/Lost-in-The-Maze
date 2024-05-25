package com.bksgames.game.views.gameScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MazeMapFactory {
	final static public int tilePixelSize = 50;
	final static public int maxBoardWidth = 300;
	final static public int maxBoardHeight = 300;

	public static TiledMap produce() {

		TiledMap map = new TiledMap();


		TiledMapTileLayer tunnels = new TiledMapTileLayer(3 * maxBoardWidth, 3 * maxBoardHeight, tilePixelSize, tilePixelSize);
		tunnels.setName("tunnels");

		TiledMapTileLayer wallsAndNexuses = new TiledMapTileLayer(3 * maxBoardWidth, 3 * maxBoardHeight, tilePixelSize, tilePixelSize);
		wallsAndNexuses.setName("wallsAndNexuses");

		TiledMapTileLayer mirrors = new TiledMapTileLayer(3 * maxBoardWidth, 3 * maxBoardHeight, tilePixelSize, tilePixelSize);
		mirrors.setName("mirrors");

		TiledMapTileLayer minions = new TiledMapTileLayer(3 * maxBoardWidth, 3 * maxBoardHeight, tilePixelSize, tilePixelSize);
		minions.setName("minions");

		TiledMapTileLayer laser = new TiledMapTileLayer(3 * maxBoardWidth, 3 * maxBoardHeight, tilePixelSize, tilePixelSize);
		laser.setName("laser");

		map.getLayers().add(tunnels);
		map.getLayers().add(wallsAndNexuses);
		map.getLayers().add(mirrors);
		map.getLayers().add(minions);
		map.getLayers().add(laser);

		return map;
	}
}
