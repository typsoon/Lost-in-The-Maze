package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.scenes.scene2d.Event;

public class ChosenMove extends Event {
    private final IncompleteMove incompleteMove;

    public ChosenMove(IncompleteMove incompleteMove) {
        this.incompleteMove = incompleteMove;
    }

    public IncompleteMove getIncompleteMove() {
        return incompleteMove;
    }
}
