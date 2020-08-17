package net.atopiamc.dev.captive.API;

import net.atopiamc.dev.captive.API.Game.Game;
import net.atopiamc.dev.captive.API.Game.GamePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class GameAPI {
    public static Set<Game> games;
    public static HashMap<Player, GamePlayer> gamePlayers;
}
