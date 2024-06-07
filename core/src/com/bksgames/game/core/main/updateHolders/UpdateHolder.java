package com.bksgames.game.core.main.updateHolders;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.Player;
import com.bksgames.game.core.utils.Point;

public abstract class UpdateHolder<T extends Update> {
    T content;

    abstract public T encode(Player player);

    protected final Point encodedPosition(Player player) {
        return new Point(content.relativeX() - player.getMainNexus().x, content.relativeY() - player.getMainNexus().y);
    }

    protected final int encodedX(Player player) {return encodedPosition(player).x;}

    protected final int encodedY(Player player) {return encodedPosition(player).y;}

    UpdateHolder(T content) {
        this.content = content;
    }
}
