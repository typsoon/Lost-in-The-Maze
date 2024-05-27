package com.bksgames.game.common;

import com.bksgames.game.common.utils.ConfigManager;
import com.bksgames.game.common.utils.Configuration;
import com.bksgames.game.core.utils.Parameters;

public class ConfigManagerSingleton {
	static Parameters instance;
	private static final String path = "assets/gameProperties.properties";

	private ConfigManagerSingleton() {
//		instance = new ConfigManager(path);
	}

	public static synchronized Parameters getInstance() {
		if (instance == null) {
			Configuration config = new ConfigManager(path);

			int minionCount = configToInt("minionCount", config);
			int maxMirrorsBending = configToInt("maxMirrorsBending", config);
			int wallBuildCount = configToInt("wallBuildCount", config);
			int doorPrice = configToInt("doorPrice", config);
			int doorCooldown = configToInt("doorCooldown", config);
			int doorHitPoints = configToInt("doorHitPoints", config);
			int actionsNumber = configToInt("actionsNumber", config);
			int minionHitPoints = configToInt("minionHitPoints", config);
			int nexusHitPoints = configToInt("nexusHitPoints", config);
			int mapSize = configToInt("mapSize", config);
			int baseSize = configToInt("baseSize", config);
//        TODO: think about these values
			int laserDamage = configToInt("laserDamage", config);
			int swordDamage = configToInt("swordDamage", config);


			instance = new Parameters(minionCount, maxMirrorsBending, wallBuildCount, doorPrice, doorCooldown,
					doorHitPoints, actionsNumber, minionHitPoints, nexusHitPoints, mapSize, baseSize, laserDamage, swordDamage);
		}
		return instance;
	}

	private static int configToInt(String key, Configuration config) {
		return Integer.parseInt(config.getProperty(key));
	}
}
