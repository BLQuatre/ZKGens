package fr.blquatre.zknetwork.zkgens.commands;

import fr.blquatre.zknetwork.zkgens.Zkgens;
import fr.blquatre.zknetwork.zkgens.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Zkgens.reload(true);
        commandSender.sendMessage(Messages.format("&aReload des fichiers effectués", true));
        return true;
    }
}
