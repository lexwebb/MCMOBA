package net.lexwebb.mcmoba.listeners;

import net.lexwebb.mcmoba.Abilities.Events.EntityCollideEvent;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import net.lexwebb.mcmoba.defaults.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 04/08/13
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public class EntityLis extends DefaultListener {
    public EntityLis(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof LivingEntity){
            e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if(e.getPlayer().getWorld().getBlockAt(e.getPlayer().getLocation()).getType().isSolid()){
            e.getPlayer().teleport(e.getPlayer().getLocation().add(0,1,0));
        }
    }

    @EventHandler
    public void blockLandEvent(EntityChangeBlockEvent e){
        if(Main.instance.thrownBlock.contains(e.getEntity())){
            //Main.instance.getServer().broadcastMessage("Falling Block Destroyed");  //debug
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 0, false);
            Location loc = e.getEntity().getLocation();
            e.setCancelled(true);


            Utilities util = new Utilities();

            List<Entity> entList = util.getNearbyLocationEntities(Main.instance.players.get(0), loc, 3);
            for(Entity entity : entList){
                if(entity instanceof LivingEntity)
                    ((LivingEntity) entity).damage(6);
            }

            Main.instance.thrownBlock.remove(e.getEntity());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent e){
        Main.instance.playerClass.get(e.getPlayer()).setItemSlots();
    }

    @EventHandler
    public void onEntityCollide(EntityCollideEvent e){
        Bukkit.getServer().broadcastMessage(e.getEntity1().getEntityId() + " collided with " + e.getEntity2().getEntityId());
    }

}
