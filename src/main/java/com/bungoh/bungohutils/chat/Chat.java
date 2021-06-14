package com.bungoh.bungohutils.chat;

import org.bukkit.ChatColor;

public class Chat {

    /**
     * Format a message with basic Spigot chat colors using the '&' character.
     * @param message The message to be color coded.
     * @return A formatted, colored String.
     */
    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
