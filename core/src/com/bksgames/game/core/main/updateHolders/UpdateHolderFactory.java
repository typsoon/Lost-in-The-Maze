package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.*;

public class UpdateHolderFactory {
    public static TileUpdateHolder produceUpdateHolder(TileUpdate tileUpdate) {
        return new TileUpdateHolder(tileUpdate);
    }
    public static LaserUpdateHolder produceUpdateHolder(LaserUpdate laserUpdate) {
        return new LaserUpdateHolder(laserUpdate);
    }
    public static EntityUpdateHolder produceUpdateHolder(EntityUpdate entityUpdate) {
        return new EntityUpdateHolder(entityUpdate);
    }
    public static SwordUpdateHolder produceUpdateHolder (SwordUpdate swordUpdate){
        return new SwordUpdateHolder(swordUpdate);
    }
    public static EntityStateUpdateHolder produceUpdateHolder (EntityStateUpdate entityStateUpdate){
        return new EntityStateUpdateHolder(entityStateUpdate);
    }
}
