package de.scholle.potionmanager.listeners;

import de.scholle.potionmanager.PotionManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.List;

public class PotionListener implements Listener {

    @EventHandler
    public void onBrew(BrewEvent event) {
        List<String> disabledBrewing = PotionManager.getInstance().getDisabledBrewing();
        List<String> disabledBoth = PotionManager.getInstance().getDisabledBoth();

        for (ItemStack result : event.getResults()) {
            if (result == null || result.getType() != Material.POTION) continue;
            if (!(result.getItemMeta() instanceof PotionMeta meta)) continue;

            try {
                PotionData potionData = meta.getBasePotionData();
                PotionType type = potionData.getType();
                if (disabledBrewing.contains(type.name()) || disabledBoth.contains(type.name())) {
                    event.setCancelled(true);
                    return;
                }
            } catch (Exception ignored) {}

            for (PotionEffect effect : meta.getCustomEffects()) {
                PotionEffectType type = effect.getType();
                if (disabledBrewing.contains(type.getName()) || disabledBoth.contains(type.getName())) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!(item.getItemMeta() instanceof PotionMeta meta)) return;

        List<String> disabledUsing = PotionManager.getInstance().getDisabledUsing();
        List<String> disabledBoth = PotionManager.getInstance().getDisabledBoth();

        try {
            PotionData potionData = meta.getBasePotionData();
            PotionType type = potionData.getType();
            if (disabledUsing.contains(type.name()) || disabledBoth.contains(type.name())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou cannot use this potion!");
                return;
            }
        } catch (Exception ignored) {}

        for (PotionEffect effect : meta.getCustomEffects()) {
            PotionEffectType type = effect.getType();
            if (disabledUsing.contains(type.getName()) || disabledBoth.contains(type.getName())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou cannot use this potion!");
                return;
            }
        }
    }

    @EventHandler
    public void onSplash(PotionSplashEvent event) {
        ItemStack item = event.getPotion().getItem();
        if (item == null) return;
        if (!(item.getItemMeta() instanceof PotionMeta meta)) return;

        List<String> disabledUsing = PotionManager.getInstance().getDisabledUsing();
        List<String> disabledBoth = PotionManager.getInstance().getDisabledBoth();

        try {
            PotionData potionData = meta.getBasePotionData();
            PotionType type = potionData.getType();
            if (disabledUsing.contains(type.name()) || disabledBoth.contains(type.name())) {
                event.getAffectedEntities().stream()
                        .filter(e -> e instanceof Player)
                        .map(e -> (Player) e)
                        .forEach(p -> event.setIntensity(p, 0f));
                return;
            }
        } catch (Exception ignored) {}

        for (PotionEffect effect : meta.getCustomEffects()) {
            PotionEffectType type = effect.getType();
            if (disabledUsing.contains(type.getName()) || disabledBoth.contains(type.getName())) {
                event.getAffectedEntities().stream()
                        .filter(e -> e instanceof Player)
                        .map(e -> (Player) e)
                        .forEach(p -> event.setIntensity(p, 0f));
                return;
            }
        }
    }
}
