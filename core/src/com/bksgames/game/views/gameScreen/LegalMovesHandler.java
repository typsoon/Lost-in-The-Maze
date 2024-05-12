package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.viewmodels.moves.MoveMaker;

import java.awt.*;
import java.util.Collection;

public class LegalMovesHandler {
    private final InputMultiplexer inputMultiplexer;

    private final LegalMovesObserver legalMovesObserver;

    public LegalMovesHandler(InputMultiplexer inputMultiplexer, MoveMaker moveMaker, TextureAtlas atlas) {
        this.inputMultiplexer = inputMultiplexer;
        legalMovesObserver = new LegalMovesObserver(new LegalMoves(moveMaker, atlas));
    }

    void displayLegalMoves(Collection<Move> movesToDisplay, Point coordinates) {
        legalMovesObserver.setLegalMoves(movesToDisplay, coordinates);
    }

    void discardMoves() {inputMultiplexer.removeProcessor(legalMovesObserver);}

    private class LegalMovesObserver extends InputAdapter {
        private final LegalMoves delegate;

        void setLegalMoves(Collection<Move> legalMoves, Point coordinates) {delegate.setLegalMoves(legalMoves, coordinates);}

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

