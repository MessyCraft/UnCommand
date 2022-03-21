package com.github.messycraft.uncommand.bungee.event;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.messycraft.uncommand.bungee.Main.replaceColor;

public class using implements Listener {

    public Configuration config;
    public Configuration pass;

    @EventHandler
    public void main(ChatEvent e) {
        if (e.getMessage().charAt(0) == '/' && e.getMessage().length() > 1) {
            if (!e.getMessage().equals("/uncmdNONE")) {
                if (e.getSender() instanceof ProxiedPlayer) {
                    ProxiedPlayer p = (ProxiedPlayer) e.getSender();
                    reloadPass();
                    boolean canpass = false;
                    if (pass.contains("(global)")) {
                        if (pass.getStringList("(global)").contains(p.getName())) {
                            canpass = true;
                        }
                    }
                    if (!canpass && pass.contains(p.getServer().getInfo().getName())) {
                        if (pass.getStringList(p.getServer().getInfo().getName()).contains(p.getName())) {
                            if (!e.isProxyCommand()) {
                                canpass = true;
                            }
                        }
                    }
                    if (!canpass) {
                        reloadConfig();
                        if (config.getString("use").equals("blacklist")) {
                            String cmd = e.getMessage().substring(1);
                            List<String> list = config.getStringList("blacklist");
                            List<String> first = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).length() <= cmd.length()) {
                                    first.add(list.get(i));
                                }
                            }
                            for (int i = 0; i < first.size(); i++) {
                                if (cmd.substring(0, first.get(i).length()).equalsIgnoreCase(first.get(i))) {
                                    if (first.get(i).length() == cmd.length() || cmd.charAt(first.get(i).length()) == ' ') {
                                        if (!config.getString("tip").equals("<UNKNOWN>")) {
                                            if (!config.getString("tip").equals("")) {
                                                p.sendMessage(new TextComponent(replaceColor(config.getString("tip"))));
                                            }
                                            e.setCancelled(true);
                                        }
                                        else {
                                            e.setMessage("/uncmdNONE");
                                        }
                                        break;
                                    }
                                }
                            }
                        } else if (config.getString("use").equals("whitelist")) {
                            String cmd = e.getMessage().substring(1);
                            List<String> list = config.getStringList("whitelist");
                            List<String> first = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).length() <= cmd.length()) {
                                    first.add(list.get(i));
                                }
                            }
                            boolean bypass = false;
                            for (int i = 0; i < first.size(); i++) {
                                if (cmd.substring(0, first.get(i).length()).equalsIgnoreCase(first.get(i))) {
                                    if (first.get(i).length() == cmd.length() || cmd.charAt(first.get(i).length()) == ' ') {
                                        bypass = true;
                                        break;
                                    }
                                }
                            }
                            if (!bypass) {
                                if (!config.getString("tip").equals("<UNKNOWN>")) {
                                    if (!config.getString("tip").equals("")) {
                                        p.sendMessage(new TextComponent(replaceColor(config.getString("tip"))));
                                    }
                                    e.setCancelled(true);
                                }
                                else {
                                    e.setMessage("/uncmdNONE");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void reloadConfig() {
        File file = new File("plugins/UnCommand","config.yml");
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadPass() {
        File file = new File("plugins/UnCommand","pass.yml");
        try {
            pass = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
