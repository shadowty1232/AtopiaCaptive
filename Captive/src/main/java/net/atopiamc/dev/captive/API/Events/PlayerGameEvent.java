package net.atopiamc.dev.captive.API.Events;

import net.atopiamc.dev.captive.API.Game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerGameEvent extends PlayerEvent implements Cancellable {

    private static HandlerList handlers;
    private boolean cancelled;
    private Game game;

    public PlayerGameEvent(Game game, Player who) {
        super(who);
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    static {
        handlers = new HandlerList();
    }
}
