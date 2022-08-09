package fr.blquatre.zknetwork.zkgens.commands;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GeneratorCommand implements CommandExecutor {
    private final Zkgens plugin;

    public GeneratorCommand(Zkgens plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, String[] args) {
        if (sender instanceof Player p && sender.isOp()) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length >= 3) {
                        Material m = Material.matchMaterial(args[1]);
                        if (m == null) {
                            sender.sendMessage(args[1] + " is not an item material!");
                            return false;
                        }

                        if (this.isDouble(args[2])) {
                            Block b = p.getTargetBlock(null, 3);
                            if (!b.getType().isSolid()) {
                                p.sendMessage(ChatColor.RED + "You must look at a solid block!");
                                return false;
                            }

                            this.plugin.getGeneratorManager().addGenerator(b, new ItemStack(m), Double.parseDouble(args[2]));
                            return true;
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "/generator [set/remove] [itemType] [seconds]");
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    Block b = p.getTargetBlock(null, 3);
                    if (!b.getType().isSolid()) {
                        p.sendMessage(ChatColor.RED + "You must look at a solid block!");
                        return false;
                    }

                    this.plugin.getGeneratorManager().removeGenerator(b);
                    return false;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "/generator [set/remove] [itemType] [seconds]");
            }
        }

        return false;
    }

    private boolean isDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }
}