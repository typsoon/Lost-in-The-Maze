package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.moves.IncompleteMove;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.*;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers.ArrowButtonContainer;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers.DirectionalButtonsContainer;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers.TwoButtonedButtonContainer;

public class MainTableFactory {
	static final int actionsMenuWidth = DisplayPropertiesSingleton.getInstance().actionsMenuWidth();
	static final int actionsMenuHeight = DisplayPropertiesSingleton.getInstance().actionsMenuHeight();

	static final int distanceToAdjacentButton = DisplayPropertiesSingleton.getInstance().distanceToAdjacentButton();

	//	static Map<MoveTypes, Table> mapping = new HashMap<>();
	public static Table produce(ActionButtonFactory factory, TextureAtlas atlas){

		Table mainTable = new Table();
		mainTable.setVisible(false);

		final Table actionsTable = new Table();
		actionsTable.setBounds(0, 0, actionsMenuWidth, actionsMenuHeight);

		Drawable drawable = new TextureRegionDrawable(atlas.findRegion("ActionMenuBackground"));
		actionsTable.setBackground(drawable);

		ImageButton backSlashButton = factory.getButton(new IncompleteMove(ActionToken.MIRROR, Direction.LEFT));
		ImageButton slashButton = factory.getButton(new IncompleteMove(ActionToken.MIRROR, Direction.RIGHT));

		Table mirrorTable = new TwoButtonedButtonContainer(atlas.findRegion("MirrorButton"), backSlashButton, slashButton);
		actionsTable.add(mirrorTable).left();
//		actionsTable.add(MirrorButtonGetter.getMirrorButtonTable(backSlashButton, slashButton, atlas.findRegion("MirrorButton"))).left();

		Table laserTable = produceBasicDirectionalContainer(atlas.findRegion("MirrorButton"), factory, ActionToken.LASER);
		actionsTable.add(laserTable).padLeft(distanceToAdjacentButton);

		Table swordTable = produceBasicDirectionalContainer(atlas.findRegion("MirrorButton"), factory, ActionToken.SWORD);
		actionsTable.add(swordTable).padLeft(distanceToAdjacentButton);

		Table doorTable = produceBasicDirectionalContainer(atlas.findRegion("MirrorButton"), factory, ActionToken.DOOR);
		actionsTable.add(doorTable).padLeft(distanceToAdjacentButton);

		actionsTable.add().expand();

		ImageButton leftArrow = factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.LEFT));
		ImageButton rightArrow = factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.RIGHT));
		ImageButton upArrow = factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.UP));
		ImageButton downArrow = factory.getButton(new IncompleteMove(ActionToken.MOVE, Direction.DOWN));

		final Table arrowTable = new ArrowButtonContainer(null, leftArrow, rightArrow, upArrow, downArrow);

		addToMainTable(mainTable, actionsTable).bottom();

		mainTable.add().expand().fill();

		addToMainTable(mainTable, arrowTable).bottom().right();

		mainTable.addCaptureListener(event -> arrowTable.notify(event, true));
		mainTable.addCaptureListener(event -> actionsTable.notify(event, true));

		return mainTable;
	}

	private static Cell<Actor> addToMainTable(Table mainTable, Actor actor) {
		Cell<Actor> actorCell = mainTable.add(actor);
//		actorCell.maxSize(actorCell.getMinWidth(), actorCell.getMinHeight());
//		actorCell.maxSize(actorCell.getActor().getWidth(), actorCell.getActor().getHeight());
		actorCell.maxSize(actorCell.getActor().getWidth(), actorCell.getActor().getHeight());
//		actorCell.size(actorCell.getActorWidth(), actorCell.getActorHeight());
		return actorCell;
	}

	private static Table produceBasicDirectionalContainer(TextureRegion region, ActionButtonFactory factory, ActionToken actionToken) {
		ImageButton leftButton = factory.getButton(new IncompleteMove(actionToken, Direction.LEFT));
		ImageButton rightButton = factory.getButton(new IncompleteMove(actionToken, Direction.RIGHT));
		ImageButton upButton = factory.getButton(new IncompleteMove(actionToken, Direction.UP));
		ImageButton downButton = factory.getButton(new IncompleteMove(actionToken, Direction.DOWN));

		return new DirectionalButtonsContainer(region, leftButton, rightButton, upButton, downButton);
	}
}
