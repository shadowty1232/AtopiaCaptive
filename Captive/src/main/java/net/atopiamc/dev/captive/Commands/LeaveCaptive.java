package net.atopiamc.dev.captive.Commands;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.API.GameAPI;
import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

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
        if (args.length == 0) {
            if (GameAPI.gamePlayers.get(p) != null) {
                if (!confirmation.contains(p.getName())) {
                    p.sendMessage(Utils.Color("&3Are you sure you want to leave the queue? Send again to confirmation."));
                    confirmation.add(p.getName());
                } else {
                    GameFunctions.getInstance().leave(p);
                    p.sendMessage(Utils.Color("&cYou have left the queue."));
                    confirmation.remove(p.getName());
                }
            } else {
                p.sendMessage(Utils.Color("&cYou have need playing a game to do that!"));
            }
        }
        return false;
    }
}
