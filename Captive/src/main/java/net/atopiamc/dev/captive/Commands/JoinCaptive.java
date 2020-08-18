package net.atopiamc.dev.captive.Commands;

import net.atopiamc.dev.captive.API.Game.GameFunctions;
import net.atopiamc.dev.captive.Main;
import net.atopiamc.dev.captive.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCaptive implements CommandExecutor {

    private Main plugin;
    public JoinCaptive(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("joincaptive").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.Color("&cOnly players may execute this command."));
            return false;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("joincaptive")) {
            GameFunctions.getInstance().join(p);
        }
        return false;
    }
}
