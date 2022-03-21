package com.github.messycraft.uncommand.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public final class tools {

    public static final double v = 1.4;

    public static Plugin mainclass() {
        return UnCommand.getProvidingPlugin(UnCommand.class);
    }

    public static String replaceColor(String s) {
        return s.replaceAll("&","§");
    }

    public static void SM(CommandSender sender, String msg) {
        msg = replaceColor(msg);
        sender.sendMessage(msg);
    }

    public static void noPermission(CommandSender sender) {
        SM(sender,"&cYou don't have permission");
    }

}
