package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Cop {

    public static Location spawn;
    public static World world;
    public static Cop instance;
    private static Location entryPoint;


    private Main plugin;
    
    public Cop(Main plugin) {
    	this.plugin = plugin;
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

    public void setCopSpawn(Location loc) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
        entryPoint = loc;
        World world = loc.getWorld();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        config.set("Cops.Spawn.World", world.getName());
        config.set("Cops.Spawn.X", x);
        config.set("Cops.Spawn.Y", y);
        config.set("Cops.Spawn.Z", z);
        config.set("Cops.Spawn.Pitch", pitch);
        config.set("Cops.Spawn.Yaw", yaw);
        try {
        	config.save(configFile);
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }

    public static Cop getInstance() {
        return instance;
    }

}
