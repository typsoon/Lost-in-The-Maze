package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.tiles.Mirror;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.core.updates.SimpleTileUpdate;

/**
 * {@code ActionHandler} for {@code MoveTypes.MIRROR}
 * @author jajko
 * @author riper
 */
public class MirrorHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != ActionToken.MIRROR)
            throw new IllegalStateException("Wrong move type!");

        Tile currentTile = gameManager.getBoard().getTile(action.position());
        Tunnel currentTunnel = currentTile.getTunnel();

        if(currentTunnel==null){
            throw new IllegalArgumentException("Not a tunnel!");
        }

        if(action.direction()== Direction.RIGHT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.SLASH,gameManager.getCurrentPlayer()));
        if(action.direction()==Direction.LEFT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.BACKSLASH,gameManager.getCurrentPlayer()));

        gameManager.sendUpdate(
                UpdateHolderFactory.produceUpdateHolder(
                        new SimpleTileUpdate(currentTunnel.getDisplayable(),true,action.position())
                )
        );
        gameManager.getVisionManager().playerVisionUpdate();
    }

    MirrorHandler(GameManager manager) {
        super(manager);
    }
}
