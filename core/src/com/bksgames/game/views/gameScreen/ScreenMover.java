package com.bksgames.game.views.gameScreen;

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
    private static final float offset = 20;
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

    private void alterCameraPosition(float alterX, float alterY){
        float newX = gameCamera.position.x + alterX;
        float newY = gameCamera.position.y + alterY;

        newY = Math.max(playerViewModel.getMostDistant(Direction.DOWN) - offset, newY);
        newY = Math.min(playerViewModel.getMostDistant(Direction.UP) + offset, newY);
        newX = Math.max(playerViewModel.getMostDistant(Direction.LEFT) - offset, newX);
        newX = Math.min(playerViewModel.getMostDistant(Direction.RIGHT) + offset, newX);

        Vector3 vector3 = MazeMapFactory.project(new Vector3(newX, newY, 0));
        gameCamera.position.set(vector3);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY > 0) {
            // Zoom in when scrolling up
            gameCamera.zoom *= 1.1f; // Adjust the zoom factor as needed
        } else {
            // Zoom out when scrolling down
            gameCamera.zoom /= 1.1f; // Adjust the zoom factor as needed
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

//            gameCamera.translate(-deltaX * gameCamera.zoom, deltaY*gameCamera.zoom);
            alterCameraPosition(-deltaX * gameCamera.zoom, deltaY * gameCamera.zoom);

//            gameCamera.position.x -= deltaX * gameCamera.zoom;
//            gameCamera.position.y += deltaY * gameCamera.zoom;
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
                gameCamera.zoom += 0.02f; // Adjust zoom speed as necessary
            }
            if (zoomInPressed) {
                gameCamera.zoom -= 0.02f; // Adjust zoom speed as necessary
            }
        }

        // Apply velocity to the camera's position
         gameCamera.translate(velocityX, velocityY);
//        alterCameraPosition(velocityX, velocityY);

        // Update the camera's view
        gameCamera.update();
    }
}
