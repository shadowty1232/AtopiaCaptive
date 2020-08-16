package net.atopiamc.dev.captive.Listener;

import net.atopiamc.dev.captive.API.Teams.Teams;
import net.atopiamc.dev.captive.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;

public class BlockListener implements Listener {

    public BlockListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    ArrayList<Player> cops = Teams.getCopTeam();
    ArrayList<Player> prisoner = Teams.getPrisonerTeam();
    ArrayList<Player> criminals = Teams.getCriminalTeam();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Material blockType = e.getBlock().getType();
        if (cops.contains(p) || prisoner.contains(p) || criminals.contains(p)) {
            if (blockType.equals(Material.DIRT) || blockType.equals(Material.STONE)
                || blockType.equals(Material.GRASS) || blockType.equals(Material.SMOOTH_BRICK)
                || blockType.equals(Material.WOOD)) {
                return;
            } else {
                e.setCancelled(true);
            }
        }
    }


}
