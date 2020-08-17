package net.atopiamc.dev.captive.API.Game;

import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GamePlayer {

    private Game game;
    private Player p;
    private Location loc;
    private ItemStack[] invContents;
    private GameMode gameMode;

    public GamePlayer(Game game, Player p) {
        this.game = game;
        this.p = p;
        this.loc = p.getLocation();
        this.invContents = p.getInventory().getContents();
        this.gameMode = p.getGameMode();

        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);

        GameAPI.gamePlayers.put(p, this);
    }

    public void setTeam(String team) {
        if (team.equals("criminal")) {
            Teams.getCriminalTeam().add(p);
        }
        else if (team.equals("prisoner")) {
            Teams.getPrisonerTeam().add(p);
        }
        else if (team.equals("cop")) {
            Teams.getCopTeam().add(p);
        }
    }

    public String getTeam() {
        if (Teams.getCriminalTeam().contains(p)) {
            return "criminal";
        }
        else if (Teams.getPrisonerTeam().contains(p)) {
            return "prisoner";
        }
        else if (Teams.getCopTeam().contains(p)) {
            return "cop";
        } else {
            return null;
        }
    }

    public void leave() {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().setContents(invContents);
        p.updateInventory();
        p.teleport(loc);
        p.setGameMode(gameMode);
        if (Teams.getCopTeam().contains(p)) {
            Teams.getCopTeam().remove(p);
        }
        else if (Teams.getCriminalTeam().contains(p)) {
            Teams.getCriminalTeam().remove(p);
        }
        else if (Teams.getPrisonerTeam().contains(p)) {
            Teams.getPrisonerTeam().remove(p);
        }
    }

    public void sendMessage(String message) {
        p.sendMessage(Utils.Color(message));
    }

    public String getName() {
        return p.getName();
    }

    public Location getLocation() {
        return loc;
    }

    public ItemStack[] getInvContents() {
        return invContents;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return p;
    }

}
