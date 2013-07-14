package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import net.minecraft.server.v1_6_R1.Packet61WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_6_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Leap extends Ability {

    int taskID;
    public Leap(Player player){
        super(player, "Leap", false, true, 20, false, false);

    }

    @Override
    public void targetEffect(){
        Vector position = player.getLocation().toVector();
        List<Entity> entities = player.getNearbyEntities(3, 3, 3);
        Vector e, v;

        int force = 4, power = 4;

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity && entity != player) {
                e = entity.getLocation().toVector();
                v = e.subtract(position).normalize().multiply(force/10.0*power);

                if (force != 0) {

                    v.setY(v.getY() + (force/10.0*power));

                } else {

                    v.setY(force/10.0*power);

                }

                if (v.getY() > (force/10.0)) {

                    v.setY(force/10.0);

                }

                entity.setVelocity(v);

                ((LivingEntity) entity).damage(6);
            }

        }
    }

    @Override
    public void playerEffect(){
        Vector v = new Vector(player.getLocation().getDirection().getX(), 0.6, player.getLocation().getDirection().getZ()); //get rid of y component
        player.setVelocity(player.getVelocity().add(v));
        player.teleport(player.getLocation().add(new Vector(0, 0.1, 0)));

        player.getWorld().playSound(player.getLocation(), Sound.GHAST_CHARGE, 1, 1);
        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 35);
        Packet61WorldEvent packet = new Packet61WorldEvent(2001, player.getLocation().getBlockX(), player.getLocation().getBlockY(),  player.getLocation().getBlockZ(), 20, true);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new groundTest(player), 10, 2);
    }

    public class groundTest implements Runnable{

        Player p;
        public groundTest(Player p){
            this.p = p;
        }

        @Override
        public void run() {
            if(inUse){
                if(((Entity) p).isOnGround()){
                    targetEffect();
                    player.sendMessage("Shockwaved");
                    inUse = false;
                    Bukkit.getScheduler().cancelTask(taskID);
                }
            }
        }
    }

}

