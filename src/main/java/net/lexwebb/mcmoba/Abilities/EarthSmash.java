package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import net.minecraft.server.v1_6_R1.Packet61WorldEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_6_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 14/07/13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class EarthSmash extends Ability{

    public EarthSmash(Player player){
        super(player, "EarthSmash", false, true, 1, false, false);

    }

    @Override
    public void playerEffect(){
        for(int i = 1; i <7;i++){
            List<Location> blocks = circle(player.getLocation(), i, 1, true, false, -1);
            for(Location l :blocks){
                Block b = player.getWorld().getBlockAt(l);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new liftBlock(b), i * 2);
            }
        }

        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 35);
        Packet61WorldEvent packet = new Packet61WorldEvent(2001, player.getLocation().getBlockX(), player.getLocation().getBlockY(),  player.getLocation().getBlockZ(), 20, true);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        player.setVelocity(player.getVelocity().add(new Vector(0, 0.5, 0)));
    }

    public class liftBlock implements Runnable{

        Block b;
        Material m;
        byte bi;
        public liftBlock(Block b){
            this.b = b;
            this.m = b.getType();
            bi = b.getData();
        }

        @Override
        public void run() {
            boolean topBlock = false;
            while(!topBlock){
                if(b.getType() == Material.AIR){
                    if(b.getRelative(BlockFace.DOWN).getTypeId() != 0){
                        m = b.getType();
                        bi = b.getData();
                        b.setType(Material.AIR);
                        topBlock = true;
                    } else {
                        b = b.getRelative(BlockFace.DOWN);
                    }
                } else {
                    if(b.getRelative(BlockFace.UP).getTypeId() == 0){
                        m = b.getType();
                        bi = b.getData();
                        b.setType(Material.AIR);
                        topBlock = true;
                    } else {
                        b = b.getRelative(BlockFace.UP);
                    }
                }
            }

            FallingBlock block = b.getWorld().spawnFallingBlock(b.getLocation(), m, bi);
            block.setVelocity(new Vector(0, 0.2, 0));

            block.getWorld().playSound(block.getLocation(), Sound.DIG_STONE, 5, 1);

            List<Entity> entList = block.getNearbyEntities(2, 2, 2);
            for(Entity entity : entList){
                if (entity instanceof LivingEntity && entity != player) {
                    ((LivingEntity) entity).damage(6, player);
                }
            }
        }
    }

    public static List<Location> circle (Location loc, Integer r, Integer h, Boolean hollow, Boolean sphere, int plus_y) {
        List<Location> circleblocks = new ArrayList<Location>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        for (int x = cx - r; x <= cx +r; x++)
            for (int z = cz - r; z <= cz +r; z++)
                for (int y = (sphere ? cy - r : cy); y < (sphere ? cy + r : cy + h); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < r*r && !(hollow && dist < (r-1)*(r-1))) {
                        Location l = new Location(loc.getWorld(), x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }

        return circleblocks;
    }
}
