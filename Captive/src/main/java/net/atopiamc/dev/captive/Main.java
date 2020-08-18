package net.atopiamc.dev.captive;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.Teams.Cop;
import net.atopiamc.dev.captive.API.Teams.Criminals;
import net.atopiamc.dev.captive.API.Teams.Prisoner;
import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Commands.*;
import net.atopiamc.dev.captive.Listener.BlockListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerAPI();
        registerEvents();
        registerCommands();
        registerConfig();
        getLogger().info("by Carbonate + TheJokerDev");
    }

    public void registerConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                saveDefaultConfig();
                getLogger().info("Config not found. Creating.");
            }
            catch (RuntimeException e) {
                getLogger().info("Error Saving Config.");
            }
        } else {
            getLogger().info("Config Loaded.");
        }
    }

    public void registerAPI() {
        new Teams(this);
        new Cop(this);
        new Criminals(this);
        new Prisoner(this);
        new GameFunctions(this);
    }

    public void registerCommands() {
        new SetLobby(this);
        new SetCopSpawn(this);
        new SetCriminalSpawn(this);
        new SetPrisonerSpawn(this);
        new JoinCaptive(this);
        new LeaveCaptive(this);
    }

    public void registerEvents() {
        new BlockListener(this);
    }

}
