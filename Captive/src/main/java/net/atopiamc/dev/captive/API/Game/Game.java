package net.atopiamc.dev.captive.API.Game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface Game {
    ArrayList<Player> players = new ArrayList<Player>();

    void start();

    void startGame();

    void stop();

    boolean inLobby();

    void join(Player paramPlayer);

    void leave(Player paramPlayer);

    void sendMessage(String paramString);

    void setEntryPoint(Location paramLocation);

    Location getEntryPoint();

    String getName();

    boolean hasStarted();

}
