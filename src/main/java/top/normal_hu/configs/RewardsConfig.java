package top.normal_hu.configs;

/*
    rewards.yml format:
    rewards:
      'name1':
        items:
          - xxxxxx
          - xxxxxx
        advancement: xxxxxxxxxxxx（get value from advancements.yml）
      'name2':
        items:
            ......
 */

import com.sun.tools.jconsole.JConsoleContext;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.Yaml;
import top.normal_hu.Main;
import top.normal_hu.Reward;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class RewardsConfig {

    public static void loadFile(){
        if(file == null) {
            //reload file
            file = new File(Main.getInst().getDataFolder(), "rewards.yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    config = YamlConfiguration.loadConfiguration(file);
                    config.save(file);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //reload config
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void loadToArray(){
        loadFile();
        //Logger logger = Main.getInst().getLogger();
        ArrayList<Reward> rewards = Main.getRewards();

        var section = config.getConfigurationSection("rewards");
        if (section == null){
            return;
        }
        var keys = section.getKeys(false);
        String path = "rewards.";

        rewards.clear();
        for (String key:keys){
            Reward reward = new Reward();
            ArrayList<ItemStack> items = (ArrayList<ItemStack>) config.get(path + key + ".items");
            String advancement  = config.getString(path + key + ".advancement");
            reward.setName(key);
            reward.setItems(items);
            reward.setAdvancement(advancement);
            rewards.add(reward);
        }
    }

    public static void saveFromArray(){
        loadFile();
        ArrayList<Reward> rewards = Main.getRewards();

        String path   = "rewards.";
        for (Reward reward:rewards){
            config.set(path+reward.getName()+".items",reward.getItems());
            config.set(path + reward.getName() + ".advancement",null);
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File file;
    private static YamlConfiguration config;
}
