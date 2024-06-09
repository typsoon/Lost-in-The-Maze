package com.bksgames.game.core.tiles;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.utils.KnownPosition;
import com.bksgames.game.core.utils.PlayerEnums;
import com.bksgames.game.core.utils.Vulnerable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representing {@code Tunnel}
 *
 * @author typsoon
 * @author riper
 * @author jajko
 */
public class Tunnel implements Tile {

    private Mirror mirror;
    private final Collection<Entity> entities = new ArrayList<>();

    //this will be removed when board will be observing all KnownPosition objects
    public void removeObject(KnownPosition object) {
        if (object instanceof Entity entity) {
            removeEntity(entity);
        }
        //in future removing doors itd
    }

    //this will be removed when board will be observing all KnownPosition objects
    public void addObject(KnownPosition object) {
        if (object instanceof Entity entity) {
            addEntity(entity);
        }
        //in future adding doors itd
    }

    @Override
    public Collection<Vulnerable> getVulnerable() {
        return new ArrayList<>(entities);
    }

    /**
     * @return {@code Collection} of {@code Entity} present in {@code Tunnel}
     */
    public Collection<Entity> getEntities() {
        return entities;
    }

    /**
     * Add {@code Entity} to {@code Tunnel}
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Remove {@code Entity} from {@code Tunnel}
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * @return {@code Mirror} if present | otherwise {@code NULL}
     */
    public Mirror getMirror() {
        return this.mirror;
    }

    /**
     * Set present {@code Mirror} in {@code Tunnel} to {@code mirror},
     * to remove {@code Mirror} give {@code mirror==NULL}
     */
    public void setMirror(Mirror mirror) {
        this.mirror = mirror;
    }

    //Tile
    @Override
    public Displayable getDisplayable() {
        if (mirror == null) {
            return Displayable.TUNNEL;
        }
        return PlayerEnums.getMirrorDisplayable(mirror.owner(), mirror.orientation());
    }

    @Override
    public Tunnel getTunnel() {
        return this;
    }
}
