package fr.minepixl.camo;

import fr.minepixl.camo.Commands.testCmd;
import fr.minepixl.camo.Event.EventClass;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main Instance;

    @Override
    public void onEnable() {
        super.onEnable();
        Instance = this;
        getLogger().info("Plugin lancé !");
        getServer().getPluginManager().registerEvents(new EventClass(), this);
        getCommand("test").setExecutor(new testCmd());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("Plugin fermé.");
    }

    public static Main getInstance() {
        return Instance;
    }
}
