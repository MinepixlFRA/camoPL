package fr.minepixl.camo.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    public static String rdmBelleCouleurs() {
        ArrayList<String> belleCouleurs = new ArrayList<>();
        belleCouleurs.addAll(List.of("§e", "§c", "§a", "§3", "§b", "§d", "§f"));
        Random rdm = new Random();
        return belleCouleurs.get(rdm.nextInt(belleCouleurs.size()));
    }
}
