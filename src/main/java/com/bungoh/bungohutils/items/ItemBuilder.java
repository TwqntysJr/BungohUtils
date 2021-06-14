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

    /**
     * Creates an ItemBuilder to be used to create a custom ItemStack.
     * @param material The material of the ItemStack.
     */
    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    /**
     * Sets the display name of the ItemStack with chat formatting.
     * @param name The display name of the ItemStack.
     * @return The ItemBuilder for further modification.
     */
    public ItemBuilder setName(String name) {
        meta.setDisplayName(Chat.format(name));
        return this;
    }

    /**
     * Sets the lore of the ItemStack with chat formatting.
     * @param lore The variable length String array of which will be the lore of the ItemStack.
     * @return The ItemBuilder for further modification.
     */
    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.stream(lore).map(Chat::format).collect(Collectors.toList()));
        return this;
    }

    /**
     * Sets the quantity of which the ItemStack contains.
     * @param amount The amount of items for this ItemStack.
     * @return The ItemBuilder for further modification.
     */
    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Builds the ItemStack from the constituents of the ItemBuilder.
     * @return The ItemStack created by the ItemBuilder.
     */
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

}
