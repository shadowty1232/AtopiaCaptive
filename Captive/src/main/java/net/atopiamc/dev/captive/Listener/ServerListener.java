package net.atopiamc.dev.captive.Listener;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListener implements Listener {

    private Main plugin;
    public ServerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (GameAPI.gamePlayers.get(p) == null) {
            GameFunctions.getInstance().join(p);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (GameAPI.gamePlayers.get(p) != null) {
            GameFunctions.getInstance().leave(p);
        }
    }

}
