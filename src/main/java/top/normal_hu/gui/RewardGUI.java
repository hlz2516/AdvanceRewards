package top.normal_hu.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.normal_hu.holders.RewardHolder;

public class RewardGUI {
    public RewardGUI(Player player) {
        Inventory gui = Bukkit.createInventory(new RewardHolder(), 18, "set the rewards here");
        ItemStack save = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = save.getItemMeta();
        meta.setDisplayName("Click here to save the rewards!");
        save.setItemMeta(meta);
        gui.setItem(17, save);

        player.openInventory(gui);
    }
}
