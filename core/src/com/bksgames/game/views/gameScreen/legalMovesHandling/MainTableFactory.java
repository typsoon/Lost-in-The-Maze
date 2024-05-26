package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.*;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers.DirectionalButtonsContainer;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers.TwoButtonedButtonContainer;

public class MainTableFactory {
	static final int mainTableHeight = 200;
	static final int arrowButtonSize = 75;
	static final int actionsMenuWidth = 350;
	static final int actionsMenuHeight = 100;
	static final int actionButtonSize = 75;

	static final int distanceToAdjacentButton = 10;
	static final int bottomPadding = 20;
	static final int leftPadding = 20;
	static final int rightPadding = 20;

	//	static Map<MoveTypes, Table> mapping = new HashMap<>();
	public static Table produce(Table arrowTable, Table actionsTable, ActionButtonFactory factory, TextureAtlas atlas){
		Table mainTable = new Table();
		mainTable.setVisible(false);

		Drawable drawable = new TextureRegionDrawable(atlas.findRegion("ActionMenuBackground"));
		actionsTable.setBackground(drawable);

		ImageButton backSlashButton = factory.getButton(new IncompleteMove(ActionToken.MIRROR, Direction.LEFT));
		ImageButton slashButton = factory.getButton(new IncompleteMove(ActionToken.MIRROR, Direction.RIGHT));

		Table mirrorTable = new TwoButtonedButtonContainer(MirrorButtonGetter.slashButtonSize, atlas.findRegion("MirrorButton"), backSlashButton, slashButton);
		actionsTable.add(mirrorTable).left();
//		actionsTable.add(MirrorButtonGetter.getMirrorButtonTable(backSlashButton, slashButton, atlas.findRegion("MirrorButton"))).left();

		ImageButton leftLaserButton = factory.getButton(new IncompleteMove(ActionToken.LASER, Direction.LEFT));
		ImageButton rightLaserButton = factory.getButton(new IncompleteMove(ActionToken.LASER, Direction.RIGHT));
		ImageButton upLaserButton = factory.getButton(new IncompleteMove(ActionToken.LASER, Direction.UP));
		ImageButton downLaserButton = factory.getButton(new IncompleteMove(ActionToken.LASER, Direction.DOWN));

		Table table = new DirectionalButtonsContainer(LaserButtonGetter.LaserArrowButtonSize, atlas.findRegion("MirrorButton"), leftLaserButton, rightLaserButton, upLaserButton, downLaserButton);
//		table = LaserButtonGetter.getLaserArrowButtonTable(leftLaserButton, rightLaserButton, upLaserButton, downLaserButton, atlas.findRegion("MirrorButton"));
//		table.debugAll();
		actionsTable.add(table).padLeft(distanceToAdjacentButton);

//		actionsTable.add(LaserButtonGetter.getLaserArrowButtonTable(leftLaserButton, rightLaserButton, upLaserButton, downLaserButton, atlas.findRegion("MirrorButton"))).padLeft(distanceToAdjacentButton);

		ImageButton leftSwordButton = factory.getButton(new IncompleteMove(ActionToken.SWORD, Direction.LEFT));
		ImageButton rightSwordButton = factory.getButton(new IncompleteMove(ActionToken.SWORD, Direction.RIGHT));
		ImageButton upSwordButton = factory.getButton(new IncompleteMove(ActionToken.SWORD, Direction.UP));
		ImageButton downSwordButton = factory.getButton(new IncompleteMove(ActionToken.SWORD, Direction.DOWN));

		Table swordTable = new DirectionalButtonsContainer(SwordButtonGetter.swordButtonSize, atlas.findRegion("MirrorButton"), leftSwordButton, rightSwordButton, upSwordButton, downSwordButton);
		actionsTable.add(swordTable).padLeft(distanceToAdjacentButton);

		ImageButton leftDoorButton = factory.getButton(new IncompleteMove(ActionToken.DOOR, Direction.LEFT));
		ImageButton rightDoorButton = factory.getButton(new IncompleteMove(ActionToken.DOOR, Direction.RIGHT));
		ImageButton upDoorButton = factory.getButton(new IncompleteMove(ActionToken.DOOR, Direction.UP));
		ImageButton downDoorButton = factory.getButton(new IncompleteMove(ActionToken.DOOR, Direction.DOWN));

		Table doorTable = new DirectionalButtonsContainer(DoorButtonGetter.DoorButtonSize, atlas.findRegion("MirrorButton"), leftDoorButton, rightDoorButton, upDoorButton, downDoorButton);
		actionsTable.add(doorTable).padLeft(distanceToAdjacentButton);

		actionsTable.add().expand();

		Actor upArrow = factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.UP));

		arrowTable.add().expand();

		addArrow(upArrow, arrowTable).padBottom(distanceToAdjacentButton).padRight(distanceToAdjacentButton);

		arrowTable.row();

		addArrow(factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.LEFT)), arrowTable).padRight(distanceToAdjacentButton);
		addArrow(factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.DOWN)), arrowTable).padRight(distanceToAdjacentButton);
		addArrow(factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.RIGHT)), arrowTable);

//		arrowTable.padRight(rightPadding);
//		arrowTable.padBottom(bottomPadding);
//		actionsTable.padLeft(leftPadding);

//		actionsTable.bottom();
		mainTable.add(actionsTable).bottom();

		mainTable.add().expand().fill();

		mainTable.add(arrowTable).bottom().right();

		mainTable.addCaptureListener(event -> arrowTable.notify(event, true));
		mainTable.addCaptureListener(event -> actionsTable.notify(event, true));

		return mainTable;
	}

	private static Cell<Actor> addArrow(Actor arrowButton, Table arrowTable) {
		Cell<Actor> answer = arrowTable.add(arrowButton);
		arrowTable.addCaptureListener(event -> {
			if (!arrowButton.isVisible())
				return false;
			return arrowButton.notify(event, true);
		});
		return answer;
	}
}
