package fr.blquatre.zknetwork.zkgens.listeners;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import fr.blquatre.zknetwork.zkgens.utils.FileManager;
import fr.blquatre.zknetwork.zkgens.Generator;
import fr.blquatre.zknetwork.zkgens.utils.Messages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;
import java.util.Objects;

public class BlockEvent implements Listener {
    private final FileConfiguration  dataFile = FileManager.getFileConfiguration("data");

    @EventHandler
    public void GenBlockPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().getPersistentDataContainer().has(Generator.getGenId())) return;

        String genId = item.getItemMeta().getPersistentDataContainer().get(Generator.getGenId(), PersistentDataType.STRING);
        if (genId == null) return;
        if (!Zkgens.getGeneratorsIds().contains(genId)) return;

        Location bLoc = e.getBlockPlaced().getLocation();
        String world = bLoc.getWorld().getName();
        int x = bLoc.getBlockX();
        int y = bLoc.getBlockY();
        int z = bLoc.getBlockZ();
        dataFile.createSection("data." + genId, Map.of("world", world, "x", x, "y", y, "z", z));
        e.getPlayer().sendMessage(Messages.formatC(dataFile.getString("messages.generatorPlaced"), true));
    }

    @EventHandler
    public void GenBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        String world = b.getLocation().getWorld().getName();
        int x = b.getLocation().getBlockX();
        int y = b.getLocation().getBlockY();
        int z = b.getLocation().getBlockZ();

        for (String generator : Objects.requireNonNull(dataFile.getConfigurationSection("data")).getKeys(false)) {
            if (b.getType() != Material.getMaterial(Objects.requireNonNull(dataFile.getString("data." + generator + ".block.material")))) return;
            if (!b.getWorld().getName().equals(dataFile.getString("data." + generator + "block.world"))) return;
            if (x != dataFile.getInt("data." + generator + "block.x")) return;
            if (y != dataFile.getInt("data." + generator + "block.y")) return;
            if (z != dataFile.getInt("data." + generator + "block.z")) return;

            dataFile.set("data." + generator, null);

        }
    }
}