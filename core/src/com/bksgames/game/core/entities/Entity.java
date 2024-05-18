package com.bksgames.game.core.entities;

import com.bksgames.game.core.utils.IsDisplayable;
import com.bksgames.game.core.utils.KnownPosition;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.Vulnerable;

/**
 * Objects are {@code Entity}
 * @author typsoon
 * @author riper
 * @author jajko
 */
public interface Entity extends Vulnerable, KnownPosition, IsDisplayable {
    /**
     * Spawns {@code Entity}
     * @param position Spawning location
     * @param hitPoints {@code Entity} HP after spawn
     */
    void spawn(Point position, int hitPoints);
}
