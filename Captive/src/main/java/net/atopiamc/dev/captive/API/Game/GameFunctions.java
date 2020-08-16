package net.atopiamc.dev.captive.API.Game;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.CountdownTimer;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GameFunctions implements Game {

    private Main plugin;
    private boolean inLobby;
    private boolean hasStarted;
    private static final int MIN_PLAYERS;
    private static final int MAX_PLAYERS;
    private Location entryPoint;

    public GameFunctions(Main plugin) {
        this.plugin = plugin;
        this.inLobby = true;
        this.hasStarted = false;

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
        // TODO: Start EVENT
    }

    public void stop() {
        // TODO
    }

    public boolean inLobby() {
        return inLobby;
    }

    public void join(Player paramPlayer) {
        // TODO
    }

    public void leave(Player paramPlayer) {
        // TODO
    }

    public void sendMessage(String message) {
        for (Player p : GameFunctions.players) {
            p.sendMessage(Utils.Color(message));
        }
    }

    public void setEntryPoint(Location loc) {
        entryPoint = loc;
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        World world = loc.getWorld();
        plugin.getConfig().set("Captive.Lobby.World", world);
        plugin.getConfig().set("Captive.Lobby.X", x);
        plugin.getConfig().set("Captive.Lobby.Y", y);
        plugin.getConfig().set("Captive.Lobby.Z", z);
    }

    public Location getEntryPoint() {
        World world = plugin.getServer().getWorld((String) plugin.getConfig().get("Captive.Lobby.World"));
        double x = Double.parseDouble((String) plugin.getConfig().get("Captive.Lobby.X"));
        double y = Double.parseDouble((String) plugin.getConfig().get("Captive.Lobby.Y"));
        double z = Double.parseDouble((String) plugin.getConfig().get("Captive.Lobby.Z"));
        entryPoint = new Location(world, x, y, z);
        return entryPoint;
    }

}
