package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.Move;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.enums.MoveTypes;

import java.awt.*;
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
