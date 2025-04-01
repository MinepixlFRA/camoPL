package fr.minepixl.camo.Commands;

import fr.minepixl.camo.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class testCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (!(sender instanceof final Player player)) return false;
        Main.getInstance().reloadConfig();
        FileConfiguration fc = Main.getInstance().getConfig();
        player.sendMessage(fc.get("uncamo-mode").toString());
        player.sendMessage(fc.get("disable").toString());
        return false;
    }
}
