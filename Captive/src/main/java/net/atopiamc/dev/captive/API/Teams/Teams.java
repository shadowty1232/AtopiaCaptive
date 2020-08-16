package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Teams {

    public static ArrayList<Player> prisonerteam;
    public static ArrayList<Player> copteam;
    public static ArrayList<Player> criminalteam;

    private Main plugin;
    public Teams(Main plugin) {
        this.prisonerteam = new ArrayList<Player>();
        this.copteam = new ArrayList<Player>();
        this.criminalteam = new ArrayList<Player>();
        plugin.getLogger().info("Teams API Loaded.");
    }

    public static ArrayList<Player> getPrisonerTeam() {
        return prisonerteam;
    }

    public static ArrayList<Player> getCopTeam() {
        return copteam;
    }

    public static ArrayList<Player> getCriminalTeam() {
        return criminalteam;
    }

    public void addPrisonerTeam(Player p) {
        if (prisonerteam.size() > 1) {
            throw new IllegalArgumentException("Prisoner Team is too Large, Max Size 1.");
        } else {
            prisonerteam.add(p);
        }
    }

    public void addCopTeam(Player p) {
        if (copteam.size() > 8) {
            throw new IllegalArgumentException("Cop Team is too Large, Max Size 8.");
        } else {
            copteam.add(p);
        }
    }

    public void addCriminalTeam(Player p) {
        if (criminalteam.size() > 8) {
            throw new IllegalArgumentException("Criminal Team is too Large, Max Size 8.");
        } else {
            criminalteam.add(p);
        }
    }
}
