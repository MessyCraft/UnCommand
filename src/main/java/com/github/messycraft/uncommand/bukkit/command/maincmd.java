package com.github.messycraft.uncommand.bukkit.command;

import com.github.messycraft.uncommand.bukkit.tools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.util.ArrayList;
import java.util.List;

public class maincmd implements CommandExecutor, TabExecutor {
    Plugin m = tools.mainclass();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            tools.SM(sender,"&3&lUnCommand &bv" + tools.v);
            tools.SM(sender,"&eUsage: &6/uncmd help");
        }
        else {
            if (args[0].equals("help")) {
                tools.SM(sender,"&8&l--------------------");
                tools.SM(sender,"&e/uncmd help: &6Show this help.");
                tools.SM(sender,"&e/uncmd reload: &6Reload config.");
                tools.SM(sender,"&e/uncmd check: &6Show the whitelist and blacklist.");
                tools.SM(sender,"&8&l--------------------");
            }
            else if (args[0].equals("reload")) {
                if (sender.hasPermission("uncommand.reload")) {
                    tools.SM(sender,"&e&l[UnCommand] &6Config start reload...");
                    m.saveDefaultConfig();
                    m.reloadConfig();
                    String str_use = m.getConfig().getString("use");
                    if (str_use.equals("blacklist") || str_use.equals("whitelist")) {
                        tools.SM(sender,"&e&l[UnCommand] &6Config reload complete!");
                    }
                    else {
                        System.out.println("");
                        System.out.println("System log << (" + sender.getName() + ") reloaded config! But there are some problems...");
                        System.out.println("Config ERROR");
                        System.out.println("Option \"use\" in config must be \"blacklist\" or \"whitelist\" !");
                        System.out.println("Plugin will be closed.");
                        System.out.println("");
                        tools.SM(sender,"");
                        tools.SM(sender,"&c&lConfig ERROR");
                        tools.SM(sender,"&cOption \"use\" in config must be \"blacklist\" or \"whitelist\" !");
                        tools.SM(sender,"&cPlugin will be closed.");
                        tools.SM(sender,"");
                        new JavaPluginLoader(m.getServer()).disablePlugin(m);
                    }
                }
                else {
                    tools.noPermission(sender);
                }
            }
            else if (args[0].equals("check")) {
                if (sender.hasPermission("uncommand.check")) {
                    List<String> whitelist = m.getConfig().getStringList("whitelist");
                    List<String> blacklist = m.getConfig().getStringList("blacklist");
                    tools.SM(sender,"");
                    tools.SM(sender,"&8&l--------------------");
                    tools.SM(sender,"&eUsing: &6" + m.getConfig().getString("use"));
                    tools.SM(sender,"&8&l--------------------");
                    tools.SM(sender,"&eCommandWhitelist: ");
                    for (int i=0;i<whitelist.size();i++) {
                        tools.SM(sender,"&7  - &6/" + whitelist.get(i));
                    }
                    tools.SM(sender,"&8&l--------------------");
                    tools.SM(sender,"&eCommandBlacklist: ");
                    for (int i=0;i<blacklist.size();i++) {
                        tools.SM(sender,"&7  - &6/" + blacklist.get(i));
                    }
                    tools.SM(sender,"&8&l--------------------");
                    tools.SM(sender,"");
                }
                else {
                    tools.noPermission(sender);
                }
            }
            else {
                tools.SM(sender,"&3&lUnCommand &bv1.1");
                tools.SM(sender,"&eUsage: &6/uncmd help");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("help");
            list.add("reload");
            list.add("check");
            return list;
        }
        return null;
    }
}
