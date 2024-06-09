package com.bksgames.game.core.main;

import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.utils.IsDisplayable;
import com.bksgames.game.core.utils.KnownPosition;
import com.bksgames.game.core.utils.Point;

import java.util.*;

public class BoardManager {
    final GameManager gameManager;
    final Map<KnownPosition,Point> positions;
    public BoardManager subscribe (KnownPosition observable)
    {
        positions.put(observable,observable.getPosition());
        if(observable instanceof IsDisplayable displayable && observable.getPosition()!=null)
            gameManager.sendUpdate(UpdateHolderFactory.produceUpdateHolder(new TileUpdate(displayable.getDisplayable(),true, observable.getX(), observable.getY())));
        return this;
    }
    @SuppressWarnings("unused")
    public void unsubscribe(KnownPosition observable)
    {
        positions.remove(observable);
    }
    public void update(KnownPosition observable)
    {
        if(!positions.containsKey(observable) || positions.get(observable) == observable.getPosition())
            return;
        if(observable instanceof IsDisplayable displayable && observable.getPosition()!=null)
            gameManager.sendUpdate(UpdateHolderFactory.produceUpdateHolder(new TileUpdate(displayable.getDisplayable(),true, observable.getX(), observable.getY())));

        if(positions.get(observable)!=null)
            gameManager.getBoard().getTile(positions.get(observable)).getTunnel().removeObject(observable);
        positions.put(observable,observable.getPosition());
        if(positions.get(observable)!=null)
            gameManager.getBoard().getTile(positions.get(observable)).getTunnel().addObject(observable);
    }



    public BoardManager(GameManager gameManager) {
        this.gameManager = gameManager;
        positions = new HashMap<>();
    }
}
