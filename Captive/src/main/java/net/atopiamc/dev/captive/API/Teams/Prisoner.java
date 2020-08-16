package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;

public class Prisoner {

    public Location spawn;
    public World world;

    private Main plugin;
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
