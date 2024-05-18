package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.views.gameScreen.actionButtons.ActionButtonFactory;

import java.util.HashMap;
import java.util.Map;

public class MainTableFactory {
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

		arrowTable.add(factory.getButton(new Move(new Point(), MoveTypes.MOVE, Direction.UP)));

		arrowTable.row();

		arrowTable.add(factory.getButton(new Move(new Point(), MoveTypes.MOVE, Direction.LEFT)));
		arrowTable.add(factory.getButton(new Move(new Point(), MoveTypes.MOVE, Direction.DOWN)));
		arrowTable.add(factory.getButton(new Move(new Point(), MoveTypes.MOVE, Direction.RIGHT)));

		mainTable.addActor(actionsTable);

		mainTable.row();
		mainTable.addActor(arrowTable);
		arrowTable.debugAll();

		return mainTable;
	}
}
