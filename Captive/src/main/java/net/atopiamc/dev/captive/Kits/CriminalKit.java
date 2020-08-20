package net.atopiamc.dev.captive.Kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CriminalKit extends Kit{
	public CriminalKit(Player player) {
		super("Criminal", player, new ItemStack(Material.WOOL, 1, (short)14));
	}
	@Override
	public void receiveItems(byte id) {
        getPlayer().getInventory().clear();
        getPlayer().getInventory().setArmorContents(null);
    	ItemStack swords = new ItemStack(Material.IRON_SWORD, 1);
    	ItemStack pick = new ItemStack(Material.STONE_PICKAXE, 1);
    	ItemStack helm = new ItemStack(Material.IRON_HELMET, 1);
    	ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
    	ItemStack legg = new ItemStack(Material.IRON_LEGGINGS, 1);
    	ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
    	
    	getPlayer().getInventory().setItem(0, swords);
    	getPlayer().getInventory().setItem(1, pick);
    	getPlayer().getInventory().setHelmet(helm);
    	getPlayer().getInventory().setChestplate(chest);
    	getPlayer().getInventory().setBoots(boots);
    	getPlayer().getInventory().setLeggings(legg);
	}

}
