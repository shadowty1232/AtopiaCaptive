package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Prisoner {

    public Location spawn;
    public World world;
    public static Prisoner instance;
    public Location entryPoint;

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

    public void setPrisonerSpawn(Location loc) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
        entryPoint = loc;
        World world = loc.getWorld();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        config.set("Prisoner.Spawn.World", world.getName());
        config.set("Prisoner.Spawn.X", x);
        config.set("Prisoner.Spawn.Y", y);
        config.set("Prisoner.Spawn.Z", z);
        config.set("Prisoner.Spawn.Pitch", pitch);
        config.set("Prisoner.Spawn.Yaw", yaw);
        try {
            config.save(configFile);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Prisoner getInstance() {
        return instance;
    }
}
