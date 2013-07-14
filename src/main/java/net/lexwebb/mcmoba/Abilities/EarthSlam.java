package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
        super(player, "EarthSlam", false, true, 20, false, true);

    }

    @Override
    public void targetEffect(){

    }

    @Override
    public void playerEffect(){
        int length = 7;

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
            b.setTypeId(0);
            FallingBlock block = b.getWorld().spawnFallingBlock(b.getLocation(), m, bi);
            block.setVelocity(new Vector(0, 0.2, 0));

            block.getWorld().playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 35);
            List<Entity> entList = block.getNearbyEntities(2, 2, 2);
            for(Entity entity : entList){
                if (entity instanceof LivingEntity && entity != player) {
                    ((LivingEntity) entity).damage(6);
                }
            }
        }
    }
}

