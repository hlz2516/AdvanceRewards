package top.normal_hu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.normal_hu.configs.AdvancementsConfig;

import java.util.ArrayList;
import java.util.List;

public class ArCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> result = new ArrayList<String>();

        if (args.length == 1){
            result.add("create");
            result.add("edit");
            result.add("remove");
            result.add("bind");
            result.add("unbind");
        }
        else if (args.length == 2){
            ArrayList<Reward> rewards = Main.getRewards();
            switch (args[0].toLowerCase()){
                case "edit":        //ar edit [reward's name]
                case "remove":      //ar remove [reward's name]
                    for (Reward reward:rewards){
                        result.add(reward.getName());
                    }
                    break;
                case "bind":        //ar bind [advancement's name] [reward's name]
                    //load all the advancements
                    ArrayList<String> advs = AdvancementsConfig.getAdvancements();
                    for (String adv:advs){
                        result.add(adv);
                    }
                    break;
                case "unbind":      //ar unbind [advancement's name] [reward's name]
                    //load the advancements has binded rewards
                    //one reward corresponds to multiple advancements
                    // and one advancement corresponds to one reward
                    for (Reward reward:rewards){
                        for (String adv:reward.getAdvancements()){
                            result.add(adv);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        else if (args.length == 3){
            if (args[0].equals("bind")){
                ArrayList<Reward> rewards = Main.getRewards();
                for (Reward reward:rewards){
                    result.add(reward.getName());
                }
            }
            else if (args[0].equals("unbind")){
                Reward reward = Main.getAdvancementBelongsTo(args[1].toLowerCase());
                if (reward != null){
                    result.add(reward.getName());
                }

            }
        }

        return result;
    }
}
