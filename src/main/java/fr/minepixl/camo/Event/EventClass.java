package fr.minepixl.camo.Event;

import com.destroystokyo.paper.profile.PlayerProfile;
import fr.minepixl.camo.Main;
import fr.minepixl.camo.Utils.GetSkin;
import fr.minepixl.camo.Utils.ResetSkin;
import fr.minepixl.camo.Utils.SkullItemStack;
import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

public class EventClass implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        ResetSkin.resetSkin(event.getPlayer());
        if (!(event.getDamageSource().getDirectEntity() instanceof final Player killer)) return;
        Player target = event.getPlayer();
        event.setDeathMessage("");
        target.getWorld().dropItem(target.getLocation(), SkullItemStack.getCamoSkullItemStack(target.getName()));
    }

    @EventHandler
    public void interactToSpoof(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) return;
        if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.PLAYER_HEAD) return;
        SkullMeta head = (SkullMeta) event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
        if (!(head.getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "isCamoHead")))) return;
        event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
        final Player player = event.getPlayer();
        final String target = head.getOwner();
        final PlayerProfile playerProfile = player.getPlayerProfile();
        playerProfile.setProperties(GetSkin.getSkin(target));
        player.setPlayerProfile(playerProfile);
        player.sendMessage("Vous êtes camouflé en " + target);
    }

//    @EventHandler
//    public void placeIsCamoHead(BlockPlaceEvent event) {
//        // Récupère l'item dans la main du joueur
//        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
//        Bukkit.getPlayer("MinepixlFR").sendMessage("String.valueOf(camoHeadKey)");
//
//        // Vérifie si l'item est une tête de joueur
//        if (itemInHand.getType() != Material.PLAYER_HEAD) return;
//
//        // Récupère les PersistentDataContainer de l'item
//        PersistentDataContainerView persistentData = itemInHand.getPersistentDataContainer();
//
//        // Vérifie si l'item a le tag "isCamoHead"
//        NamespacedKey camoHeadKey = new NamespacedKey(Main.getInstance(), "isCamoHead");
//        Bukkit.getPlayer("MinepixlFR").sendMessage(String.valueOf(camoHeadKey));
//        if (persistentData.has(camoHeadKey, PersistentDataType.BOOLEAN)) {
//            // Si l'item a le tag "isCamoHead", annule l'événement pour empêcher le placement
//            event.setCancelled(true);
//            event.getPlayer().sendMessage("§cVous ne pouvez pas poser cet objet !");  // Message pour le joueur
//        }
//    }
}
