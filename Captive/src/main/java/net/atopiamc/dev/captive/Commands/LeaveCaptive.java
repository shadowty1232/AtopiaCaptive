package net.atopiamc.dev.captive.Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.Game.GamePlayer;
import net.atopiamc.dev.captive.Utils.Utils;

public class LeaveCaptive implements CommandExecutor {
    private Main plugin;
    private ArrayList<String> confirmation = new ArrayList<>();
    public LeaveCaptive(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("leavecaptive").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.Color("&cOnly players may execute this command."));
            return false;
        }
        Player p = (Player) sender;
        int i = 0;
        if (cmd.getName().equalsIgnoreCase("leavecaptive")) {
            if (args.length == 0) {
                if (GameFunctions.getInstance().inLobby() == true) {
                    if (GameAPI.gamePlayers.get(p) != null) {
                        if (!confirmation.contains(p.getName())) {
                            p.sendMessage(Utils.Color("&3Are you sure you want to leave the queue? Send again to confirmation."));
                            confirmation.add(p.getName());
                        } else {
                            GameFunctions.getInstance().leave(p);
                            p.sendMessage(Utils.Color("&cYou have left the queue."));
                            for(GamePlayer pp : GameAPI.gamePlayers.values()) {
                            	pp.sendMessage(Utils.Color("&7" + p.getName() + " &ehas left! " + GameFunctions.getInstance().players.size() + "/17"));
                            }
                            confirmation.remove(p.getName());
                            
                        }
                    } else {
                        p.sendMessage(Utils.Color("&cYou have need playing a game to do that!"));
                    }
                } else {
                    p.sendMessage(Utils.Color("&cYou cannot leave right now."));
                }
                return false;
            }
        }
        return false;
    }
}
