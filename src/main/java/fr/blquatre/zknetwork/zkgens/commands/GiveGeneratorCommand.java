package fr.blquatre.zknetwork.zkgens.commands;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import fr.blquatre.zknetwork.zkgens.Generator;
import fr.blquatre.zknetwork.zkgens.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveGeneratorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length < 1 || args.length > 2) {
            commandSender.sendMessage(Messages.format("&7Liste des générateurs: &c" + String.join("&7, &c", Zkgens.getGeneratorsIds()) + "&7.", true));
            return false;
        }

        Generator generator = Zkgens.getPlugin(Zkgens.class).getGenerator(args[0]);
        if (generator == null) {
            commandSender.sendMessage(Messages.format("&7Liste des baguettes: &c" + String.join("&7, &c", Zkgens.getGeneratorsIds()) + "&7.", true));
            return false;
        }

        if (args.length == 1) {
            if (commandSender instanceof Player p) {

                p.getInventory().addItem(generator.getItem());
                p.sendMessage(Messages.format("&7Tu as reçu 1 " + generator.getBlockDisplayName() + "&7.", true));
            } else {
                commandSender.sendMessage(Messages.format("&7Spécifie un joueur !", true));
                return false;
            }
        } else { // args == 2
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                commandSender.sendMessage(Messages.format("&7Spécifie un joueur !", true));
                return false;
            }

            target.getInventory().addItem(generator.getItem());
            target.sendMessage(Messages.format("&7Tu as reçu 1 " + generator.getBlockDisplayName() + "&7.", true));
            commandSender.sendMessage(Messages.format("&7Tu as donné 1 " + generator.getBlockDisplayName() + " &7à &e" + target.getName() + "&7.", true));
        }
        return true;
    }
}