package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.MazeMapFactory;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.ActionButtonFactory;

import java.util.HashMap;
import java.util.Map;

public class MainTableFactory {
	static final int arrowButtonSize = 100;
	static final int distanceToAdjacentButton = 10;

	static Map<MoveTypes, Table> mapping = new HashMap<>();
	public static Table produce(Table arrowTable, Table actionsTable, ActionButtonFactory factory){

		Table mainTable = new Table();
		mainTable.setPosition((MazeMapFactory.maxBoardWidth+ 10)*MazeMapFactory.tilePixelSize, (MazeMapFactory.maxBoardHeight)*MazeMapFactory.tilePixelSize);
		mainTable.align(Align.right);
		mainTable.setVisible(false);

		arrowTable.align(Align.bottom);
		actionsTable.align(Align.top);

		mapping.put(MoveTypes.MOVE, arrowTable);

		mapping.put(MoveTypes.DOOR, actionsTable);
		mapping.put(MoveTypes.LASER, actionsTable);
		mapping.put(MoveTypes.SWORD, actionsTable);
		mapping.put(MoveTypes.MIRROR, actionsTable);

		arrowTable.setFillParent(true);

		Actor upArrow = factory.getButton(new IncompleteMove(MoveTypes.MOVE, Direction.UP));

		arrowTable.add().expand();

		addArrow(upArrow, arrowTable).padBottom(distanceToAdjacentButton).padRight(distanceToAdjacentButton);

		arrowTable.row();

		addArrow(factory.getButton(new IncompleteMove(MoveTypes.MOVE, Direction.LEFT)), arrowTable).padRight(distanceToAdjacentButton);
		addArrow(factory.getButton(new IncompleteMove(MoveTypes.MOVE, Direction.DOWN)), arrowTable).padRight(distanceToAdjacentButton);
		addArrow(factory.getButton(new IncompleteMove(MoveTypes.MOVE, Direction.RIGHT)), arrowTable);

		mainTable.addActor(actionsTable);
		mainTable.addCaptureListener(event -> arrowTable.notify(event, true));
		mainTable.addCaptureListener(event -> actionsTable.notify(event, true));

		mainTable.row();
		mainTable.addActor(arrowTable);
//		arrowTable.debugAll();

		return mainTable;
	}

	private static Cell<Actor> addArrow(Actor arrowButton, Table arrowTable) {
		Cell<Actor> answer = arrowTable.add(arrowButton);
		arrowTable.addCaptureListener(event -> {
			if (!arrowButton.isVisible())
				return false;
			return arrowButton.notify(event, true);
//			return arrowButton.fire(event, true);
//			arrowButton.fire(event);
//			return false;
		});
		return answer;
	}
}
