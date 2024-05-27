package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.LaserUpdate;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.common.updates.TileUpdate;

public class UpdateHolderFactory {
    public static TileUpdateHolder produceUpdateHolder(TileUpdate tileUpdate) {
        return new TileUpdateHolder(tileUpdate);
    }
    public static LaserUpdateHolder produceUpdateHolder(LaserUpdate laserUpdate) {
        return new LaserUpdateHolder(laserUpdate);
    }
    public static MinionUpdateHolder produceUpdateHolder(EntityUpdate entityUpdate) {
        return new MinionUpdateHolder(entityUpdate);
    }
    public static SwordUpdateHolder produceUpdateHolder (SwordUpdate swordUpdate){
        return new SwordUpdateHolder(swordUpdate);
    }
}
