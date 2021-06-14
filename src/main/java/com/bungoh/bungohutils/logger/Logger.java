package com.bungoh.bungohutils.logger;

import org.bukkit.Bukkit;

public class Logger {

    /**
     * Log a message using a specific LogType in Console.
     * @param type The type of log.
     * @param message The log's message.
     */
    public static void log(LogType type, String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(type.getColor() + "[" + type + "]" + message);
    }

}
