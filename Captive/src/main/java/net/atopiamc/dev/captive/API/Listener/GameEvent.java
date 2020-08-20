package net.atopiamc.dev.captive.API.Listener;

import net.atopiamc.dev.captive.API.Game.Game;
import net.atopiamc.dev.captive.Main;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameEvent extends Event {

    private static HandlerList handlers = new HandlerList();

    private Main plugin;
    private Game game;

    public GameEvent(Main plugin, Game game) {
        this.plugin = plugin;
        this.game = game;
    }

    public Main getPlugin() {
        return plugin;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
