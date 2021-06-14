package com.bungoh.bungohutils.items;

import com.bungoh.bungohutils.chat.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(Chat.format(name));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.stream(lore).map(Chat::format).collect(Collectors.toList()));
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

}
