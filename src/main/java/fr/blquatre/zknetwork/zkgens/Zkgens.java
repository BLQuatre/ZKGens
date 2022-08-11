package fr.blquatre.zknetwork.zkgens;

import fr.blquatre.zknetwork.zkgens.commands.GeneratorCommand;
import fr.blquatre.zknetwork.zkgens.file.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Zkgens extends JavaPlugin {

    private static Zkgens plugin;
    private GeneratorManager generatorManager;

    @Override
    public void onEnable() {
        plugin = this;


        generatorManager = new GeneratorManager(this);
        registerCommands();

    }
    @Override
    public void onDisable() {
    }

    public static Zkgens getPlugin() {
        return plugin;
    }
    public GeneratorManager getGeneratorManager() {
        return generatorManager;
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("generator")).setExecutor(new GeneratorCommand(this));
    }

    private void loadFiles() {
        saveDefaultConfig();
        FileManager.createFile("data");
    }


}