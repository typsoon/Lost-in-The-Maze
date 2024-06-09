package com.bksgames.game.core.moves;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.SourceOfDamage;

import java.util.List;

public class SwordAction extends Action {
    public SwordAction(Direction direction, Point minionPosition, GameManager gameManager) {
        super(direction, minionPosition, gameManager);
    }

    @Override
    public void handle() {
        gameManager.getDamageManager().dealDamage(
                new SourceOfDamage(gameManager.getParameters(), SourceOfDamage.DamageType.SWORD),
                List.of(getIncompleteMove().direction().getNext(minionPosition))
        );
        gameManager.sendUpdate(
                UpdateHolderFactory.produceUpdateHolder(
                        new SwordUpdate(getIncompleteMove().direction(), minionPosition.x, minionPosition.y)
                )
        );
    }
    @Override
    protected ActionToken getActionToken() {
        return ActionToken.SWORD;
    }
}
