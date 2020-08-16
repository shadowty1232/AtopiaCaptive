package net.atopiamc.dev.captive.Utils;

import net.atopiamc.dev.captive.Main;
import org.bukkit.ChatColor;

public class Utils {

    public static String Color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getString(String string) {
        return Main.getPlugin(Main.class).getConfig().getString(string);
    }
}
