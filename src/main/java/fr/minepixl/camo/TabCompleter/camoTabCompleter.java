package fr.minepixl.camo.TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class camoTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String @NotNull [] args) {
        if (args.length == 1) return List.of("give", "uncamo", "config");
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            ArrayList<String> list = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("config")) return List.of("uncamo", "disable");
        if (args.length == 3 && args[1].equalsIgnoreCase("uncamo")) return List.of("killed", "damage", "timed", "help");
        if (args.length == 3 && args[1].equalsIgnoreCase("disable")) return List.of("false", "true", "unactive", "help");
        return List.of();
    }
}
