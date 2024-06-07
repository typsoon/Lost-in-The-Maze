package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.utils.SourceOfDamage;

import java.util.List;

/**
 * {@code ActionHandler} for {@code MoveTypes.SWORD}
 *
 * @author jajko
 * @author riper
 */
public class SwordHandler extends ActionHandler {
    @Override
    public void handle(Move action) {
        if (action.type() != ActionToken.SWORD)
            throw new IllegalStateException("Wrong move type!");

        gameManager.getDamageManager().dealDamage(
                new SourceOfDamage(gameManager.getParameters(), SourceOfDamage.DamageType.SWORD),
                List.of(action.direction().getNext(action.position()))
        );
        gameManager.sendUpdate(
                UpdateHolderFactory.produceUpdateHolder(
                        new SwordUpdate(action.direction(), action.position().x, action.position().y)
                )
        );
    }

    SwordHandler(GameManager manager) {
        super(manager);
    }
}
