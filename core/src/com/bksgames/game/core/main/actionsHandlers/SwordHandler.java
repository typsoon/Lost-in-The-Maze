package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.common.MinionEvent;
import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.updates.SimpleMinionUpdate;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.core.utils.Vulnerable;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.moves.ActionToken;

/**
 * {@code ActionHandler} for {@code MoveTypes.SWORD}
 * @author jajko
 * @author riper
 */
public class SwordHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != ActionToken.SWORD)
            throw new IllegalStateException("Wrong move type!");

        Point point = action.position();
        action.direction().next(point);
        Tile currentTile = gameManager.getBoard().getTile(point.x, point.y);
        Tunnel currentTunnel = currentTile.getTunnel();
        if(currentTunnel==null){
            if(currentTile instanceof Vulnerable vul)
                vul.damage(new SourceOfDamage(
                    gameManager.getParameters(),
                    SourceOfDamage.DamageType.SWORD));
        }
		else{
		    for(Entity e : currentTunnel.getEntities()) {
                if(e!=null){
                    e.damage(new SourceOfDamage(
                            gameManager.getParameters(),
                            SourceOfDamage.DamageType.SWORD));
                }
            }
        }
        gameManager.sendUpdate(
                UpdateHolderFactory.produceUpdateHolder(
                        new SimpleMinionUpdate(action.direction(),null, null,ActionToken.SWORD,action.position())
                )
        );
    }
    SwordHandler(GameManager manager) {
        super(manager);
    }
}
