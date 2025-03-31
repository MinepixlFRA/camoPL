package fr.minepixl.camo.Utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.entity.Player;

public class ResetSkin {
    public static void resetSkin(Player player) {
        PlayerProfile playerProfile = player.getPlayerProfile();
        playerProfile.setProperties(GetSkin.getSkin(player.getName()));
        player.setPlayerProfile(playerProfile);
    }
}
