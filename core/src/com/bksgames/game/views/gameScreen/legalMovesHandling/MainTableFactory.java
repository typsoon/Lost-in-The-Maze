package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.globalClasses.enums.ActionToken;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.ActionButtonFactory;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.MirrorButtonGetter;


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

		Actor backSlashButton = factory.getButton(new IncompleteMove(ActionToken.MIRROR, Direction.LEFT));
		Actor slashButton = factory.getButton(new IncompleteMove(ActionToken.MIRROR, Direction.RIGHT));

		if (!(backSlashButton instanceof ImageButton backSlash) || !(slashButton instanceof ImageButton slash)) {
			throw new IllegalStateException("Mirror buttons should be instances of ImageButton");
		}

		actionsTable.add(MirrorButtonGetter.getMirrorButtonTable(backSlash, slash, atlas.findRegion("MirrorButton"))).left();

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
