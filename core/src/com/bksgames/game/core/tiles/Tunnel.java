package com.bksgames.game.core.tiles;

import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.enums.Displayable;
import com.bksgames.game.enums.PlayerColor;

import java.util.ArrayList;
import java.util.Collection;


public class Tunnel implements Tile{

    Collection<Entity> entities = new ArrayList<>();

    Mirror mirror;

    @Override
    public Displayable getDisplayable() {
       if(mirror==null)
           return Displayable.TUNNEL;
       if(mirror.getOwner()== PlayerColor.RED)
           return Displayable.RED_MIRROR;
       else return Displayable.BLUE_MIRROR;
    }
    @Override
    public Tunnel getTunnel() {
        return this;
    }

    public Collection<Entity> getEntities() {
        return entities;
    }
    public void addEntity(Entity entity){
        entities.add(entity);
    }
    public void removeEntity(Entity entity){
        entities.remove(entity);
    }
}
