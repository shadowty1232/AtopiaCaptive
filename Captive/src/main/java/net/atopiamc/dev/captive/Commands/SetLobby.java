package net.atopiamc.dev.captive.Commands;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {

    private Main plugin;
    public SetLobby(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("setlobby").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.Color("&cOnly players may execute this command."));
            return false;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("setlobby")) {
            if (p.hasPermission("captive.admin")) {
                GameFunctions.getInstance().setEntryPoint(p.getLocation());
                p.sendMessage(Utils.Color("&bLobby has been set."));
            }
        }


        return false;
    }

}
