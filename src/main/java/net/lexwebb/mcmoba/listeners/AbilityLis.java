package net.lexwebb.mcmoba.listeners;

import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
    private void playerChangeHeldItem(PlayerItemHeldEvent e){
        int slot = e.getNewSlot();
        if(Main.instance.playerClass.containsKey(e.getPlayer())){
            switch (slot) {
                case 0: Main.instance.playerClass.get(e.getPlayer()).onAbilityOne(); e.setCancelled(true); break;
                case 1: Main.instance.playerClass.get(e.getPlayer()).onAbilityTwo(); e.setCancelled(true); break;
                case 2: Main.instance.playerClass.get(e.getPlayer()).onAbilityThree(); e.setCancelled(true); break;
                case 3: Main.instance.playerClass.get(e.getPlayer()).onAbilityFour(); e.setCancelled(true); break;
                case 8: Main.instance.menu.open(e.getPlayer());
            }
        } else {

        }
    }

    @EventHandler
    private void onPlayerDamage(EntityDamageEvent e){
        double damage = e.getDamage();

    }

    @EventHandler
    private void playerInteractEvent(PlayerInteractEvent e){
        Action eAction = e.getAction();
        if (eAction.equals(Action.RIGHT_CLICK_AIR) || eAction.equals(Action.RIGHT_CLICK_BLOCK)) {
            Main.instance.playerClass.get(e.getPlayer()).onRightClick();
        } else if(eAction.equals(Action.LEFT_CLICK_AIR) || eAction.equals(Action.LEFT_CLICK_BLOCK)) {
            Main.instance.playerClass.get(e.getPlayer()).onLeftClick();
        }
    }
}
