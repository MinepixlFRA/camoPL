package fr.minepixl.camo;

import fr.minepixl.camo.Commands.CamoCmd;
import fr.minepixl.camo.Commands.testCmd;
import fr.minepixl.camo.Event.EventClass;
import fr.minepixl.camo.TabCompleter.camoTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main Instance;

    @Override
    public void onEnable() {
        super.onEnable();
        Instance = this;
        getLogger().info("Plugin lancé !");
        this.saveDefaultConfig();
        this.reloadConfig();
        getServer().getPluginManager().registerEvents(new EventClass(), this);
        getCommand("test").setExecutor(new testCmd());
        getCommand("camo").setTabCompleter(new camoTabCompleter());
        getCommand("camo").setExecutor(new CamoCmd());
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
