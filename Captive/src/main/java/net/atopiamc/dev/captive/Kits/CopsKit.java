package net.atopiamc.dev.captive.Kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CopsKit extends Kit{

	public static CopsKit instance;

	public CopsKit(Player player) {
		super("Cops", player, new ItemStack(Material.WOOL, 1, (short)11));
		instance = this;
	}

	@Override
	public void receiveItems(Player p) {
        p.getInventory().clear();
		p.getInventory().setArmorContents(null);
    	ItemStack swords = new ItemStack(Material.IRON_SWORD, 1);
    	ItemStack helm = new ItemStack(Material.IRON_HELMET, 1);
    	ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
    	ItemStack legg = new ItemStack(Material.IRON_LEGGINGS, 1);
    	ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);

		p.getInventory().setItem(0, swords);
		p.getInventory().setHelmet(helm);
		p.getInventory().setChestplate(chest);
		p.getInventory().setBoots(boots);
		p.getInventory().setLeggings(legg);
	}

	public static CopsKit getInstance() {
		return instance;
	}

}
