package net.atopiamc.dev.captive.API.Listener;

import net.atopiamc.dev.captive.API.Game.Game;
import net.atopiamc.dev.captive.Main;

public class GameReset extends GameEvent {
    public GameReset(Main plugin, Game game) {
        super(plugin, game);
    }
}
