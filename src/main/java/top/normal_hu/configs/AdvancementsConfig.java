package top.normal_hu.configs;

import org.bukkit.configuration.file.YamlConfiguration;
import top.normal_hu.Main;

import java.io.File;
import java.util.ArrayList;

public class AdvancementsConfig {

    public static void loadFile(){
        file = new File(Main.getInst().getDataFolder()+"/advancements.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static ArrayList<String> getAdvancements(){
        loadFile();
        var section = config.getConfigurationSection("Advancements");
        var indexes = section.getKeys(false);
        ArrayList<String> advancements = new ArrayList<String>();
        for (String index:indexes){
            String advStr = config.getString("Advancements." + index + ".story");
            advancements.add(advStr);
        }
        return advancements;
    }

    private static File file;
    private static YamlConfiguration config;

}
