package com.bksgames.game.services;

import com.bksgames.game.core.Player;
import com.bksgames.game.enums.PlayerColor;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);

}
