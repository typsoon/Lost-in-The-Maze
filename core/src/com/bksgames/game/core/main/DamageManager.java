package com.bksgames.game.core.main;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.core.utils.Vulnerable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DamageManager {
    Map<Point, Collection<Vulnerable>> receivers;
    Map<Vulnerable, Integer> respawnWaitingRoom;
    GameManager gameManager;
    public DamageManager subscribe(Vulnerable vulnerable) {
        if(!receivers.containsKey(vulnerable.getPosition())) {
            receivers.put(vulnerable.getPosition(), new ArrayList<Vulnerable>());
        }
        receivers.get(vulnerable.getPosition()).add(vulnerable); //TODO check this
        return this;
    }
    public boolean unsubscribe(Vulnerable vulnerable) {
        receivers.get(vulnerable.getPosition()).remove(vulnerable);
        return true;
    }
    public void dealDamage(SourceOfDamage sourceOfDamage, Collection<Point> range) {
        for(Point point : range) {
            if(receivers.get(point) != null)  {
                for(Vulnerable vulnerable : receivers.get(point)) {
                   UpdateHolder<Update> updateHolder = vulnerable.damage(sourceOfDamage);
                   if(updateHolder != null) {
                        gameManager.sendUpdate(updateHolder);
                        unsubscribe(vulnerable);
                        if(vulnerable.getRespawnPosition()!=null)
                            respawnWaitingRoom.put(vulnerable,5);//TODO respawn manager with different respawn queue
                        }
                   }
                }
            }
        }
    public void nextTurn(){
        for(Vulnerable vulnerable: respawnWaitingRoom.keySet()){
            respawnWaitingRoom.put(vulnerable, respawnWaitingRoom.get(vulnerable)-1);
            if(respawnWaitingRoom.get(vulnerable)==0){
                respawnWaitingRoom.remove(vulnerable);
                respawn(vulnerable);
            }
        }
    }
    private void respawn(Vulnerable vulnerable) {
        //TODO search for spot
        vulnerable.
    }
    DamageManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
