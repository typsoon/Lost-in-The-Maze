package com.bksgames.game.core.moves;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.Point;

public class MoveAction extends Action {
    public MoveAction(Direction direction, Point minionPosition, GameManager gameManager) {
        super(direction, minionPosition, gameManager);
    }

    @Override
    public void handle() {
        Point point;
        Tile currentTile = gameManager.getBoard().getTile(minionPosition);
        Tunnel currentTunnel = currentTile.getTunnel();

        Minion minion = gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getMinion(minionPosition);
        minion.makeAction(getActionToken());
        Point lastPos = new Point(minion.getX(), minion.getY());
        currentTunnel.removeEntity(minion);

        point = getIncompleteMove().direction().getNext(minionPosition);
        Tile nextTile = gameManager.getBoard().getTile(point.x, point.y);
        Tunnel nextTunnel = nextTile.getTunnel();
        nextTunnel.addEntity(minion);

        minion.moveMinion(getIncompleteMove().direction());
        gameManager.getVisionManager().playerVisionUpdate(gameManager.getCurrentPlayer());
        gameManager.sendUpdate(
                UpdateHolderFactory.produceUpdateHolder(
                        new EntityUpdate(getIncompleteMove().direction(), minion.getDisplayable(), lastPos.x, lastPos.y)
                )
        );
    }

    @Override
    protected ActionToken getActionToken() {
        return ActionToken.MOVE;
    }
}
