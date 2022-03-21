package com.github.messycraft.uncommand.bukkit;

import com.github.messycraft.uncommand.bukkit.command.maincmd;
import com.github.messycraft.uncommand.bukkit.event.using;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public final class UnCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin has been loaded.");
        saveDefaultConfig();
        reloadConfig();
        String str_use = getConfig().getString("use");
        if (str_use.equals("blacklist") || str_use.equals("whitelist"));
        else {
            System.out.println("");
            System.out.println("Config ERROR");
            System.out.println("Option \"use\" in config must be \"blacklist\" or \"whitelist\" !");
            System.out.println("Plugin will be closed.");
            System.out.println("");
            new JavaPluginLoader(getServer()).disablePlugin(this);
        }
        getCommand("uncommand").setExecutor(new maincmd());
        getCommand("uncommand").setTabCompleter(new maincmd());
        getServer().getPluginManager().registerEvents(new using(),this);

        Metrics metrics = new Metrics(this,14661);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin has been unloaded.");
    }
}
