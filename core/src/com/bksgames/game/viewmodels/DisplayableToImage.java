package com.bksgames.game.viewmodels;

import com.bksgames.game.enums.Displayable;

import java.util.HashMap;

public class DisplayableToImage {
    static private HashMap<Displayable, DisplayInfo> mapping = new HashMap<>();

    public record DisplayInfo(String revealed, String visible, String layer) {
    }

    public static DisplayInfo getDisplayInfo(Displayable displayable) {
        return mapping.get(displayable);
    }

    static {
//        mapping.put(Displayable.BLUE_NEXUS, revAndVis("BlueNexe"));
//        mapping.put(Displayable.RED_NEXUS, "RedNexus");
//        mapping.put(Displayable.WALL, "Wall");
//        mapping.put(Displayable.TUNNEL, "Tunnel");
    }
}
