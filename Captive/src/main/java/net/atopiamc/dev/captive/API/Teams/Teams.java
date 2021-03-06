package net.atopiamc.dev.captive.API.Teams;

import net.atopiamc.dev.captive.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Teams {

    public static Object[] teams;
    public static Object Cops;
    public static Object Prisoners;
    public static Object Criminals;
    public static ArrayList<Player> prisonerteam;
    public static ArrayList<Player> copteam;
    public static ArrayList<Player> criminalteam;

    private Main plugin;
    public Teams(Main plugin) {
        this.prisonerteam = new ArrayList<Player>();
        this.copteam = new ArrayList<Player>();
        this.criminalteam = new ArrayList<Player>();
        this.Cops = this.copteam;
        this.Prisoners = this.prisonerteam;
        this.Criminals = this.criminalteam;
        this.teams = new Object[]{this.Cops, this.Criminals, this.Prisoners};
        plugin.getLogger().info("Teams API Loaded.");
    }

    public static String getTeam(Player p) {
        if (getPrisonerTeam().contains(p)) {
            return "Prisoner";
        }
        else if (getCopTeam().contains(p)) {
            return "Cop";
        }
        else if (getCriminalTeam().contains(p)) {
            return "Criminal";
        }
        else {
            return null;
        }
    }

    public static Object[] getTeams() {
        return teams;
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

    public static boolean addPrisonerTeam(Player p) {
        if (prisonerteam.size() > 1) {
            return false;
        } else {
            prisonerteam.add(p);
            return true;
        }
    }

    public static boolean addCopTeam(Player p) {
        if (copteam.size() > 8) {
            return false;
        } else {
            copteam.add(p);
            return true;
        }
    }

    public static boolean addCriminalTeam(Player p) {
        if (criminalteam.size() > 8) {
            return false;
        } else {
            criminalteam.add(p);
            return true;
        }
    }
}
