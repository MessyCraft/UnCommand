package com.github.messycraft.uncommand.bukkit.event;

import com.github.messycraft.uncommand.bukkit.tools;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class using implements Listener {
    Plugin m = tools.mainclass();
    @EventHandler
    public void main(PlayerCommandPreprocessEvent e) throws UnsupportedEncodingException {
        if (!e.getMessage().equals("/") && !e.getPlayer().hasPermission("uncommand.bypass")) {
            if (!e.getMessage().equals("/uncmdNONE")) {
                if (m.getConfig().getString("use").equals("blacklist")) {
                    String cmd = e.getMessage().substring(1);
                    List<String> list = m.getConfig().getStringList("blacklist");
                    List<String> first = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).length() <= cmd.length()) {
                            first.add(list.get(i));
                        }
                    }
                    for (int i = 0; i < first.size(); i++) {
                        if (cmd.substring(0, first.get(i).length()).equalsIgnoreCase(first.get(i))) {
                            if (first.get(i).length() == cmd.length() || cmd.charAt(first.get(i).length()) == ' ') {
                                if (!m.getConfig().getString("tip").equals("<UNKNOWN>")) {
                                    if (!m.getConfig().getString("tip").equals("")) {
                                        tools.SM(e.getPlayer(), m.getConfig().getString("tip"));
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
                } else {
                    String cmd = e.getMessage().substring(1);
                    List<String> list = m.getConfig().getStringList("whitelist");
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
                        if (!m.getConfig().getString("tip").equals("<UNKNOWN>")) {
                            if (!m.getConfig().getString("tip").equals("")) {
                                tools.SM(e.getPlayer(), m.getConfig().getString("tip"));
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
