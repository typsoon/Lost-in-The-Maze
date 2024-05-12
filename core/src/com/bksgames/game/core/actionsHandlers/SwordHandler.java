package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.Move;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.core.utils.Vulnerable;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.MoveTypes;

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
