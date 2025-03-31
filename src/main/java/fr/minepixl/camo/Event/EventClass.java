package fr.minepixl.camo.Event;

import com.destroystokyo.paper.profile.PlayerProfile;
import fr.minepixl.camo.Main;
import fr.minepixl.camo.Utils.GetSkin;
import fr.minepixl.camo.Utils.SkullItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.SkullMeta;

public class EventClass implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
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
        final Player target = Bukkit.getPlayer(head.getOwner());
        final PlayerProfile playerProfile = player.getPlayerProfile();
        playerProfile.setProperties(GetSkin.getSkin(target.getName()));
        player.setPlayerProfile(playerProfile);
        player.sendMessage("Vous êtes camouflé en " + target.getName());
    }
}
