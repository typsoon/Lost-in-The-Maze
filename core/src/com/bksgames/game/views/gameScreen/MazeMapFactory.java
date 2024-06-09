package com.bksgames.game.views.gameScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.bksgames.game.core.utils.Point;

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

	public static Point unproject(Vector3 vector3){
		// Convert world coordinates to map coordinates
		int tileX = (int) (vector3.x / tilePixelSize);
		int tileY = (int) (vector3.y / tilePixelSize);

		return new Point(tileX - maxBoardWidth, tileY - maxBoardHeight);
	}

	public static Vector3 project(Vector3 vector3){
		float tileX = (vector3.x + maxBoardWidth) * tilePixelSize;
		float tileY = (vector3.y + maxBoardHeight) * tilePixelSize;

		return new Vector3(tileX, tileY, 0);
	}
}
