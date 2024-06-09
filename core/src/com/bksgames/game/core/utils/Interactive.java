package com.bksgames.game.core.utils;

import com.bksgames.game.common.moves.ActionToken;

/**
 * Players can interact with
 * @author riper
 */
public interface Interactive {
 /**
  * @return amount of active points
  */
 @SuppressWarnings("unused")
 int getActionPoints();
 /**
  * Make action and use action points
  * @param actionToken type of action
  * @return if action was made
  */
 @SuppressWarnings("UnusedReturnValue")
 boolean makeAction(ActionToken actionToken);
 /**
  *
  * @param actionToken type of action
  * @return if it can make action
  */
     boolean canMakeAction(ActionToken actionToken);
}
