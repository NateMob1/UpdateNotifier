package org.nathanlovette.updatenotifier.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.nathanlovette.updatenotifier.UpdateNotifier;

public class JoinHandler implements Listener {
    public JoinHandler(UpdateNotifier plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("SLAY SLAY SLAY");
    }
}
