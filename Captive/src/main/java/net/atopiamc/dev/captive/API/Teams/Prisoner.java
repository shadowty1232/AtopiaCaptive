package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Prisoner {

    public Location spawn;
    public World world;
    public static Prisoner instance;

    private Main plugin;
    public Prisoner(Main plugin) {
        plugin.getConfig().set("Prisoner.Spawn.World", "world");
        world = plugin.getServer().getWorld(Utils.getString("Prisoner.Spawn.World"));
        double x = plugin.getConfig().getDouble("Prisoner.Spawn.X");
        double y = plugin.getConfig().getDouble("Prisoner.Spawn.Y");
        double z = plugin.getConfig().getDouble("Prisoner.Spawn.Z");
        spawn = new Location(world, x, y, z);
        this.instance = this;
    }

    public Location getPrisonerSpawn() {
        return spawn;
    }

    public void setPrisonerSpawn(Player p) {
        Location playerLoc = p.getLocation();
        double x = playerLoc.getX();
        double y = playerLoc.getY();
        double z = playerLoc.getZ();
        plugin.getConfig().set("Prisoner.Spawn.X", x);
        plugin.getConfig().set("Prisoner.Spawn.Y", y);
        plugin.getConfig().set("Prisoner.Spawn.Z", z);
    }

    public static Prisoner getInstance() {
        return instance;
    }
}
