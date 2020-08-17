package net.atopiamc.dev.captive;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.Teams.Cop;
import net.atopiamc.dev.captive.API.Teams.Criminals;
import net.atopiamc.dev.captive.API.Teams.Prisoner;
import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Commands.SetCopSpawn;
import net.atopiamc.dev.captive.Commands.SetCriminalSpawn;
import net.atopiamc.dev.captive.Commands.SetLobby;
import net.atopiamc.dev.captive.Commands.SetPrisonerSpawn;
import net.atopiamc.dev.captive.Listener.BlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerAPI();
        registerEvents();
        registerCommands();
        getLogger().info("by Carbonate + TheJokerDev");
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
    }

    public void registerEvents() {
        new BlockListener(this);
    }

}
