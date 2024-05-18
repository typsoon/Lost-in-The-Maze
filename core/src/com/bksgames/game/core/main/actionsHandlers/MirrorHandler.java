package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.tiles.Mirror;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
/**
 * {@code ActionHandler} for {@code MoveTypes.MIRROR}
 * @author jajko
 * @author riper
 */
public class MirrorHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MIRROR)
            throw new IllegalStateException("Wrong move type!");

        Tile currentTile = gameManager.getBoard().getTile(action.position());
        Tunnel currentTunnel = currentTile.getTunnel();

        if(currentTunnel==null){
            throw new IllegalArgumentException("To nie Tunnel");
        }

        if(action.direction()== Direction.RIGHT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.SLASH,gameManager.getCurrentPlayer()));
        if(action.direction()==Direction.LEFT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.BACKSLASH,gameManager.getCurrentPlayer()));
    }

    MirrorHandler(GameManager manager) {
        super(manager);
    }
}
