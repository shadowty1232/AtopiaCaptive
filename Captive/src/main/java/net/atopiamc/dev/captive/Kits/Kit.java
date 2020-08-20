package net.atopiamc.dev.captive.Kits;

import net.atopiamc.dev.captive.API.Teams.Teams;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public abstract class Kit {
    public static Set<Kit> kits = new HashSet<Kit>();

    private String name;
    private Player player;
    private ItemStack icon;

    private String teams;

    public Kit(String name, Player player, ItemStack icon) {
        this.name = name;
        this.player = player;

        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(getName());
        icon.setItemMeta(meta);

        this.icon = icon;

        if (player != null)
            this.teams = Teams.getTeams().toString();

        for (Kit kit : kits)
        {
            if (kit.getName().equalsIgnoreCase(getName()))
                return;
        }

        kits.add(this);
    }

    public ItemStack getIcon() {
        return icon;
    }

    public static void loadKits() {
        new CopsKit(null);
        new CriminalKit(null);
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public abstract void receiveItems(byte id);
}
