package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Criminals {

    public Location spawn;
    public World world;
    public static Criminals instance;

    private Main plugin;
    public Criminals(Main plugin) {
        plugin.getConfig().set("Criminals.Spawn.World", "world");
        world = plugin.getServer().getWorld(Utils.getString("Criminals.Spawn.World"));
        double x = plugin.getConfig().getDouble("Criminals.Spawn.X");
        double y = plugin.getConfig().getDouble("Criminals.Spawn.Y");
        double z = plugin.getConfig().getDouble("Criminals.Spawn.Z");
        spawn = new Location(world, x, y, z);
        this.instance = this;
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

    public static Criminals getInstance() {
        return instance;
    }
}
