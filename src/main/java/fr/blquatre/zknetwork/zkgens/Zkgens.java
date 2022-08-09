package fr.blquatre.zknetwork.zkgens;

import fr.blquatre.zknetwork.zkgens.commands.GeneratorCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.management.BufferPoolMXBean;
import java.util.Objects;

public final class Zkgens extends JavaPlugin {
    private GeneratorManager generatorManager;

    public void onEnable() {
        generatorManager = new GeneratorManager(this);
        registerCommands();
        Bukkit.
        getLogger().fine("§aPlugin enabled!");

    }
    @Override
    public void onDisable() {
        getLogger().fine("§aPlugin enabled!");
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("generator")).setExecutor(new GeneratorCommand(this));
    }

    public GeneratorManager getGeneratorManager() {
        return generatorManager;
    }
}