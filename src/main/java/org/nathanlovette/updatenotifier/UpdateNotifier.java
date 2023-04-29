package org.nathanlovette.updatenotifier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nathanlovette.updatenotifier.handlers.JoinHandler;
import org.nathanlovette.updatenotifier.util.ConfigUtil;

import java.util.Objects;
import java.util.logging.Level;

public final class UpdateNotifier extends JavaPlugin {
    ConfigUtil config = new ConfigUtil(this, "config.yml");
    ConfigUtil playerData = new ConfigUtil(this, "playerData.yml");

    @Override
    public void onEnable() {
        config.saveDefaultConfig(this);
        playerData.saveDefaultConfig(this);
        // Plugin startup logic
//        Bukkit.getLogger().info("Hello World!");

        try {
            Objects.requireNonNull(config.getConfig().getConfigurationSection("messages")).getKeys(false);
            new JoinHandler(this, config, playerData);
        } catch (NullPointerException e) {
            Bukkit.getLogger().log(Level.WARNING, "No message key values found.");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
//        Bukkit.getLogger().info("Goodbye World!");
    }
}
