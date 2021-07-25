package top.normal_hu.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import top.normal_hu.Main;
import top.normal_hu.Reward;
import top.normal_hu.configs.RewardsConfig;
import top.normal_hu.holders.RewardHolder;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ClickRewardGUIListener implements Listener {
    @EventHandler
    public void onClickInventory(InventoryClickEvent event){
        if (event.getInventory() == null) return;;
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getHolder() instanceof RewardHolder){
            if(event.getSlot() == 17){
                ItemStack[] items = event.getView().getTopInventory().getContents();
                Reward reward = Main.getCreatingReward().get(player.getUniqueId());
                ArrayList<ItemStack> tmpItems = new ArrayList<ItemStack>();
                for (int i=0;i<17;i++){
                    if (items[i]!=null && !items[i].getType().equals(Material.AIR)){
                        tmpItems.add(items[i]);
                    }
                }
                reward.setItems(tmpItems);

                event.setCancelled(true);
                player.closeInventory();
                //add the reward to global rewards
                Main.getRewards().add(reward);
                //save to rewards.yml
                RewardsConfig.saveFromArray();
                //this op has created a reward,so he's not in creating
                Main.getCreatingReward().remove(player.getUniqueId());
                //debug
                Logger logger = Main.getInst().getLogger();
                for (Reward reward1:Main.getRewards()){
                    logger.info("reward's name:" + reward1.getName());
                    for (ItemStack item2:reward1.getItems()){
                        logger.info(item2.getType().toString() + "," + item2.getAmount());
                    }
                }
            }
        }
    }
}
