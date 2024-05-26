package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.Player;

public abstract class UpdateHolder<T extends Update> {
    T content;

    abstract public T encode(Player player);

    UpdateHolder(T content) {
        this.content = content;
    }
}
