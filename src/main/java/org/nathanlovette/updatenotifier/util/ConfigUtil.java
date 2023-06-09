package org.nathanlovette.updatenotifier.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigUtil {
    private File file;
    private FileConfiguration config;

    private String path;

    public ConfigUtil(Plugin plugin, String path) {
        this(plugin.getDataFolder().getAbsolutePath() + "/" + path);
        this.path = path;
    }

    public ConfigUtil(String path) {
        this.file = new File(path);
        this.config = YamlConfiguration.loadConfiguration(this.file);
        this.path = path;
    }

    public boolean save() {
        try {
            this.config.save(this.file);
            return true;
        } catch(Exception e) {
         e.printStackTrace();
         return false;
        }
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveDefaultConfig(Plugin plugin) {
        if (!this.getFile().exists()) {
            plugin.saveResource(path, false);
        }
    }
}
