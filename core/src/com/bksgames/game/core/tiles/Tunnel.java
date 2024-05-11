package com.bksgames.game.core.tiles;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Collection;

public class Tunnel implements Tile{

    @Override
    public boolean isHollow() {
        return true;
    }

    Collection<Entity> entities = new ArrayList<>();

//    TODO: implement mirrors and put
//    Mirror mirror; //here

}
