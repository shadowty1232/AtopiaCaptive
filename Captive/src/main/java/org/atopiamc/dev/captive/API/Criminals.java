package org.atopiamc.dev.captive.API;

import org.atopiamc.dev.captive.Main;
import org.atopiamc.dev.captive.Utils;
import org.bukkit.Location;
import org.bukkit.World;

public class Criminals {

    public Location spawn;
    public World world;

    public Criminals(Main plugin) {
        world = plugin.getServer().getWorld(Utils.getString("Criminals.Spawn.World"));
        double x = plugin.getConfig().getDouble("Criminals.Spawn.X");
        double y = plugin.getConfig().getDouble("Criminals.Spawn.Y");
        double z = plugin.getConfig().getDouble("Criminals.Spawn.Z");
        spawn = new Location(world, x, y, z);
    }

    public Location getCriminalsSpawn() {
        return spawn;
    }
}
