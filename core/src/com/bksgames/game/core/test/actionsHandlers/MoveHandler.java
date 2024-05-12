package com.bksgames.game.core.test.actionsHandlers;

import com.bksgames.game.core.test.entities.Minion;
import com.bksgames.game.core.test.main.GameManager;
import com.bksgames.game.core.test.tiles.Tile;
import com.bksgames.game.core.test.tiles.Tunnel;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;

import java.awt.*;

public class MoveHandler extends ActionHandler {
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");

        Tile currentTile = gameManager.getBoard().getTile(action.x(), action.y());
        Tunnel currentTunnel = currentTile.getTunnel();
        Minion minion = gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getMinion(action.x(), action.y());
        currentTunnel.removeEntity(minion);

        Point point = new Point(action.x(), action.y());
        Direction.next(point, action.direction());
        Tile nextTile = gameManager.getBoard().getTile(point.x, point.y);
        Tunnel nextTunnel = nextTile.getTunnel();
        nextTunnel.addEntity(minion);

        minion.moveMinion(action.direction());
        gameManager.playerVisionUpdate(gameManager.getCurrentPlayer());
        gameManager.minionUpdate(gameManager.getCurrentPlayer(),new Point(minion.getX(),minion.getY()),action.direction(),null,MoveTypes.MOVE);
    }
    MoveHandler(GameManager manager) {
        super(manager);
    }
}
