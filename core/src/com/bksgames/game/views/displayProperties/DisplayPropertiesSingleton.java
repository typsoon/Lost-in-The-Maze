package com.bksgames.game.views.displayProperties;

import com.bksgames.game.common.utils.ConfigManager;
import com.bksgames.game.common.utils.Configuration;

public class DisplayPropertiesSingleton {
    private static DisplayProperties instance;
    private static final String path = "displayProperties.properties";

    private DisplayPropertiesSingleton() {
    }

    public static synchronized DisplayProperties getInstance() {
        if (instance == null) {
            Configuration config = new ConfigManager(path);

            final int actionButtonSize = configToInt("actionButtonSize", config, 75);
            final int arrowButtonSize = configToInt("arrowButtonSize", config, 75);
            final int slashButtonSize = configToInt("slashButtonSize", config, 30);
            final int doorButtonSize = configToInt("doorButtonSize", config, 25);
            final int laserButtonSize = configToInt("laserButtonSize", config, 25);
            final int mirrorButtonSize = configToInt("mirrorButtonSize", config, 25);
            final int swordButtonSize = configToInt("swordButtonSize", config, 25);
            final int distanceToAdjacentButton = configToInt("distanceToAdjacentButton", config, 10);
            final float cameraSpeed = configToFloat("cameraSpeed", config, 400f);
            final int laserDurationInFrames = configToInt("laserDurationInFrames", config, 200);
            final int mainTableHeight = configToInt("mainTableHeight", config, 200);
            final int offset = configToInt("offset", config, 10);
            final int actionsMenuWidth = configToInt("actionsMenuWidth", config, 350);
            final int actionsMenuHeight = configToInt("actionsMenuHeight", config, 100);
            final int bottomPadding = configToInt("bottomPadding", config, 20);
            final int leftPadding = configToInt("leftPadding", config, 20);
            final int rightPadding = configToInt("rightPadding", config, 20);
            final int endTheTurnScreenButtonTopPadding = configToInt("endTheTurnScreenButtonTopPadding", config, 20);
            final float scrollZoomMultiplier = configToFloat("scrollZoomMultiplier", config, 0.1f);
            final float adjustZoomSpeed = configToFloat("adjustZoomSpeed", config, 0.2f);
            final float backgroundMusicVolume = configToFloat("backgroundMusicVolume", config, 0.3f);

            instance = new DisplayProperties(actionButtonSize, arrowButtonSize, slashButtonSize, doorButtonSize,
                    laserButtonSize, mirrorButtonSize, swordButtonSize, distanceToAdjacentButton, cameraSpeed, laserDurationInFrames, mainTableHeight, offset,
                    actionsMenuWidth, actionsMenuHeight, bottomPadding, leftPadding, rightPadding, endTheTurnScreenButtonTopPadding,
                    scrollZoomMultiplier, adjustZoomSpeed, backgroundMusicVolume);}
        return instance;
    }

    private static int configToInt(String key, Configuration config, int defaultValue) {
        String value = config.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    @SuppressWarnings({"SameParameterValue"})
    private static float configToFloat(String key, Configuration config, float defaultValue) {
        String value = config.getProperty(key);
        return value != null ? Float.parseFloat(value) : defaultValue;
    }

//    private static <T> T configToValue(String key, Configuration config, T defaultValue) {
//        String value = config.getProperty(key);
//        return value != null ?
//    }
}
