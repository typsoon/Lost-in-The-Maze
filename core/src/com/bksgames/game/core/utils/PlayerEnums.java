package com.bksgames.game.core.utils;

import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.globalClasses.enums.PlayerColor;

public class PlayerEnums {
	Displayable getNexusColor(PlayerColor playerColor){
		if(playerColor==PlayerColor.RED) return Displayable.RED_NEXUS;
		else return Displayable.BLUE_NEXUS;
	}

	Displayable getMinionColor(PlayerColor playerColor){
		if(playerColor==PlayerColor.RED) return Displayable.RED_MINION;
		else return Displayable.BLUE_MINION;
	}

	Displayable getMirrorColor(PlayerColor playerColor){
		if(playerColor==PlayerColor.RED) return Displayable.RED_MIRROR;
		else return Displayable.BLUE_MIRROR;
	}

}
