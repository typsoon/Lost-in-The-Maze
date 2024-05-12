package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.MoveMaker;
import com.bksgames.game.views.gameScreen.actionButtons.ActionButtonFactory;

import java.awt.*;
import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    Map<MoveTypes, Table> mapping = new HashMap<>();

    private final ActionButtonFactory factory;

    LegalMoves(MoveMaker moveMaker, TextureAtlas atlas) {
        mainTable = new Table();
        mainTable.align(Align.right);
        this.addActor(mainTable);

        Table arrowTable = new Table();
        arrowTable.align(Align.bottom);

        Table actionsTable = new Table();
        actionsTable.align(Align.top);

        factory = new ActionButtonFactory(moveMaker, atlas);

        mapping.put(MoveTypes.MOVE, arrowTable);

        mapping.put(MoveTypes.DOOR, actionsTable);
        mapping.put(MoveTypes.LASER, actionsTable);
        mapping.put(MoveTypes.SWORD, actionsTable);
        mapping.put(MoveTypes.MIRROR, actionsTable);
    }

    public void setLegalMoves(Collection<Move> legalMoves, Point coordinates) {
        mainTable.clearChildren();
        Set<Table> addThese = new HashSet<>();

        legalMoves.forEach(move -> {
            Table ourTable = mapping.get(move.type());

            ourTable.add(factory.getButton(move, coordinates));

            addThese.add(ourTable);
        });

        addThese.forEach(mainTable::add);
    }
}