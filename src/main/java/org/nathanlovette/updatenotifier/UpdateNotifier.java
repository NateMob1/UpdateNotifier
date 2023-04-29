package org.nathanlovette.updatenotifier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nathanlovette.updatenotifier.handlers.JoinHandler;

public final class UpdateNotifier extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World!");

        new JoinHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Goodbye World!");
    }
}
