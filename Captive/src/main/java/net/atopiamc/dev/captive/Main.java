package net.atopiamc.dev.captive;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.API.Teams.Cop;
import net.atopiamc.dev.captive.API.Teams.Criminals;
import net.atopiamc.dev.captive.API.Teams.Prisoner;
import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Commands.*;
import net.atopiamc.dev.captive.Kits.Kit;
import net.atopiamc.dev.captive.Listener.BlockListener;
import net.atopiamc.dev.captive.Listener.GameListener;
import net.atopiamc.dev.captive.Listener.ServerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public GameAPI api;

    public void onEnable() {
        api = GameAPI.getInstance(this);
        Kit.loadKits();
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
        new ServerListener(this);
        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
    }

    public GameAPI getApi() {
        return api;
    }

    public static Main getPlugin() {
        return getPlugin(Main.class);
    }

}
