package com.bungoh.bungohutils.logger;

import org.bukkit.ChatColor;

public enum LogType {

    INFO(ChatColor.GREEN),
    WARNING(ChatColor.RED);

    private ChatColor color;

    LogType(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() { return color; }

}
