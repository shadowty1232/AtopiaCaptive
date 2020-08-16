package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;

public class Cop {

    public Location spawn;
    public World world;

    public Cop(Main plugin) {
        world = plugin.getServer().getWorld(Utils.getString("Cops.Spawn.World"));
        double x = plugin.getConfig().getDouble("Cops.Spawn.X");
        double y = plugin.getConfig().getDouble("Cops.Spawn.Y");
        double z = plugin.getConfig().getDouble("Cops.Spawn.Z");
        spawn = new Location(world, x, y, z);
    }

    public Location getCopSpawn() {
        return spawn;
    }

}
