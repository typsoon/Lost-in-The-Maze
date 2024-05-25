package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.viewmodels.PlayerViewModel;

public class ScreenMover extends InputAdapter {
    private final OrthographicCamera gameCamera;
    private static final float cameraSpeed = 400f;
    private static final float offset = 7;
//    private static final float cameraSpeed = 5f;
    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private int lastMouseX, lastMouseY;
    private boolean isDragging;

    private boolean controlPressed;
    private boolean zoomInPressed;
    private boolean zoomOutPressed;

    PlayerViewModel playerViewModel;

    ScreenMover(OrthographicCamera gameCamera, PlayerViewModel playerViewModel) {
        this.gameCamera = gameCamera;
        this.playerViewModel = playerViewModel;
    }

    // TODO: typsoon's function

    private void alterCameraPosition(float alterX, float alterY){
        float newX = gameCamera.position.x + alterX;
        float newY = gameCamera.position.y + alterY;

        Vector3 upLeft = new Vector3( playerViewModel.getMostDistant(Direction.LEFT) - offset, playerViewModel.getMostDistant(Direction.UP) + offset, 0);
        Vector3 downRight = new Vector3(playerViewModel.getMostDistant(Direction.RIGHT) + offset, playerViewModel.getMostDistant(Direction.DOWN) - offset,  0);
        Vector3 upLeftProjected = MazeMapFactory.project(upLeft);
        Vector3 downRightProjected = MazeMapFactory.project(downRight);

        newY = Math.max(downRightProjected.y, newY);
        newY = Math.min(upLeftProjected.y, newY);
        newX = Math.min(downRightProjected.x, newX);
        newX = Math.max(upLeftProjected.x, newX);

        gameCamera.position.set(new Vector3(newX, newY, 0));
    }

    private void adjustZoom(float zoomy){
        float newZoom = gameCamera.zoom + zoomy;

        Vector3 leftUpperInCamera = new Vector3(0, 0, 0);
        Vector3 leftUpper = gameCamera.unproject(leftUpperInCamera);

        Vector3 rightLowerInCamera = new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),  0);
        Vector3 rightLower = gameCamera.unproject(rightLowerInCamera);

        Vector3 leftUpperWorld = new Vector3( playerViewModel.getMostDistant(Direction.LEFT) - offset, playerViewModel.getMostDistant(Direction.UP) + offset, 0);
        Vector3 rightLowerWorld = new Vector3(playerViewModel.getMostDistant(Direction.RIGHT) + offset, playerViewModel.getMostDistant(Direction.DOWN) - offset,  0);
        Vector3 leftUpperProjected = MazeMapFactory.project(leftUpperWorld);
        Vector3 rightLowerProjected = MazeMapFactory.project(rightLowerWorld);

        Gdx.app.log("ZoomInfo", rightLower.toString() + ' ' + leftUpper.toString() + ' ' + leftUpperProjected.toString() + ' ' + rightLowerProjected);

        float t1 = leftUpperProjected.x/leftUpper.x;
        float t2 = leftUpperProjected.y/leftUpper.y;
        float t3 = rightLowerProjected.x/rightLower.x;
        float t4 = rightLowerProjected.y/rightLower.y;

        Gdx.app.log("tInfo", String.valueOf(t1) + ' ' + String.valueOf(t2) + ' ' + String.valueOf(t3) + ' ' + String.valueOf(t4));

        float min = Math.min(t1, Math.min(t2, Math.min(t3, t4)));
        gameCamera.zoom = Math.max(min, newZoom);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY > 0) {
            // Zoom in when scrolling up
            float added = gameCamera.zoom * 0.1f;
            adjustZoom(added);
//            gameCamera.zoom *= 1.1f; // Adjust the zoom factor as needed
        } else {
            // Zoom out when scrolling down
            float added = gameCamera.zoom /11f;
            adjustZoom(added);
//            gameCamera.zoom /= 1.1f; // Adjust the zoom factor as needed
        }
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

            var tempX = gameCamera.position.x - deltaX * gameCamera.zoom;
            var tempY = gameCamera.position.y + deltaY * gameCamera.zoom;
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
//                gameCamera.zoom += 0.02f; // Adjust zoom speed as necessary
                adjustZoom(0.02f);
            }
            if (zoomInPressed) {
//                gameCamera.zoom -= 0.02f; // Adjust zoom speed as necessary
                adjustZoom(-0.02f);
            }
        }

        // Apply velocity to the camera's position
//         gameCamera.translate(velocityX, velocityY);
        alterCameraPosition(velocityX, velocityY);

        // Update the camera's view
        gameCamera.update();
    }
}
