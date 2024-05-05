package com.bksgames.game.views;

import com.badlogic.gdx.maps.tiled.TiledMap;

// TODO: This code really needs to be revised
public class UpdaterRunnable implements Runnable {

    private final PlayerService playerService;
    private final TiledMap map;

    UpdaterRunnable(PlayerService playerService, TiledMap map) {
        this.playerService = playerService;
        this.map = map;
    }

    @Override
    public void run(){
        while (true) {
            while (playerService.hasUpdates()) {
                processUpdate(playerService.getUpdate());
                System.out.println("processed update");
            }

            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void processUpdate(Update update) {

    }
}
