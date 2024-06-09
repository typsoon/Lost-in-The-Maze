package com.bksgames.game.core.main;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.TileUpdate;
import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.utils.Point;

import java.util.HashSet;
import java.util.Set;

public class VisionManager {
    private final GameManager gameManager;

    public void playerVisionUpdate(PlayerColor color) {
        Player player = gameManager.getPlayers().get(color);
        Set<Point> visible = new HashSet<>(gameManager.getBoard().getNexusesVision(color));
        for (Minion minion : player.getMinions()) {
            if(minion.getHitPoints()>0)
                visible.addAll(gameManager.getBoard().getVisible(minion));
        }
        Set<Point> changes = player.updateVisibleTiles(visible);
        for (Point point : changes) {
            Tile actTile = gameManager.getBoard().getTile(point);
            gameManager.sendUpdate(
                    UpdateHolderFactory.produceUpdateHolder(
                            new TileUpdate(actTile.getDisplayable(), player.isVisible(point), point.x, point.y))
                    ,color);
            if (actTile.isHollow()) {
                if (actTile.getDisplayable() != Displayable.TUNNEL)
                    gameManager.sendUpdate(
                            UpdateHolderFactory.produceUpdateHolder(
                                    new TileUpdate(Displayable.TUNNEL, player.isVisible(point), point.x, point.y))
                            ,color);

                for (Entity entity : actTile.getTunnel().getEntities()) {
                    gameManager.sendUpdate(
                            UpdateHolderFactory.produceUpdateHolder(
                                    new TileUpdate(entity.getDisplayable(), player.isVisible(point), point.x, point.y))
                            ,color);
                }
            }
        }
    }
    public void playerVisionUpdate(){
        for(PlayerColor playerColor: gameManager.getPlayers().keySet())
            playerVisionUpdate(playerColor);
    }

    VisionManager(GameManager gameManager){
        this.gameManager = gameManager;
    }
}
