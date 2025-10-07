package de.scholle.potionmanager;

import de.scholle.potionmanager.listeners.PotionListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PotionManager extends JavaPlugin {

    private static PotionManager instance;
    private List<String> disabledBrewing;
    private List<String> disabledUsing;
    private List<String> disabledBoth;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        disabledBrewing = getConfig().getStringList("disabled-brewing");
        disabledUsing = getConfig().getStringList("disabled-using");
        disabledBoth = getConfig().getStringList("disabled-both");
        getServer().getPluginManager().registerEvents(new PotionListener(), this);
        getLogger().info("PotionManager enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PotionManager disabled!");
    }

    public static PotionManager getInstance() {
        return instance;
    }

    public List<String> getDisabledBrewing() {
        return disabledBrewing;
    }

    public List<String> getDisabledUsing() {
        return disabledUsing;
    }

    public List<String> getDisabledBoth() {
        return disabledBoth;
    }
}
