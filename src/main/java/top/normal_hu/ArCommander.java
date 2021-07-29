package top.normal_hu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.normal_hu.configs.AdvancementsConfig;
import top.normal_hu.configs.RewardsConfig;
import top.normal_hu.gui.RewardGUI;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ArCommander implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            //only op can use command
            if (player.isOp()){
                if (args.length >= 1){
                    switch (args[0].toLowerCase()){
                        case "create":
                            if (args.length == 2){
                                //check if the name has been used,Case insensitive
                                String name = args[1].toLowerCase();
                                for (Reward reward:Main.getRewards()){
                                    if(reward.getName().equals(name)){
                                        player.sendMessage("the name has been used,please create an another name!");
                                        return false;
                                    }
                                }
                                var rewards = Main.getCreatingReward().values();
                                for (Reward reward:rewards){
                                    if (reward.getName().equals(name)){
                                        player.sendMessage("the name has been used,please create an another name!");
                                        return false;
                                    }
                                }
                                //set up the rewards' name
                                Reward reward = new Reward();
                                reward.setName(name);
                                Main.getCreatingReward().put(player.getUniqueId(),reward);
                                // open gui
                                new RewardGUI(player);
                            }
                            else player.sendMessage("usage:ar create [rewards' name]");
                            break;

                        case "edit":
                            if (args.length == 2){
                                var rewards = Main.getRewards();
                                for (Reward reward:rewards){
                                    if (reward.getName().equals(args[1].toLowerCase())){
                                        var creating = Main.getCreatingReward();
                                        creating.put(player.getUniqueId(),reward);
                                        var items = reward.getItems();
                                        new RewardGUI(player).setItemStacks(items);
                                    }
                                }
                            }
                            else player.sendMessage("usage:ar edit [rewards' name]");
                            break;
                        case "remove":
                            if (args.length == 2){
                                var rewards = Main.getRewards();
                                for (Reward reward:rewards){
                                    if (reward.getName().equals(args[1].toLowerCase())){
                                        //check if the reward had binded a advancement,if has then return
                                        if(reward.getAdvancements().size() > 0){
                                            player.sendMessage("this reward has binded an advancement," +
                                                    "please use ar unbind [advancement] [reward's name]");
                                            return false;
                                        }
                                        rewards.remove(reward);
                                        RewardsConfig.saveFromArray();
                                    }
                                }
                            }
                            else player.sendMessage("usage:ar remove [reward's name]");
                            break;
                        case "bind":
                            if (args.length == 3){
                                var advs = AdvancementsConfig.getAdvancements();
                                for (String adv:advs){
                                    if (args[1].equals(adv)){ //advancement equal
                                        var rewards = Main.getRewards();
                                        for (Reward reward:rewards){
                                            if (args[2].toLowerCase().equals(reward.getName())){  //reward's name equal
                                                var rewardAdvancements = reward.getAdvancements();
                                                if (rewardAdvancements == null){
                                                    rewardAdvancements = new ArrayList<String>();
                                                }
                                                rewardAdvancements.add(adv);
                                                reward.setAdvancements(rewardAdvancements);
                                                RewardsConfig.saveFromArray();
                                                player.sendMessage(
                                                        "binded advancement["+ adv +"] to reward[" + reward + "]");
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                            else player.sendMessage("usage:ar bind [advancement] [reward's name]");
                            break;
                        case "unbind":
                            if (args.length == 3){
                                var rewards = Main.getRewards();
                                for (Reward reward:rewards){
                                    if (reward.getName().equals(args[2].toLowerCase())){
                                        var advs = reward.getAdvancements();
                                        if (advs == null){
                                            player.sendMessage("this reward doesn't bind any advancements");
                                            return false;
                                        }
                                        for (String adv:advs){
                                            if (adv.equals(args[1])){
                                                advs.remove(adv);
                                                RewardsConfig.saveFromArray();
                                                player.sendMessage("has unbinded!");
                                                return true;
                                            }
                                        }
                                        player.sendMessage("this reward doesn't bind this advancement");
                                    }
                                }
                            }
                            else player.sendMessage("usage:ar bind [advancement] [reward's name]");
                            break;
                        case "debug":
                            Logger logger = Main.getInst().getLogger();
                            var rewards = Main.getRewards();
                            for (Reward reward:rewards){
                                logger.info("reward's name:" + reward.getName());
                                for (ItemStack item:reward.getItems()){
                                    logger.info("name:"+item.getType().name()+",amount:" + item.getAmount());
                                }
                                for (String adv:reward.getAdvancements()){
                                    logger.info("adv:" + adv);
                                }
                                logger.info("\n");
                            }
                            break;
                    }
                }
            }
        }

        return false;
    }
}