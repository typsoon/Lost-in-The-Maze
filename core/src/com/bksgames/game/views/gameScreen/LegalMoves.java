package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.MoveMaker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LegalMoves extends Stage {
    private final MoveMaker moveMaker;
    Table mainTable;
    Table arrowTable;
    Table actionsTable;
    Map<MoveTypes, Table> mapping = new HashMap<>();

    Table arrows;

    LegalMoves(MoveMaker moveMaker) {
        this.moveMaker = moveMaker;

        arrowTable = new Table();
        arrowTable.align(Align.bottom);

        actionsTable = new Table();
        actionsTable.align(Align.top);

        mapping.put(MoveTypes.MOVE, arrowTable);

        mapping.put(MoveTypes.DOOR, actionsTable);
        mapping.put(MoveTypes.LASER, actionsTable);
        mapping.put(MoveTypes.SWORD, actionsTable);
        mapping.put(MoveTypes.MIRROR, actionsTable);
    }

    public void setLegalMoves(Collection<Move> legalMoves) {
        this.addActor(mainTable);
        mainTable.align(Align.right);

        legalMoves.forEach(this::displayMove);
    }

    private void displayMove(Move move) {
        switch (move.type()) {
            case MOVE -> {

            }
        }
    }


}

