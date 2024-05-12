package com.bksgames.game.core.test.actionsHandlers;

import com.bksgames.game.core.test.entities.Entity;
import com.bksgames.game.core.test.main.GameManager;
import com.bksgames.game.core.test.tiles.Tile;
import com.bksgames.game.core.test.tiles.Tunnel;
import com.bksgames.game.core.test.utils.SourceOfDamage;
import com.bksgames.game.core.test.utils.Vulnerable;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;

import java.awt.*;

public class SwordHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");

        Point point = new Point(action.x(), action.y());
        Direction.next(point, action.direction());
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
    }
    SwordHandler(GameManager manager) {
        super(manager);
    }
}
