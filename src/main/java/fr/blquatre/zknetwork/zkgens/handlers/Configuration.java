package fr.blquatre.zknetwork.zkgens.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;

import fr.blquatre.zknetwork.zkgens.Zkgens;

public class Configuration {

    private final Zkgens plugin;
    private FileConfiguration config;

    // Configuration constructor
    public Configuration(Zkgens plugin) {
        this.plugin = plugin;
        reload();
    }

    // Reload the config file
    public void reload() {
        config = plugin.getConfig();
    }

    // Get all fishing lines from the config file
    public List<Generator> getAllGenerators() {
        List<Generator> generators = new ArrayList<>();
        for (String key : Objects.requireNonNull(config.getConfigurationSection("generators")).getKeys(false)) {
            generators.add(new Generator(key,
                    Material.getMaterial(Objects.requireNonNull(config.getString("generators." + key + ".block.material")).toUpperCase()),
                    config.getInt("generators." + key + ".block.modelData"),
                    config.getString("generators." + key + ".block.displayName"),
                    config.getStringList("generators." + key + ".block.lore"),
                    Material.getMaterial(Objects.requireNonNull(config.getString("generators." + key + ".item.material")).toUpperCase()),
                    config.getInt("generators." + key + ".item.modelData"),
                    config.getString("generators." + key + ".item.displayName"),
                    config.getStringList("generators." + key + ".item.lore")));
        }
        return generators;
    }
}