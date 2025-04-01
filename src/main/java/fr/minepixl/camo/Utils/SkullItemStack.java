package fr.minepixl.camo.Utils;

import fr.minepixl.camo.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

public class SkullItemStack {
    public static ItemStack getCamoSkullItemStack(String targetName) {
        ItemStack targetSkull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta targetSkullMeta = (SkullMeta) targetSkull.getItemMeta();
        targetSkullMeta.setOwner(targetName);
        targetSkullMeta.setDisplayName(RandomUtils.rdmBelleCouleurs() + targetName + " > Clique droit pour se déguisier <");
        NamespacedKey nsk = new NamespacedKey(Main.getInstance(), "isCamoHead");
        targetSkullMeta.getPersistentDataContainer().set(nsk, PersistentDataType.BOOLEAN, true);
        Bukkit.getPlayer("MinepixlFR").sendMessage(String.valueOf(nsk));
        targetSkull.setItemMeta(targetSkullMeta);
        return targetSkull;
    }
}
