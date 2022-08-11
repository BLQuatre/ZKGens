package fr.blquatre.zknetwork.zkgens;

import fr.blquatre.zknetwork.zkgens.commands.GiveGeneratorCommand;
import fr.blquatre.zknetwork.zkgens.utils.FileManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Zkgens extends JavaPlugin {
    private static FileConfiguration config;
    private static final Map<String, Generator> GENERATORS = new HashMap<>();

    @Override
    public void onEnable() {
        registerCommands();
        registerGenerators();
        loadFiles();

        GeneratorManager generatorManager = new GeneratorManager(this);
    }
    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("givegenerator")).setExecutor(new GiveGeneratorCommand());
    }
    private static void registerGenerators() {
        for (String key : Objects.requireNonNull(config.getConfigurationSection("generators")).getKeys(false)) {
            registerGenerator(new Generator(key,
                    config.getInt("generators." + key + ".price"),
                    config.getInt("generators." + key + ".time"),
                    Material.getMaterial(Objects.requireNonNull(config.getString("generators." + key + ".block.material")).toUpperCase()),
                    config.getInt("generators." + key + ".block.modelData"),
                    config.getString("generators." + key + ".block.displayName"),
                    config.getStringList("generators." + key + ".block.lore"),
                    Material.getMaterial(Objects.requireNonNull(config.getString("generators." + key + ".item.material")).toUpperCase()),
                    config.getInt("generators." + key + ".item.modelData"),
                    config.getString("generators." + key + ".item.displayName"),
                    config.getStringList("generators." + key + ".item.lore")));
        }
    }
    public static void registerGenerator(Generator generator) {
        GENERATORS.put(generator.getId(), generator);
    }
    public Generator getGenerator(String id) {
        return GENERATORS.get(id);
    }
    public static Set<String> getGeneratorsIds() {
        return GENERATORS.keySet();
    }

    private void loadFiles() {
        saveDefaultConfig();
        FileManager.createFile("data");
        FileManager.getFileConfiguration("data").options().copyDefaults(true);
        reload(false);
    }

    public static void reload(boolean reloadGenerators) {
        config = Zkgens.getPlugin(Zkgens.class).getConfig();
        FileManager.reloadFile("data");
        if (reloadGenerators) {
            GENERATORS.clear();
            registerGenerators();
        }
    }
}