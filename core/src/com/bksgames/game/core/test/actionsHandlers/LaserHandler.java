package com.bksgames.game.core.test.actionsHandlers;

import com.bksgames.game.core.test.entities.Entity;
import com.bksgames.game.core.test.main.GameManager;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.core.test.tiles.Tile;
import com.bksgames.game.core.test.tiles.Tunnel;
import com.bksgames.game.core.test.utils.SourceOfDamage;

import java.awt.*;
import java.util.Collection;
import java.util.Set;

public class LaserHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");

        Point point = new Point(action.x(), action.y());
        Set<Point> lineofsigth = gameManager.getBoard().getLineOfSight(point, action.direction());

        for(Point p : lineofsigth){
            Tile currentTile = gameManager.getBoard().getTile(p.x, p.y);
            Tunnel currentTunnel = currentTile.getTunnel();
            if(currentTunnel==null) continue;                   // in the future reconsider apllying damage to non-hollow tiles
            Collection<Entity> entities = currentTunnel.getEntities();
            for(Entity e : entities){
                e.damage(new SourceOfDamage(
                        gameManager.getParameters(),
                        SourceOfDamage.DamageType.LASER));
            }
        }
    }
    LaserHandler(GameManager manager) {super(manager);}
}
