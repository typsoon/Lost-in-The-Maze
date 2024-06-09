package com.bksgames.game.common;

import com.bksgames.game.common.utils.ConfigManager;
import com.bksgames.game.common.utils.Configuration;
import com.bksgames.game.core.utils.Parameters;

public class ConfigManagerSingleton {
	static Parameters instance;
	private static final String path = "gameProperties.properties";

	private ConfigManagerSingleton() {
//		instance = new ConfigManager(path);
	}

	public static synchronized Parameters getInstance() {
		if (instance == null) {
			Configuration config = new ConfigManager(path);

			final int minionCount = configToInt("minionCount", config);
			final int maxMirrorsBending = configToInt("maxMirrorsBending", config);
			final int wallBuildCount = configToInt("wallBuildCount", config);
			final int doorPrice = configToInt("doorPrice", config);
			final int doorCooldown = configToInt("doorCooldown", config);
			final int doorHitPoints = configToInt("doorHitPoints", config);
			final int actionsNumber = configToInt("actionsNumber", config);
			final int minionHitPoints = configToInt("minionHitPoints", config);
			final int nexusHitPoints = configToInt("nexusHitPoints", config);
			final int mapSize = configToInt("mapSize", config);
			final int baseSize = configToInt("baseSize", config);
			final int laserDamage = configToInt("laserDamage", config);
			final int swordDamage = configToInt("swordDamage", config);


			instance = new Parameters(minionCount, maxMirrorsBending, wallBuildCount, doorPrice, doorCooldown,
					doorHitPoints, actionsNumber, minionHitPoints, nexusHitPoints, mapSize, baseSize, laserDamage, swordDamage);
		}
		return instance;
	}

	private static int configToInt(String key, Configuration config) {
		return Integer.parseInt(config.getProperty(key));
	}
}
