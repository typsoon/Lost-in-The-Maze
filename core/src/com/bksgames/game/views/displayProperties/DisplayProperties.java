package com.bksgames.game.views.displayProperties;

public record DisplayProperties(int actionButtonSize, int arrowButtonSize, int slashButtonSize, int doorButtonSize,
                                int laserButtonSize, int mirrorButtonSize, int swordButtonSize,
                                int distanceToAdjacentButton, float cameraSpeed, int laserDurationInFrames, int swordDurationInFrames, int mainTableHeight,
                                int offset, int actionsMenuWidth, int actionsMenuHeight,
                                int bottomPadding, int leftPadding, int rightPadding, int endTheTurnScreenButtonTopPadding,
                                float scrollZoomMultiplier, float adjustZoomSpeed, float backgroundMusicVolume) {
}