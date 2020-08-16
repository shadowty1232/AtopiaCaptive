package org.atopiamc.dev.captive.API;

import org.atopiamc.dev.captive.Main;
import org.atopiamc.dev.captive.Utils;
import org.bukkit.Location;
import org.bukkit.World;

public class Prisoner {

    public Location spawn;
    public World world;

    public Prisoner(Main plugin) {
        world = plugin.getServer().getWorld(Utils.getString("Prisoner.Spawn.World"));
        double x = plugin.getConfig().getDouble("Prisoner.Spawn.X");
        double y = plugin.getConfig().getDouble("Prisoner.Spawn.Y");
        double z = plugin.getConfig().getDouble("Prisoner.Spawn.Z");
        spawn = new Location(world, x, y, z);
    }

    public Location getPrisonerSpawn() {
        return spawn;
    }
}
