package net.atopiamc.dev.captive.API.Game;

import net.atopiamc.dev.captive.API.Events.PlayerGameJoinEvent;
import net.atopiamc.dev.captive.API.Events.PlayerGameLeaveEvent;
import net.atopiamc.dev.captive.API.Exceptions.EntryPointNotSetException;
import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.API.Listener.GameReset;
import net.atopiamc.dev.captive.API.Listener.GameStart;
import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.CountdownTimer;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class GameFunctions implements Game {

    private static Main plugin;
    private boolean inLobby;
    private boolean hasStarted;
    private static final int MIN_PLAYERS;
    private static final int MAX_PLAYERS;
    private static Location entryPoint;
    public static GameFunctions instance;


    public GameFunctions(Main plugin) {
        this.plugin = plugin;
        this.inLobby = true;
        this.hasStarted = false;
        this.instance = this;

    }

    static {
        MIN_PLAYERS = 3;
        MAX_PLAYERS = 17;
    }

    public void start() {
        hasStarted = true;
        CountdownTimer timer = new CountdownTimer(this.plugin, 30, () -> {}, this::startGame, t -> {
        if (t.getSecondsLeft() <= 5) {
            this.sendMessage("§3Game starts in §b" + t.getSecondsLeft() + " §3seconds.");
        }
        else if (t.getSecondsLeft() % 5 == 0) {
               this.sendMessage("§3Game starts in §b" + t.getSecondsLeft() + " §3seconds.");
        }});
        timer.scheduleTimer();
    }

    public void startGame() {
        hasStarted = true;
        inLobby = false;
        for (Player p : GameFunctions.players) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.updateInventory();
        }
        Bukkit.getPluginManager().callEvent(new GameStart(plugin, this));
    }

    public void stop() {
        for (Player ppl : GameFunctions.players) {
            GameAPI.gamePlayers.get(ppl).leave();
            GameAPI.gamePlayers.remove(ppl);
            Bukkit.getPluginManager().callEvent(new PlayerGameLeaveEvent(this, ppl));
        }
        Bukkit.getPluginManager().callEvent(new GameReset(plugin, this));
        GameFunctions.players.clear();
        hasStarted = false;
        inLobby = true;
    }

    public boolean inLobby() {
        return inLobby;
    }

    public void join(Player p) {
        if (players.size() >= MAX_PLAYERS || hasStarted()) {
            return;
        }
        players.add(p);
        GameAPI.gamePlayers.put(p, new GamePlayer(this, p));
        try {
            p.sendMessage(Utils.Color("&aYou have joined the Captive queue."));
            p.teleport(getEntryPoint());
        }
        catch (EntryPointNotSetException e){
            players.remove(p);
            GameAPI.gamePlayers.remove(p);
            throw new EntryPointNotSetException("Entry Point for Captive not set");
        }
        Bukkit.getPluginManager().callEvent(new PlayerGameJoinEvent(this, p));
        if (players.size() >= MIN_PLAYERS && !hasStarted()) {
            start();
        }
    }

    public void leave(Player p) {
        players.remove(p);
        GameAPI.gamePlayers.get(p).leave();
        GameAPI.gamePlayers.remove(p);
        Bukkit.getPluginManager().callEvent(new PlayerGameLeaveEvent(this, p));
    }

    public void sendMessage(String message) {
        for (Player p : GameFunctions.players) {
            p.sendMessage(Utils.Color(message));
        }
    }

    public void setEntryPoint(Location loc) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);        
        entryPoint = loc;
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double pitch = loc.getPitch();
        double yaw = loc.getYaw();
        World world = loc.getWorld();
        config.set("Captive.Lobby.World", world.getName());
        config.set("Captive.Lobby.X", x);
        config.set("Captive.Lobby.Y", y);
        config.set("Captive.Lobby.Z", z);
        config.set("Captive.Lobby.Pitch", pitch);
        config.set("Captive.Lobby.Yaw", yaw);
        try {
        	config.save(configFile);
        } catch(IOException e) {
        	e.printStackTrace();
        }
    }

    public Location getEntryPoint() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);  
        
        World world = Bukkit.getWorld((String) config.get("Captive.Lobby.World"));
        double x = config.getDouble("Captive.Lobby.X");
        double y = config.getDouble("Captive.Lobby.Y");
        double z = config.getDouble("Captive.Lobby.Z");
        entryPoint = new Location(world, x, y, z);
        return entryPoint;
    }

    public String getName() {
        return null;
    }

    public static GameFunctions getInstance() {
        return instance;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

}
