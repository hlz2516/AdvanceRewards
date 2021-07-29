package top.normal_hu;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Reward{
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<String> getAdvancements() {
        if (Advancements == null)
            Advancements = new ArrayList<String>();
        return Advancements;
    }

    public void setAdvancements(ArrayList<String> advancements) {
        Advancements = advancements;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }


    private String Name;
    private ArrayList<ItemStack> items;
    private ArrayList<String> Advancements;
}
