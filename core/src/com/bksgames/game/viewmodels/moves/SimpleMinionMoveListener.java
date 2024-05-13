package com.bksgames.game.viewmodels.moves;

import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.services.PlayerService;

import java.awt.*;

public class SimpleMinionMoveListener implements MinionMoveListener {
    private final PlayerService playerService;
    private Point minionLocation;

    public SimpleMinionMoveListener(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void makeMove(IncompleteMove incompleteMove) {
        Move completeMove = new Move(minionLocation.x, minionLocation.y, incompleteMove.type(), incompleteMove.direction());

        playerService.sendMove(completeMove);
    }

    @Override
    public void setLocation(Point minionLocation) {
        this.minionLocation = minionLocation;
    }
}
