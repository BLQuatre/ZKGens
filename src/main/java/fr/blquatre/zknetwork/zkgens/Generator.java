package fr.blquatre.zknetwork.zkgens;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Generator extends BukkitRunnable {
    private final Block b;
    private final ItemStack item;
    private double time;
    private final double timeX;

    public Generator(Block b, ItemStack item, double time) {
        this.b = b;
        this.item = item;
        this.time = time;
        this.timeX = time;
    }

    public void run() {
        if (this.time <= 0.0D) {
            this.b.getWorld().dropItem(this.b.getLocation().add(0.5D, 1.0D, 0.5D), this.item);
            this.time = this.timeX;
        }

        this.time -= 0.05D;
    }
}