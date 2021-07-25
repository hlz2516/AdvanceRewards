package top.normal_hu;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class Reward implements ConfigurationSerializable {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdvancement() {
        return Advancement;
    }

    public void setAdvancement(String advancement) {
        Advancement = advancement;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }


    private String Name;
    private ArrayList<ItemStack> items;
    private String Advancement;

    @Override
    public @NotNull Map<String, Object> serialize() {
        return null;
    }
}
