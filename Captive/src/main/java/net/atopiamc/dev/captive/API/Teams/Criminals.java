package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Criminals {

    public Location spawn;
    public World world;
    public static Criminals instance;
    private static Location entryPoint;


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

    public void setCriminalSpawn(Location loc) {
    	File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));  
        entryPoint = loc;
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        config.set("Criminals.Spawn.X", x);
        config.set("Criminals.Spawn.Y", y);
        config.set("Criminals.Spawn.Z", z);
        config.set("Criminals.Spawn.Pitch", pitch);
        config.set("Criminals.Spawn.Yaw", yaw);
        
        try {
        	config.save(configFile);
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }

    public static Criminals getInstance() {
        return instance;
    }
}
