//package com.bksgames.game.core.main.actionsHandlers;
//
//import com.bksgames.game.common.updates.EntityUpdate;
//import com.bksgames.game.common.updates.TileUpdate;
//import com.bksgames.game.core.entities.Minion;
//import com.bksgames.game.core.main.GameManager;
//import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
//import com.bksgames.game.core.tiles.Tile;
//import com.bksgames.game.core.tiles.Tunnel;
//import com.bksgames.game.core.utils.Point;
//import com.bksgames.game.core.actions.Action;
//import com.bksgames.game.common.moves.ActionToken;
///**
// * {@code ActionHandler} for {@code MoveTypes.MOVE}
// * @author jajko
// * @author riper
// */
//public class MoveHandler extends ActionHandler {
//    @Override
//    public void handle(Action action) {
//        if(action.type() != ActionToken.MOVE)
//            throw new IllegalStateException("Wrong move type!");
//
//        Tile currentTile = gameManager.getBoard().getTile(action.position());
//        Tunnel currentTunnel = currentTile.getTunnel();
//        Minion minion = gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getMinion(action.position());
//        Point lastPos = new Point(minion.getX(), minion.getY());
//        currentTunnel.removeEntity(minion);
//
//        Point point = action.position();
//        action.direction().next(point);
//        Tile nextTile = gameManager.getBoard().getTile(point.x, point.y);
//        Tunnel nextTunnel = nextTile.getTunnel();
//        nextTunnel.addEntity(minion);
//
//        minion.moveMinion(action.direction());
//        gameManager.getVisionManager().playerVisionUpdate(gameManager.getCurrentPlayer());
//        gameManager.sendUpdate(
//                UpdateHolderFactory.produceUpdateHolder(
//                        new EntityUpdate(action.direction(), minion.getDisplayable(), lastPos.x, lastPos.y)
//                )
//        );
//        gameManager.sendUpdate(
//                UpdateHolderFactory.produceUpdateHolder(
//                        new TileUpdate(minion.getDisplayable(),true,point.x, point.y)
//                )
//        );
//    }
//    MoveHandler(GameManager manager) {
//        super(manager);
//    }
//}
