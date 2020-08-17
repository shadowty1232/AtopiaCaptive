package net.atopiamc.dev.captive.API.Listener;

import net.atopiamc.dev.captive.API.Game.Game;
import net.atopiamc.dev.captive.Main;

public class GameStart extends GameEvent {

    public GameStart(Main plugin, Game game) {
        super(plugin, game);
    }

}
