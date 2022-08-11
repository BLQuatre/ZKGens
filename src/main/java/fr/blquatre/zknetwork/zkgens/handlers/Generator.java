package fr.blquatre.zknetwork.zkgens.handlers;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import fr.blquatre.zknetwork.zkgens.utils.Messages;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class Generator {
    private final NamespacedKey idKey = new NamespacedKey(Zkgens.getPlugin(Zkgens.class), "idKey");
    private final String id;
    // Block's info
    private final Material blockMaterial;
    private final int blockModelData;
    private final String blockDisplayName;
    private final List<String> blockLore;
    // Item's info
    private final Material itemMaterial;
    private final int itemModelData;
    private final String itemDisplayName;

    private final List<String> itemLore;

    public Generator(String id, Material blockMaterial, int blockModelData, String blockDisplayName, List<String> blockLore, Material itemMaterial, int itemModelData, String itemDisplayName, List<String> itemLore) {
        this.id = id;
        this.blockMaterial = blockMaterial;
        this.blockModelData = blockModelData;
        this.blockDisplayName = blockDisplayName;
        this.blockLore = blockLore;
        this.itemMaterial = itemMaterial;
        this.itemModelData = itemModelData;
        this. itemDisplayName = itemDisplayName;
        this.itemLore = itemLore;
    }

    public ItemStack getBlock() {
        ItemStack item = new ItemStack(blockMaterial, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(blockModelData);
        meta.displayName(Messages.formatC(blockDisplayName, false));
        meta.lore(Messages.formatListC(blockLore));
        meta.getPersistentDataContainer().set(idKey, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;
    }
    public ItemStack getItem() {
        ItemStack item = new ItemStack(itemMaterial, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(itemModelData);
        meta.displayName(Messages.formatC(itemDisplayName, false));
        meta.lore(Messages.formatListC(itemLore));
        meta.getPersistentDataContainer().set(idKey, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;
    }
}
