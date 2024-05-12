package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.SourceOfDamage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LaserHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");

        Point point = new Point(action.x(), action.y());
        Set<Point> lineOfSight = gameManager.getBoard().getLineOfSight(point, action.direction());
        List<Direction> line = new ArrayList<>();
        Point prev = new Point(action.x(), action.y());
        for(Point p : lineOfSight){
            line.add(Direction.getDirection(prev, p));
            prev = p;
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
        point = new Point(action.x(), action.y());
        Direction.next(point,action.direction());
        for(int i=1;i<line.size();i++){
            gameManager.laserUpdate(line.get(i-1),line.get(i),point);
            Direction.next(point,line.get(i));
        }
    }
    LaserHandler(GameManager manager) {super(manager);}
}
