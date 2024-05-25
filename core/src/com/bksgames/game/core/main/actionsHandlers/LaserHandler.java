package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.Move;
import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.ActionToken;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.SourceOfDamage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
/**
 * {@code ActionHandler} for {@code MoveTypes.LASER}
 * @author jajko
 * @author riper
 */
public class LaserHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != ActionToken.LASER)
            throw new IllegalStateException("Wrong move type!");

        Point point = action.position();
        Set<Point> lineOfSight = gameManager.getBoard().getLineOfSight(point, action.direction());
        List<Direction> line = new ArrayList<>();
        Point prev = action.position();
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
        point = action.position();
       action.direction().next(point);
        for(int i=1;i<line.size();i++){
            gameManager.laserUpdate(line.get(i-1),line.get(i),point);
            line.get(i).next(point);
        }
    }
    LaserHandler(GameManager manager) {super(manager);}
}
