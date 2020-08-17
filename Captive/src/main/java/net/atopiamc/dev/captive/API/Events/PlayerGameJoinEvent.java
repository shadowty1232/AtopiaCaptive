package net.atopiamc.dev.captive.API.Events;

import net.atopiamc.dev.captive.API.Game.Game;
import org.bukkit.entity.Player;

public class PlayerGameJoinEvent extends PlayerGameEvent{

    public PlayerGameJoinEvent(Game game, Player who) {
        super(game, who);
    }

}
