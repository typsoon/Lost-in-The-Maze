package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.displayProperties.DisplayProperties;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;

public class ScreenMover extends InputAdapter {
    private final OrthographicCamera gameCamera;
    private static final float cameraSpeed = DisplayPropertiesSingleton.getInstance().offset();
    private static final float offset = DisplayPropertiesSingleton.getInstance().offset();
    private static final float scrollZoomMultiplier = DisplayPropertiesSingleton.getInstance().scrollZoomMultiplier();

    private static final float invertedZoomMultiplier = 1/(1+1/scrollZoomMultiplier);

    private static final float adjustZoomSpeed = DisplayPropertiesSingleton.getInstance().adjustZoomSpeed();

    //    private static final float cameraSpeed = 5f;
    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private int lastMouseX, lastMouseY;
    private boolean isDragging;

    private boolean controlPressed;
    private boolean zoomInPressed;
    private boolean zoomOutPressed;

    final PlayerViewModel playerViewModel;

    ScreenMover(OrthographicCamera gameCamera, PlayerViewModel playerViewModel) {
        this.gameCamera = gameCamera;
        this.playerViewModel = playerViewModel;
    }

// TODO: typsoon's function

//    public void getAcceptableBounds(Vector3 acceptableLeftUpper, Vector3 acceptableRightLower) {
//        Vector3 leftUpperGameCoords = new Vector3( playerViewModel.getMostDistant(Direction.LEFT) - offset, playerViewModel.getMostDistant(Direction.UP) + offset, 0);
//        Vector3 rightLowerGameCoords = new Vector3(playerViewModel.getMostDistant(Direction.RIGHT) + offset, playerViewModel.getMostDistant(Direction.DOWN) - offset,  0);
//        acceptableLeftUpper = MazeMapFactory.project(leftUpperGameCoords);
//        acceptableRightLower = MazeMapFactory.project(rightLowerGameCoords);
//    }

    private void alterCameraPosition(float alterX, float alterY){
//        float newX = gameCamera.position.x + alterX;
//        float newY = gameCamera.position.y + alterY;

        Vector3 leftUpper = gameCamera.unproject(new Vector3(0, 0, 0));
        Vector3 rightLower = gameCamera.unproject(new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),  0));

//        Gdx.app.log("Width",  String.valueOf(Gdx.graphics.getWidth()) + ' ' + Gdx.graphics.getHeight());

        Vector3 leftUpperGameCoords = new Vector3( playerViewModel.getMostDistant(Direction.LEFT) - offset, playerViewModel.getMostDistant(Direction.UP) + offset, 0);
        Vector3 rightLowerGameCoords = new Vector3(playerViewModel.getMostDistant(Direction.RIGHT) + offset, playerViewModel.getMostDistant(Direction.DOWN) - offset,  0);
        Vector3 acceptableLeftUpper = MazeMapFactory.project(leftUpperGameCoords);
        Vector3 acceptableRightLower = MazeMapFactory.project(rightLowerGameCoords);

//        Vector3 acceptableLeftUpperTemp = new Vector3(), acceptableRightLowerTemp = new Vector3();
//        setAcceptableBounds(acceptableLeftUpperTemp, acceptableRightLowerTemp);

        float maxAcceptableXShift = acceptableRightLower.x - rightLower.x;
        float minAcceptableXShift = acceptableLeftUpper.x - leftUpper.x;
        float minAcceptableYShift = acceptableRightLower.y - rightLower.y;
        float maxAcceptableYShift = acceptableLeftUpper.y - leftUpper.y;

        alterY = Math.max(minAcceptableYShift, alterY);
        alterY = Math.min(maxAcceptableYShift, alterY);

        alterX = Math.min(maxAcceptableXShift, alterX);
        alterX = Math.max(minAcceptableXShift, alterX);

        gameCamera.translate(alterX, alterY);
//        gameCamera.position.set(new Vector3(newX, newY, 0));
    }

    private void adjustZoom(float zoomy) {
        Vector3 leftUpper = gameCamera.unproject(new Vector3(0, 0, 0));
        Vector3 rightLower = gameCamera.unproject(new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),  0));

        Vector3 displayedRectangleCenter = new Vector3((leftUpper.x+rightLower.x)/2, (leftUpper.y+rightLower.y)/2, (leftUpper.z+rightLower.z)/2);

        Vector3 leftUpperGameCoords = new Vector3( playerViewModel.getMostDistant(Direction.LEFT) - offset, playerViewModel.getMostDistant(Direction.UP) + offset, 0);
        Vector3 rightLowerGameCoords = new Vector3(playerViewModel.getMostDistant(Direction.RIGHT) + offset, playerViewModel.getMostDistant(Direction.DOWN) - offset,  0);
        Vector3 acceptableLeftUpper = MazeMapFactory.project(leftUpperGameCoords);
        Vector3 acceptableRightLower = MazeMapFactory.project(rightLowerGameCoords);

//        Gdx.app.log("ZoomInfo", rightLower.toString() + ' ' + leftUpper + ' ' + acceptableLeftUpper + ' ' + acceptableRightLower);

//        TODO: remove magic values from here
        float min = getMin(displayedRectangleCenter, acceptableLeftUpper, acceptableRightLower);
        float newZoom = Math.min(gameCamera.zoom + zoomy, min);
        newZoom = Math.max(newZoom, 0.1f);
        gameCamera.zoom = newZoom;
    }

    private static float getMin(Vector3 displayedRectangleCenter, Vector3 acceptableLeftUpper, Vector3 acceptableRightLower) {
        final float screenWidth = Gdx.graphics.getWidth();
        final float screenHeight = Gdx.graphics.getHeight();

        float t1 = (displayedRectangleCenter.x - acceptableLeftUpper.x) * 2 / screenWidth;
        float t2 = (acceptableLeftUpper.y - displayedRectangleCenter.y) * 2 / screenHeight;

        float t3 = (acceptableRightLower.x - displayedRectangleCenter.x) * 2 / screenWidth;
        float t4 = (displayedRectangleCenter.y - acceptableRightLower.y) * 2 / screenHeight;

//        Gdx.app.log("tInfo", String.valueOf(t1) + ' ' + String.valueOf(t2) + ' ' + String.valueOf(t3) + ' ' + String.valueOf(t4));
//
//        Gdx.app.log("newZoom", String.valueOf(newZoom));

        return Math.min(t1, Math.min(t2, Math.min(t3, t4)));
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        float added;
        if (amountY > 0) {
            // Zoom in when scrolling up
            added = gameCamera.zoom * scrollZoomMultiplier;
        } else {
            // Zoom out when scrolling down
            added = -gameCamera.zoom * invertedZoomMultiplier;
        }
        adjustZoom(added);
        return true; // Indicate that the input event was handled
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastMouseX = screenX;
        lastMouseY = screenY;
        isDragging = true;
        return false; // Indicate that the input event was handled
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isDragging = false;
        return true; // Indicate that the input event was handled
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isDragging) {
            int deltaX = screenX - lastMouseX;
            int deltaY = screenY - lastMouseY;

//            gameCamera.translate(-deltaX * gameCamera.zoom, deltaY*gameCamera.zoom);
            alterCameraPosition(-deltaX * gameCamera.zoom, deltaY * gameCamera.zoom);

            lastMouseX = screenX;
            lastMouseY = screenY;
        }
        return true; // Indicate that the input event was handled
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                leftPressed = true;
                return true;
            case Input.Keys.RIGHT:
                rightPressed = true;
                return true;
            case Input.Keys.UP:
                upPressed = true;
                return true;
            case Input.Keys.DOWN:
                downPressed = true;
                return true;
        }

        if (keycode == Input.Keys.CONTROL_LEFT) {
            controlPressed = true;
        }
        if (controlPressed) {
            if (keycode == Input.Keys.MINUS) {
                zoomOutPressed = true;
            } else if (keycode == Input.Keys.EQUALS || keycode == Input.Keys.PLUS) {
                zoomInPressed = true;
            }
        }

        return false; // Indicate that the input event was handled
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                leftPressed = false;
                break;
            case Input.Keys.RIGHT:
                rightPressed = false;
                break;
            case Input.Keys.UP:
                upPressed = false;
                break;
            case Input.Keys.DOWN:
                downPressed = false;
                break;
        }

        if (keycode == Input.Keys.CONTROL_LEFT) {
            controlPressed = false;
        }
        if (keycode == Input.Keys.MINUS) {
            zoomOutPressed = false;
        } else if (keycode == Input.Keys.EQUALS || keycode == Input.Keys.PLUS) {
            zoomInPressed = false;
        }

        return true; // Indicate that the input event was handled
    }

    public synchronized void update(float delta) {
        float velocityX = 0;
        float velocityY = 0;

        if (leftPressed) {
            velocityX -= cameraSpeed * delta;
        }
        if (rightPressed) {
            velocityX += cameraSpeed * delta;
        }
        if (upPressed) {
            velocityY += cameraSpeed * delta;
        }
        if (downPressed) {
            velocityY -= cameraSpeed * delta;
        }

        if (controlPressed) {
            if (zoomOutPressed) {
                adjustZoom(adjustZoomSpeed);
            }
            if (zoomInPressed) {
                adjustZoom(-adjustZoomSpeed);
            }
        }

        // Apply velocity to the camera's position
//         gameCamera.translate(velocityX, velocityY);
        alterCameraPosition(velocityX, velocityY);

        // Update the camera's view
        gameCamera.update();
    }
}
