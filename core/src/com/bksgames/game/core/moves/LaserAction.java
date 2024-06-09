package com.bksgames.game.core.moves;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.LaserUpdate;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.SourceOfDamage;

import java.util.ArrayList;
import java.util.List;

public class LaserAction extends Action {
    public LaserAction(Direction direction, Point minionPosition, GameManager gameManager) {
        super(direction, minionPosition, gameManager);
    }

    @Override
    public void handle() {
        List<Point> lineOfSight = gameManager.getBoard().getLineOfSight(minionPosition, getIncompleteMove().direction());
        List<Direction> line = new ArrayList<>();
        Point prev = minionPosition;
        for (Point p : lineOfSight) {
            line.add(Direction.getDirection(prev, p));
            prev = p;
        }
        gameManager.getDamageManager().dealDamage(
                new SourceOfDamage(gameManager.getParameters(), SourceOfDamage.DamageType.LASER), lineOfSight.stream().distinct().toList()
        );
        getIncompleteMove().direction().next(minionPosition);
        for (int i = 1; i < line.size(); i++) {
            LaserUpdate laserUpdateToBeSent;
            if (line.get(i - 1).equals(line.get(i))) {
                laserUpdateToBeSent = new LaserUpdate(line.get(i-1), null, minionPosition.x, minionPosition.y);
            } else {
                laserUpdateToBeSent = new LaserUpdate(line.get(i-1), line.get(i), minionPosition.x, minionPosition.y);
            }
            gameManager.sendUpdate(UpdateHolderFactory.produceUpdateHolder(laserUpdateToBeSent));
            line.get(i).next(minionPosition);
        }
    }

    @Override
    protected ActionToken getActionToken() {
        return ActionToken.LASER;
    }
}
