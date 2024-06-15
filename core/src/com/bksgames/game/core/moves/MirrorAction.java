package com.bksgames.game.core.moves;

import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.tiles.Mirror;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.Point;

public class MirrorAction extends Action {
    public MirrorAction(Direction direction, Point minionPosition, GameManager gameManager) {
        super(direction, minionPosition, gameManager);
    }

    @Override
    public void handle() {
        Minion minion = gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getMinion(minionPosition);
        minion.makeAction(getActionToken());
        Tile currentTile = gameManager.getBoard().getTile(minionPosition);
        Tunnel currentTunnel = currentTile.getTunnel();

        if(currentTunnel==null){
            throw new IllegalArgumentException("Not a tunnel!");
        }

        if(getIncompleteMove().direction()== Direction.RIGHT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.SLASH,gameManager.getCurrentPlayer()));
        if(getIncompleteMove().direction()==Direction.LEFT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.BACKSLASH,gameManager.getCurrentPlayer()));

        gameManager.sendUpdate(
                UpdateHolderFactory.produceUpdateHolder(
                        new TileUpdate(currentTunnel.getDisplayable(),true,minionPosition.x, minionPosition.y)
                )
        );
        gameManager.getVisionManager().playerVisionUpdate();
    }

    @Override
    protected ActionToken getActionToken() {
        return ActionToken.MIRROR;
    }
}
