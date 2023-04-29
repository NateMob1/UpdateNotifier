package org.nathanlovette.updatenotifier.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.nathanlovette.updatenotifier.UpdateNotifier;
import org.nathanlovette.updatenotifier.util.ConfigUtil;

import java.util.*;

public class JoinHandler implements Listener {
    private List<Integer> stringListToIntList(List<String> stringList) {
        List<Integer> intKeys = new ArrayList<>();

        for (String key : stringList) {
            int intKey = Integer.parseInt(key);
            intKeys.add(intKey);
        }

        return intKeys;
    }

    private ConfigUtil config;
    private ConfigUtil playerData;

    public JoinHandler(UpdateNotifier plugin, ConfigUtil config, ConfigUtil playerData) {
        this.config = config;
        this.playerData = playerData;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        FileConfiguration loadedPlayerData = playerData.getConfig();
        FileConfiguration loadedConfig = config.getConfig();
        List<Integer> seenData = loadedPlayerData.getIntegerList(playerUUID + ".seen");

//        Bukkit.getLogger().info("Player " + player.getDisplayName() + "'s seenData reads " + seenData);

        // Get missing messages
        Set<String> messageKeys = Objects.requireNonNull(loadedConfig.getConfigurationSection("messages")).getKeys(false);
        List<String> messageKeyList = new ArrayList<>(messageKeys);
        List<Integer> messageIntKeys = stringListToIntList(messageKeyList);

        List<Integer> missingIntegers = new ArrayList<>(messageIntKeys);
        missingIntegers.removeAll(seenData);

        if (missingIntegers.size() > 0) {

            List<String> missingMessages = new ArrayList<>();
            // get their messages
            for (Integer i : missingIntegers) {
                String selectedMessage = loadedConfig.getString("messages" + "." + i.toString());
                missingMessages.add(selectedMessage);
            }

//            Bukkit.getLogger().info("Player " + player.getDisplayName() + "'s missingMessages reads " + missingMessages);


            if (player.hasPlayedBefore()) {
                String alertMessage = loadedConfig.getString("alert-msg");

                // Send the messages
                if (alertMessage != null) {
                    player.sendMessage(alertMessage);
                }

                List<String> missingDisplayMessages;

                if (missingMessages.size() > 5) {
                    missingDisplayMessages = missingMessages.subList(missingMessages.size() - 5, missingMessages.size());
                } else {
                    missingDisplayMessages = missingMessages;
                }

                for (String message : missingDisplayMessages) {
                    player.sendMessage("- " + message);
                }
            }

            // Now save that those messages were read
            List<Integer> seenDataToSave = new ArrayList<>();
            seenDataToSave.addAll(seenData);
            seenDataToSave.addAll(missingIntegers);
            loadedPlayerData.set(playerUUID + ".seen", seenDataToSave);
            playerData.save();
        }
        else {
            String caughtUpMsg = loadedConfig.getString("caught-up-msg");
            if (caughtUpMsg != null) {
                player.sendMessage(caughtUpMsg);
            }
        }
    }
}
