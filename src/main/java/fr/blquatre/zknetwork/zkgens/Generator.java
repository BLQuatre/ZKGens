package fr.blquatre.zknetwork.zkgens;

import fr.blquatre.zknetwork.zkgens.utils.Messages;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class Generator {
    private static final NamespacedKey genId = new NamespacedKey(Zkgens.getPlugin(Zkgens.class), "genId");
    public static NamespacedKey getGenId() {
        return genId;
    }
    private final String id;
    private final int price;
    private final int time;
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

    public Generator(String id, int price, int time, Material blockMaterial, int blockModelData, String blockDisplayName, List<String> blockLore, Material itemMaterial, int itemModelData, String itemDisplayName, List<String> itemLore) {
        this.id = id;
        this.price = price;
        this.time = time;
        this.blockMaterial = blockMaterial;
        this.blockModelData = blockModelData;
        this.blockDisplayName = blockDisplayName;
        this.blockLore = blockLore;
        this.itemMaterial = itemMaterial;
        this.itemModelData = itemModelData;
        this. itemDisplayName = itemDisplayName;
        this.itemLore = itemLore;
    }

    public String getId() {
        return id;
    }
    public String getBlockDisplayName() {
        return blockDisplayName;
    }
    public int getPrice() {
        return price;
    }
    public int getTime() {
        return time;
    }

    public ItemStack getBlock() {
        ItemStack item = new ItemStack(blockMaterial, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(blockModelData);
        meta.displayName(Messages.formatC(blockDisplayName, false));
        meta.lore(Messages.formatListC(blockLore));
        meta.getPersistentDataContainer().set(genId, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;
    }
    public ItemStack getItem() {
        ItemStack item = new ItemStack(itemMaterial, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(itemModelData);
        meta.displayName(Messages.formatC(itemDisplayName, false));
        meta.lore(Messages.formatListC(itemLore));
        meta.getPersistentDataContainer().set(genId, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;
    }
}