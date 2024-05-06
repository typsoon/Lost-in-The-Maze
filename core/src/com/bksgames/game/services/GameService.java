package com.bksgames.game.services;

import com.bksgames.game.core.Player;

import java.util.*;

public interface GameService {
    PlayerService connect(Player.PlayerColor player);

}
