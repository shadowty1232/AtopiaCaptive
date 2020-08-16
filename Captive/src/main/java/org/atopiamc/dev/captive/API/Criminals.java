package org.atopiamc.dev.captive.API;

import org.atopiamc.dev.captive.Main;
import org.atopiamc.dev.captive.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Criminals {

    public Location spawn;
    public World world;

    private Main plugin;
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

    public void setCriminalSpawn(Player p) {
        Location playerLoc = p.getLocation();
        double x = playerLoc.getX();
        double y = playerLoc.getY();
        double z = playerLoc.getZ();
        plugin.getConfig().set("Criminals.Spawn.X", x);
        plugin.getConfig().set("Criminals.Spawn.Y", y);
        plugin.getConfig().set("Criminals.Spawn.Z", z);
    }
}
