package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.viewmodels.moves.MoveMaker;

import java.util.Collection;

public class LegalMovesHandler {
    private final InputMultiplexer inputMultiplexer;

    private final LegalMovesObserver legalMovesObserver;

    public LegalMovesHandler(InputMultiplexer inputMultiplexer, MoveMaker moveMaker) {
        this.inputMultiplexer = inputMultiplexer;
        legalMovesObserver = new LegalMovesObserver(new LegalMoves(moveMaker));
    }

    void displayLegalMoves(Collection<Move> movesToDisplay) {
        legalMovesObserver.setLegalMoves(movesToDisplay);
    }

    void discardMoves() {inputMultiplexer.removeProcessor(legalMovesObserver);}

    private class LegalMovesObserver extends InputAdapter {
        private final LegalMoves delegate;

        void setLegalMoves(Collection<Move> legalMoves) {delegate.setLegalMoves(legalMoves);}

        LegalMovesObserver(LegalMoves delegate) {this.delegate = delegate;}

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            boolean success = delegate.touchDown(screenX, screenY, pointer, button);

            if (!success) {
                LegalMovesHandler.this.discardMoves();}

            return success;
        }

        @Override
        public boolean keyDown(int keycode) {
            boolean success = delegate.keyDown(keycode);

            if (!success) {
                LegalMovesHandler.this.discardMoves();}

            return success;
        }
    }
}

