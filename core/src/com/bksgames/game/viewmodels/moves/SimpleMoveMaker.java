package com.bksgames.game.viewmodels.moves;

import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.services.PlayerService;

public class SimpleMoveMaker implements MoveMaker {
    private final PlayerService playerService;

    public SimpleMoveMaker(PlayerService playerService) {this.playerService = playerService;}

    @Override
    public void makeMove(Move move) {
        playerService.sendMove(move);
    }
}
