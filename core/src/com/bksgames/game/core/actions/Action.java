//package com.bksgames.game.core.actions;
//
//import com.bksgames.game.common.moves.ActionToken;
//import com.bksgames.game.common.moves.IncompleteMove;
//import com.bksgames.game.core.main.GameManager;
//import com.bksgames.game.core.utils.Point;
//import com.bksgames.game.common.utils.Direction;
//
//public abstract class Action {
//    protected final IncompleteMove incompleteMove;
//    protected final Point minionPosition;
//    protected final GameManager gameManager;
//
//    protected abstract void handle();
//    protected abstract ActionToken getActionToken();
//
//
//    public final IncompleteMove getIncompleteMove() {
//        return incompleteMove;
//    }
//
//    public Action(Direction direction, Point minionPosition, GameManager gameManager) {
//        this.incompleteMove = new IncompleteMove(getActionToken(), direction);
//        this.minionPosition = minionPosition;
//        this.gameManager = gameManager;
//    }
//}
//
