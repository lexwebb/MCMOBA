package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import net.minecraft.server.v1_6_R1.Packet61WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_6_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class EarthSlam extends Ability {

    public EarthSlam(Player player){
        super(player, "EarthSlam", false, true, 1, false, true);

    }

    @Override
    public void targetEffect(){

    }

    @Override
    public void playerEffect(){
        int length = 10;

        Vector v = player.getLocation().getDirection();
        v.setY(0);

        BlockIterator bli = new BlockIterator(player.getWorld(), player.getLocation().toVector(), v, -1, length);
        List<Block> blockList = new ArrayList<>();

        int itCount = 0;
        while(bli.hasNext()){
            Block currentBlock = bli.next();
            blockList.add(currentBlock);
            itCount++;
            if(itCount > length)
                break;
        }

        for(int i = 0; i<blockList.size();i++ ){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new liftBlock(blockList.get(i)), i * 2);
        }

        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 35);
        Packet61WorldEvent packet = new Packet61WorldEvent(2001, player.getLocation().getBlockX(), player.getLocation().getBlockY(),  player.getLocation().getBlockZ(), 20, true);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        player.setVelocity(player.getVelocity().add(new Vector(0, 0.2, 0)));
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
}

