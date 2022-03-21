package com.github.messycraft.uncommand.bungee;

import com.github.messycraft.uncommand.bungee.event.using;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Main extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[UnCommand] Plugin has been loaded");
        System.out.println("[UnCommand] Do you know? Option \"use\" in config must be \"blacklist\" or \"whitelist\".");
        saveDefaultConfig();
        saveDefaultPass();
        getProxy().getPluginManager().registerListener(this,new using());

        Metrics metrics = new Metrics(this,14663);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[UnCommand] Plugin has been unloaded");
    }

    public void saveDefaultConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in,file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveDefaultPass() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"pass.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("pass.yml")) {
                Files.copy(in,file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String replaceColor(String s) {
        return s.replaceAll("&","§");
    }
}
