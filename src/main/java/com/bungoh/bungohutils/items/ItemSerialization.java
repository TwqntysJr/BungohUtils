package com.bungoh.bungohutils.items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ItemSerialization {
    /**
     * Converts player inv to string[] of base64.
     *
     * @param pinv player's inv to turn into base64 data
     * @return Array of string [Regular items, armour items]
     */
    public static String[] playerInvToBase64(PlayerInventory pinv){
        String content = toBase64(pinv);
        String armour = itemStackArrayToBase64(pinv.getArmorContents());
        return new String[] {content, armour};
    }

    /**
     * Serialize an inventory to base64 Data
     *
     * @param inv that gets serialized
     * @return Base 64 data of the provided inventory
     * @throws IllegalStateException
     */
    public static String toBase64(Inventory inv) throws IllegalStateException{
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inv.getSize());
            for(int i = 0; i<inv.getSize(); i++){
                dataOutput.writeObject(inv.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to write item stacks.", e);
        }
    }

    /**
     * Serialize an ItemStack array to base64 data.
     *
     * @param items turns into encoded string
     * @return Base64 String
     * @throws IllegalStateException
     */
    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(items.length);

            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save items.", e);
        }
    }

    /**
     * Get an inventory from an encoded base64 string.
     *
     * @param d Base64 data
     * @return inventory
     * @throws IOException
     */
    public static Inventory fromBase64(String d) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(d));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inv = Bukkit.getServer().createInventory(null, dataInput.readInt());

            for(int i = 0; i<inv.getSize(); i++){ // set all the items in the inventory from the bukkit object input stream
                inv.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close(); // Close the bukkit object input stream
            return inv;
        } catch (ClassNotFoundException e) {
            throw new IOException("Error whilst decoding class type.", e);
        }
    }

    /**
     * Get an array of ItemStacks from Base64 String.
     *
     * @param d Base64 data
     * @return ItemStack
     * @throws IOException
     */
    public static ItemStack[] itemStackArrayFromBase64(String d) throws IOException{
        try{
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(d));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            for (int i = 0; i < items.length; i++) { // Read serialized inv
                items[i] = (ItemStack) dataInput.readObject();
            }
            dataInput.close(); // Close the bukkit object input stream
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Error whilst decoding class type.", e);
        }
    }

}
