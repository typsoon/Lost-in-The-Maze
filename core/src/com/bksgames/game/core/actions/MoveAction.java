//package com.bksgames.game.core.actions;
//
//import com.bksgames.game.common.moves.ActionToken;
//import com.bksgames.game.common.updates.EntityUpdate;
//import com.bksgames.game.common.updates.TileUpdate;
//import com.bksgames.game.common.utils.Direction;
//import com.bksgames.game.core.entities.Minion;
//import com.bksgames.game.core.main.GameManager;
//import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
//import com.bksgames.game.core.tiles.Tile;
//import com.bksgames.game.core.tiles.Tunnel;
//import com.bksgames.game.core.utils.Point;
//
//public class MoveAction extends Action {
//
//	public MoveAction(Direction direction, Point minionPosition, GameManager gameManager) {
//		super(direction, minionPosition, gameManager);
//	}
//
//	@Override
//	protected void handle() {
//		Tile currentTile = gameManager.getBoard().getTile(minionPosition);
//		Tunnel currentTunnel = currentTile.getTunnel();
//		Minion minion = gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getMinion(minionPosition);
//		Point lastPos = new Point(minion.getX(), minion.getY());
//		currentTunnel.removeEntity(minion);
//
//		getIncompleteMove().direction().next(minionPosition);
//		Tile nextTile = gameManager.getBoard().getTile(minionPosition.x, minionPosition.y);
//		Tunnel nextTunnel = nextTile.getTunnel();
//		nextTunnel.addEntity(minion);
//
//		minion.moveMinion(getIncompleteMove().direction());
//		gameManager.getVisionManager().playerVisionUpdate(gameManager.getCurrentPlayer());
//		gameManager.sendUpdate(
//				UpdateHolderFactory.produceUpdateHolder(
//						new EntityUpdate(getIncompleteMove().direction(), minion.getDisplayable(), lastPos.x, lastPos.y)
//				)
//		);
//		gameManager.sendUpdate(
//				UpdateHolderFactory.produceUpdateHolder(
//						new TileUpdate(minion.getDisplayable(),true,minionPosition.x, minionPosition.y)
//				)
//		);
//	}
//
//	@Override
//	protected ActionToken getActionToken() {
//		return ActionToken.MOVE;
//	}
//}
