package net.atopiamc.dev.captive.API;

import net.atopiamc.dev.captive.API.Game.Game;
import net.atopiamc.dev.captive.API.Game.GamePlayer;
import net.atopiamc.dev.captive.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameAPI {
    public static Set<Game> games;
    public static HashMap<Plugin, GameAPI> apis;
    public static HashMap<Player, GamePlayer> gamePlayers;
    private Main plugin;

    public GameAPI(Main plugin) {
        this.plugin = plugin;
    }

    public boolean inGame(Player p) {
        return gamePlayers.containsKey(p);
    }

    public static GameAPI getInstance(Main plugin) {
        if (GameAPI.apis.containsKey(plugin)) {
            return GameAPI.apis.get(plugin);
        }
        GameAPI api = new GameAPI(plugin);
        GameAPI.apis.put(plugin, api);
        return api;
    }

    static {
        games = new HashSet<Game>();
        apis = new HashMap<Plugin, GameAPI>();
        gamePlayers = new HashMap<Player, GamePlayer>();
    }
}
