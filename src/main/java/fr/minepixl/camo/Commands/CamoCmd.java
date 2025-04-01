package fr.minepixl.camo.Commands;

import fr.minepixl.camo.Main;
import fr.minepixl.camo.Utils.ResetSkin;
import fr.minepixl.camo.Utils.SkullItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CamoCmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§c/camo ( give | uncamo | config ) [ args ]");
            return false;
        }
        switch (args[0]) {
            case "give":
                if (!(sender instanceof final Player player)) return false;
                if (!(args.length >= 2)) {
                    player.sendMessage("§c/camo give (player's name)");
                    return false;
                }
                if (!(args[1].length() >= 3 && args[1].length() <= 16)) {
                    player.sendMessage("§cNobody have this name.");
                    return false;
                }
                player.getInventory().addItem(SkullItemStack.getCamoSkullItemStack(args[1]));
                return false;
            case "uncamo":
                if (!(sender instanceof final Player player)) return false;
                ResetSkin.resetSkin(player);
                player.sendMessage("Votre skin a été remis a son état d'origine.");
                return false;

//                /camo config uncamo

            case "config":
                if (args.length < 2) {
                    sender.sendMessage("§c/camo config ( uncamo | disable )");
                    return false;
                }
                switch (args[1]) {
                    case "uncamo":
                        if (args.length < 3) {
                            sender.sendMessage("§c/camo config uncamo ( damage | killed | timed | help )");
                            return false;
                        }
                        switch (args[2]) {
                            case "damage":
                                Main.getInstance().getConfig().set("uncamo-mode", "damage");
                                Main.getInstance().saveConfig();
                                sender.sendMessage(Main.getInstance().getConfig().getString("uncamo-mode"));
                                return false;
                            case "killed":
                                Main.getInstance().getConfig().set("uncamo-mode", "killed");
                                Main.getInstance().saveConfig();
                                sender.sendMessage(Main.getInstance().getConfig().getString("uncamo-mode"));
                                return false;
                            case "timed":
                                Main.getInstance().getConfig().set("uncamo-mode", "timed");
                                Main.getInstance().saveConfig();
                                sender.sendMessage(Main.getInstance().getConfig().getString("uncamo-mode"));
                                return false;
                            case "help":
                                sender.sendMessage("§f§l--------------------------------------------");
                                sender.sendMessage("§f§l- §r§6killed §f§l: §r§6(§f§lDefault§r§6) The player loose his camo only when he die.");
                                sender.sendMessage("§f§l- §r§6damage §f§l: The player loose his camo when he take a damage (the source of the damage dosen't  matter).");
                                sender.sendMessage("§f§l- §r§6timed §f§l: The player loose his camo when the countdown is over.");
                                sender.sendMessage("§f§l--------------------------------------------");
                                return false;
                            default:
                                sender.sendMessage("§c/camo config uncamo ( damage | killed | timed | help )");
                                return false;
                        }
                    case "disable":
                        if (args.length < 3) {
                            sender.sendMessage("§c/camo config disable ( false | true | unactive )");
                            return false;
                        }
                        switch (args[2]) {
                            case "false":
                                Main.getInstance().getConfig().set("disable", "false");
                                Main.getInstance().saveConfig();
                                sender.sendMessage(Main.getInstance().getConfig().getString("disable"));
                                return false;
                            case "true":
                                Main.getInstance().getConfig().set("disable", "true");
                                Main.getInstance().saveConfig();
                                sender.sendMessage(Main.getInstance().getConfig().getString("disable"));
                                return false;
                            case "unactive":
                                Main.getInstance().getConfig().set("disable", "unactive");
                                Main.getInstance().saveConfig();
                                sender.sendMessage(Main.getInstance().getConfig().getString("disable"));
                                return false;
                            case "help":
                                sender.sendMessage("§f§l--------------------------------------------");
                                sender.sendMessage("§f§l- §r§6false §f§l: §r§6(§f§lDefault§r§6) The plugin work properly.");
                                sender.sendMessage("§f§l- §r§6true §f§l: The plugin don't work and every camo heads get deleted.");
                                sender.sendMessage("§f§l- §r§6unactive §f§l: The plugins don't work but camo heads is not deleted.");
                                sender.sendMessage("§f§l--------------------------------------------");
                                return false;
                            default:
                                sender.sendMessage("§c/camo config disable ( false | true | unactive )");
                                return false;
                        }
                    default:
                        sender.sendMessage("§c/camo config ( uncamo | disable )");
                        return false;
                }
            default:
                sender.sendMessage("§c/camo ( give | uncamo | config ) [ args ]");
                return false;
        }
    }
}
