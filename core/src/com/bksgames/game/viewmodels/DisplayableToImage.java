package com.bksgames.game.viewmodels;

import com.bksgames.game.enums.Displayable;

import java.util.HashMap;

public class DisplayableToImage {
    static private final HashMap<Displayable, DisplayInfo> mapping = new HashMap<>();

    public record DisplayInfo(String revealed, String visible, String layer) {
    }

    public static DisplayInfo getDisplayInfo(Displayable displayable) {
        return mapping.get(displayable);
    }

    static {
        mapping.put(Displayable.BLUE_NEXUS, new DisplayInfo("RevealedBlueNexus", "VisibleBlueNexus", "wallsAndNexuses"));
        mapping.put(Displayable.RED_NEXUS, new DisplayInfo("RevealedRedNexus", "VisibleRedNexus", "wallsAndNexuses"));

        mapping.put(Displayable.WALL, new DisplayInfo("RevealedWall", "VisibleWall", "wallsAndNexuses"));
        mapping.put(Displayable.TUNNEL, new DisplayInfo("Revealed", "Visible", "tunnels"));

        mapping.put(Displayable.RED_MINION, new DisplayInfo("RedMinion", "RedMinion", "minions"));
        mapping.put(Displayable.BLUE_MINION, new DisplayInfo("BlueMinion", "BlueMinion", "minions"));
    }
}
