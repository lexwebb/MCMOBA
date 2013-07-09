package net.lexwebb.mcmoba.Abilities;

import net.minecraft.server.v1_5_R3.Packet61WorldEvent;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Leap extends Ability {

	public Leap(Player player){
        super(player, "Leap", false, true, 400);

		}		

    @Override
    public void targetEffect(){
        for(LivingEntity target : this.targets){

        }
    }

    @Override
    public void playerEffect(){
        Vector v = new Vector(player.getLocation().getDirection().getX(), 0.3, player.getLocation().getDirection().getZ()); //get rid of y component
        player.setVelocity(player.getVelocity().add(v.add(v)));
        player.teleport(player.getLocation().add(new Vector(0, 0.1, 0)));

        player.getWorld().playSound(player.getLocation(), Sound.GHAST_CHARGE, 1, 1);
        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 35);
        Packet61WorldEvent packet = new Packet61WorldEvent(2001, player.getLocation().getBlockX(), player.getLocation().getBlockY(),  player.getLocation().getBlockZ(), 20, true);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }
}

