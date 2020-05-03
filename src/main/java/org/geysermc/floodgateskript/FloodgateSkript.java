package org.geysermc.floodgateskript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public final class FloodgateSkript extends JavaPlugin {

    private static SkriptAddon addonInstance;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("floodgate-bukkit") != null && Bukkit.getPluginManager().getPlugin("Skript") != null) {
            addonInstance = Skript.registerAddon(this);
            try {
                addonInstance.loadClasses("org.geysermc.floodgateskript", "conditions");
            } catch (IOException e) {
                getLogger().warning("IOException caught. Disabling plugin.");
                getServer().getPluginManager().disablePlugin(this);
            }
        } else {
            getLogger().warning("floodgate-bukkit or Skript not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }

    }
}
