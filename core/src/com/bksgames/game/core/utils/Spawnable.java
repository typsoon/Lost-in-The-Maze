package com.bksgames.game.core.utils;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;

public interface Spawnable {
    UpdateHolder<? extends Update> spawn(Point position);
}
