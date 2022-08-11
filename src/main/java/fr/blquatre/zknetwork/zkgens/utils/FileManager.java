package fr.blquatre.zknetwork.zkgens.utils;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static final Zkgens PLUGIN = Zkgens.getPlugin(Zkgens.class);

    public static void createFile(String fileName) {
        if (!PLUGIN.getDataFolder().exists()) {
            PLUGIN.getDataFolder().mkdir();
        }
        File file = new File(PLUGIN.getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static File getFile(String fileName) {
        return new File(PLUGIN.getDataFolder(), fileName + ".yml");
    }
    public static FileConfiguration getFileConfiguration(String fileName) {
        return YamlConfiguration.loadConfiguration(getFile(fileName + ".yml"));
    }
    public static void saveFile(String fileName) {
        try {
            getFileConfiguration(fileName).save(getFile(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void reloadFile(String fileName) {
        YamlConfiguration.loadConfiguration(getFile(fileName));
    }
}