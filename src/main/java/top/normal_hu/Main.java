package top.normal_hu;

import org.bukkit.plugin.java.JavaPlugin;
import top.normal_hu.configs.RewardsConfig;
import top.normal_hu.events.ClickRewardGUIListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("AdvanceRewards v1.0 has enabled!");

        inst = this;
        creatingReward = new HashMap<UUID,Reward>();
        rewards = new ArrayList<Reward>();
        RewardsConfig.loadToArray();

        getServer().getPluginManager().registerEvents(new ClickRewardGUIListener(),this);

        getCommand("AdvanceRewards").setExecutor(new ArCommander());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("AdvanceRewards v1.0 has disenabled!");
        //save the rewards to rewards.yml
        RewardsConfig.saveFromArray();
    }

    public static ArrayList<Reward> getRewards(){ return rewards; }

    public static HashMap<UUID,Reward> getCreatingReward(){
        return creatingReward;
    }

    public static Main getInst(){
        return inst;
    }

    private static ArrayList<Reward> rewards;
    private static HashMap<UUID,Reward> creatingReward;
    private static Main inst;
}
