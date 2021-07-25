package top.normal_hu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.normal_hu.gui.RewardGUI;

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
                                //set up the rewards' name
                                Reward reward = new Reward();
                                reward.setName(name);
                                Main.getCreatingReward().put(player.getUniqueId(),reward);
                                // open gui
                                new RewardGUI(player);
                            }
                            player.sendMessage("usage:ar create [rewards' name]");
                            break;
                    }
                }
            }
        }

        return false;
    }
}
