package com.bksgames.game.core.main;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.Respawnable;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.core.utils.Vulnerable;

import java.util.*;

public class DamageManager {
    //    Map<Point, Collection<Vulnerable>> receivers;
    final Map<Respawnable, Integer> respawnWaitingRoom;
    final GameManager gameManager;

    //    public DamageManager subscribe(Vulnerable vulnerable) {
//        if(!receivers.containsKey(vulnerable.getPosition())) {
//            receivers.put(vulnerable.getPosition(), new ArrayList<Vulnerable>());
//        }
//        receivers.get(vulnerable.getPosition()).add(vulnerable);
//        return this;
//    }
//    public boolean unsubscribe(Vulnerable vulnerable) {
//        receivers.get(vulnerable.getPosition()).remove(vulnerable);
//        return true;
//    }
    public void dealDamage(SourceOfDamage sourceOfDamage, Collection<Point> range) {
        for (Point point : range) {
            //if(receivers.containsKey(point))  {
            //Collection<Vulnerable> dead = new ArrayList<>();
            for (Vulnerable vulnerable : gameManager.getBoard().getTile(point).getVulnerable()) {
                UpdateHolder<? extends Update> updateHolder = vulnerable.damage(sourceOfDamage);
                if (updateHolder != null) {
                    gameManager.sendUpdate(updateHolder);
                    // dead.add(vulnerable);
                    if (gameManager.getBoard().getTile(point).getTunnel() != null)
                        gameManager.getBoard().getTile(point).getTunnel().removeObject(vulnerable);

                    if (vulnerable instanceof Respawnable respawnable)
                        respawnWaitingRoom.put(respawnable, 5);//TODO respawn manager with different respawn queue

                }

            }
//                    for(Vulnerable vulnerable:dead)
//                        unsubscribe(vulnerable);
        }
        gameManager.getVisionManager().playerVisionUpdate();
    }

    public void nextTurn() {
        Collection<Respawnable> alive = new HashSet<>();
        for (Respawnable respawnable : respawnWaitingRoom.keySet()) {
            respawnWaitingRoom.put(respawnable, respawnWaitingRoom.get(respawnable) - 1);
            if (respawnWaitingRoom.get(respawnable) == 0) {
                alive.add(respawnable);
                respawn(respawnable);
                //subscribe(respawnable);
            }
        }
        respawnWaitingRoom.keySet().removeAll(alive);
    }

    private void respawn(Respawnable respawnable) {
        var tile = gameManager.getBoard().getTile(respawnable.getBaseSpawnPosition()).getTunnel();
        if (tile.getTunnel() != null
                && tile.getEntities().isEmpty()) {
            gameManager.sendUpdate(respawnable.spawn());
            tile.addObject(respawnable);
        } else {
            //TODO search for spot
        }
    }

    DamageManager(GameManager gameManager) {
        this.gameManager = gameManager;
//        receivers = new HashMap<>();
        respawnWaitingRoom = new HashMap<>();
    }
}
