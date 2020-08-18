package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Cop {

    public static Location spawn;
    public static World world;
    public static Cop instance;

    private static Main plugin;
    public Cop(Main plugin) {
        plugin.getConfig().set("Cops.Spawn.World", "world");
        this.world = plugin.getServer().getWorld(Utils.getString("Cops.Spawn.World"));
        double x = plugin.getConfig().getDouble("Cops.Spawn.X");
        double y = plugin.getConfig().getDouble("Cops.Spawn.Y");
        double z = plugin.getConfig().getDouble("Cops.Spawn.Z");
        this.spawn = new Location(world, x, y, z);
        this.instance = this;
    }

    public Location getCopSpawn() {
        return spawn;
    }

    public void setCopSpawn(Player p) {
        world = p.getWorld();
        double x = p.getLocation().getX();
        double y = p.getLocation().getY();
        double z = p.getLocation().getZ();
        plugin.getConfig().set("Cops.Spawn.World", world.getName());
        plugin.getConfig().set("Cops.Spawn.X", x);
        plugin.getConfig().set("Cops.Spawn.Y", y);
        plugin.getConfig().set("Cops.Spawn.Z", z);
    }

    public static Cop getInstance() {
        return instance;
    }

}
