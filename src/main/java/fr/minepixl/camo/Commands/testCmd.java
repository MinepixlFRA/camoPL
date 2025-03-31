package fr.minepixl.camo.Commands;

import fr.minepixl.camo.Utils.RandomUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (!(sender instanceof final Player player)) return false;
        player.sendMessage("coucouioufioksd");
        return false;
    }
}
