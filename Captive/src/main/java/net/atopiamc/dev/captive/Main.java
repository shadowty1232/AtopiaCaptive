package net.atopiamc.dev.captive;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.Teams.Cop;
import net.atopiamc.dev.captive.API.Teams.Criminals;
import net.atopiamc.dev.captive.API.Teams.Prisoner;
import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Listener.BlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerAPI();
        registerEvents();
        getLogger().info("by Carbonate + TheJokerDev");
    }

    public void registerAPI() {
        new Teams(this);
        new Cop(this);
        new Criminals(this);
        new Prisoner(this);
        new GameFunctions(this);
    }

    public void registerEvents() {
        new BlockListener(this);
    }

}
