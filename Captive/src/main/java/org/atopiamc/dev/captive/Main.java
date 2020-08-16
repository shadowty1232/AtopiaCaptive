package org.atopiamc.dev.captive;

import org.atopiamc.dev.captive.API.Cop;
import org.atopiamc.dev.captive.API.Teams;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        loadAPI();
        getLogger().info("by Carbonate + TheJokerDev");
    }

    public void loadAPI() {
        new Teams(this);
        new Cop(this);
    }

}
