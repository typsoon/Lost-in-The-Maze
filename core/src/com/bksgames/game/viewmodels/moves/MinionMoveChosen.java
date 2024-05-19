package com.bksgames.game.viewmodels.moves;

import com.badlogic.gdx.scenes.scene2d.Event;

public class MinionMoveChosen extends Event {
    private final IncompleteMove incompleteMove;

    public MinionMoveChosen(IncompleteMove incompleteMove) {
        this.incompleteMove = incompleteMove;
    }

    public IncompleteMove getIncompleteMove() {return incompleteMove;}
}
