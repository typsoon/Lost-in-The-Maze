package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.LaserUpdate;
import com.bksgames.game.common.updates.MinionUpdate;
import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.common.updates.TileUpdate;

public class UpdateHolderFactory {
    public static TileUpdateHolder produceUpdateHolder(TileUpdate tileUpdate) {
        return new TileUpdateHolder(tileUpdate);
    }
    public static LaserUpdateHolder produceUpdateHolder(LaserUpdate laserUpdate) {
        return new LaserUpdateHolder(laserUpdate);
    }
    public static MinionUpdateHolder produceUpdateHolder(MinionUpdate minionUpdate) {
        return new MinionUpdateHolder(minionUpdate);
    }
    public static SwordUpdateHolder produceUpdateHolder (SwordUpdate swordUpdate){
        return new SwordUpdateHolder(swordUpdate);
    }
}
