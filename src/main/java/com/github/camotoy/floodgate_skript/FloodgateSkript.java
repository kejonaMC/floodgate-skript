package com.github.camotoy.floodgate_skript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class FloodgateSkript extends JavaPlugin {

    private static SkriptAddon addonInstance;

    @Override
    public void onEnable() {
        addonInstance = Skript.registerAddon(this);
        try {
            addonInstance.loadClasses("com.github.camotoy.floodgate_skript", "expressions", "conditions");
        } catch (IOException e) {
            getLogger().warning("IOException caught. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
