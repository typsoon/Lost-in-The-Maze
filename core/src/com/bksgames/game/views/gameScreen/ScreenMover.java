package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class ScreenMover extends InputAdapter {
    private final OrthographicCamera gameCamera;
    private static final float cameraSpeed = 400f;
//    private static final float cameraSpeed = 5f;
    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private int lastMouseX, lastMouseY;
    private boolean isDragging;

    ScreenMover(OrthographicCamera gameCamera) { this.gameCamera = gameCamera;}

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
            gameCamera.position.x -= deltaX * gameCamera.zoom;
            gameCamera.position.y += deltaY * gameCamera.zoom;
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

        // Apply velocity to the camera's position
        gameCamera.translate(velocityX, velocityY);

        // Update the camera's view
        gameCamera.update();
    }
}
