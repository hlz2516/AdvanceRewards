package top.normal_hu.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.normal_hu.Main;
import top.normal_hu.Reward;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AdvancementListener implements Listener {
    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event){
        String key = event.getAdvancement().getKey().getKey();
        Player player = event.getPlayer();
        player.sendMessage("the key:" + key);
        ArrayList<Reward> rewards = Main.getRewards();
        for (Reward reward:rewards){
            ArrayList<String> advs = reward.getAdvancements();
            if (advs == null)
                return;
            Logger logger = Main.getInst().getLogger();
            for (String adv:advs){
                logger.info("the reward[" + reward.getName() + "] has advancement["+adv+"]");
                if (adv.equals(key)){
                    player.sendMessage("give the player rewards");
                    Inventory inventory = player.getInventory();
                    ArrayList<ItemStack> items = reward.getItems();
                    for (ItemStack item:items){
                        if (inventory.getSize() == inventory.getMaxStackSize())
                            player.getWorld().dropItemNaturally(player.getLocation(),item);
                        else
                            inventory.addItem(item);
                    }
                }
            }
        }
    }
}
