package org.atopiamc.dev.captive.API;

import org.atopiamc.dev.captive.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Teams {

    public ArrayList<Player> prisonerteam;
    public ArrayList<Player> copteam;
    public ArrayList<Player> criminalteam;

    public Teams(Main plugin) {
        this.prisonerteam = new ArrayList<Player>();
        this.copteam = new ArrayList<Player>();
        this.criminalteam = new ArrayList<Player>();
        plugin.getLogger().info("Teams API Loaded.");
    }

    public ArrayList<Player> getPrisonerTeam() {
        return prisonerteam;
    }

    public ArrayList<Player> getCopTeam() {
        return copteam;
    }

    public ArrayList<Player> getCriminalTeam() {
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
