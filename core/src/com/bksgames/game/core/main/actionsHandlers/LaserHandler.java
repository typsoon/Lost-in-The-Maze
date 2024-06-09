//package com.bksgames.game.core.main.actionsHandlers;
//
//import com.bksgames.game.common.moves.ActionToken;
//import com.bksgames.game.core.actions.Action;
//import com.bksgames.game.common.updates.LaserUpdate;
//import com.bksgames.game.common.utils.Direction;
//import com.bksgames.game.core.main.GameManager;
//import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
//import com.bksgames.game.core.utils.Point;
//import com.bksgames.game.core.utils.SourceOfDamage;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * {@code ActionHandler} for {@code MoveTypes.LASER}
// *
// * @author jajko
// * @author riper
// */
//public class LaserHandler extends ActionHandler {
//    @Override
//    public void handle(Action action) {
//        if (action.type() != ActionToken.LASER)
//            throw new IllegalStateException("Wrong move type!");
//
//        Point point = action.position();
//        List<Point> lineOfSight = gameManager.getBoard().getLineOfSight(point, action.direction());
//        List<Direction> line = new ArrayList<>();
//        Point prev = action.position();
//        for (Point p : lineOfSight) {
//            line.add(Direction.getDirection(prev, p));
//            prev = p;
//        }
//        gameManager.getDamageManager().dealDamage(
//                new SourceOfDamage(gameManager.getParameters(), SourceOfDamage.DamageType.LASER), lineOfSight.stream().distinct().toList()
//        );
//        point = action.position();
//        action.direction().next(point);
//        for (int i = 1; i < line.size(); i++) {
//            LaserUpdate laserUpdateToBeSent;
//            if (line.get(i - 1).equals(line.get(i))) {
//                laserUpdateToBeSent = new LaserUpdate(line.get(i-1), null, point.x, point.y);
//            } else {
//                laserUpdateToBeSent = new LaserUpdate(line.get(i-1), line.get(i), point.x, point.y);
//            }
//            gameManager.sendUpdate(UpdateHolderFactory.produceUpdateHolder(laserUpdateToBeSent));
//            line.get(i).next(point);
//        }
//    }
//
//    LaserHandler(GameManager manager) {
//        super(manager);
//    }
//}
