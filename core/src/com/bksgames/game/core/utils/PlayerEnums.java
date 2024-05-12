package com.bksgames.game.core.utils;

import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.globalClasses.enums.PlayerColor;

public class PlayerEnums {
	public static Displayable getNexusColor(PlayerColor playerColor){
		if(playerColor==PlayerColor.RED) return Displayable.RED_NEXUS;
		else return Displayable.BLUE_NEXUS;
	}

	public static Displayable getMinionColor(PlayerColor playerColor){
		if(playerColor==PlayerColor.RED) return Displayable.RED_MINION;
		else return Displayable.BLUE_MINION;
	}

	public static Displayable getMirrorColor(PlayerColor playerColor){
		if(playerColor==PlayerColor.RED) return Displayable.RED_MIRROR;
		else return Displayable.BLUE_MIRROR;
	}

}
