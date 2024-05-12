package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.tiles.Mirror;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.utils.Move;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.MoveTypes;

public class MirrorHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");

        Tile currentTile = gameManager.getBoard().getTile(action.x(), action.y());
        Tunnel currentTunnel = currentTile.getTunnel();

        if(currentTunnel==null){
            throw new IllegalArgumentException("To nie Tunnel");
        }

        if(action.direction()==Direction.RIGHT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.SLASH));
        if(action.direction()==Direction.LEFT)
            currentTunnel.setMirror(new Mirror(Mirror.Orientation.BACKSLASH));
    }

    MirrorHandler(GameManager manager) {
        super(manager);
    }
}
