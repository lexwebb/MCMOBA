package net.lexwebb.mcmoba.listeners;

import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 08/07/13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class AbilityLis extends DefaultListener{

    public AbilityLis(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    private void onInventoryChange(PlayerItemHeldEvent e){
        int slot = e.getNewSlot();
    }
}
