package com.bksgames.game.core.tiles;

import com.bksgames.game.core.Mirror;
import com.bksgames.game.core.Entity;
import java.util.ArrayList;
import java.util.Collection;


public class Tunnel implements Tile{

    Collection<Entity> entities = new ArrayList<>();

    Mirror mirror;

    @Override
    public Tunnel getTunnel() {
        return this;
    }
}
