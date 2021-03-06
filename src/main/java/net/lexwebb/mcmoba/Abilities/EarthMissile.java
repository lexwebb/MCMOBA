package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 28/07/13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class EarthMissile extends Ability{

    Utilities utils = new Utilities();
    FallingBlock fallingblock;

    public EarthMissile(Player player){
        super(player, "EarthMissile", true, false, 1, false, false);

    }

    @Override
    public void targetEffect(){

    }

    @Override
    public void playerEffect(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new liftBlock(getLiftBlock()), 0);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new throwBlock(fallingblock), 10);
    }

    private Block getLiftBlock(){
        Block block = null;
        String direction = utils.getCardinalDirection(player);
        switch (utils.getCardinalDirection(player)){
            case "N":
                block = player.getWorld().getBlockAt(player.getLocation().add(-1,-1,0));
                break;
            case "NE":
                block = player.getWorld().getBlockAt(player.getLocation().add(-1,-1,-1));
                break;
            case "E":
                block = player.getWorld().getBlockAt(player.getLocation().add(0,-1,-1));
                break;
            case "SE":
                block = player.getWorld().getBlockAt(player.getLocation().add(1,-1,-1));
                break;
            case "S":
                block = player.getWorld().getBlockAt(player.getLocation().add(1,-1,0));
                break;
            case "SW":
                block = player.getWorld().getBlockAt(player.getLocation().add(1,-1,1));
                break;
            case "W":
                block = player.getWorld().getBlockAt(player.getLocation().add(0,-1,1));
                break;
            case "NW":
                block = player.getWorld().getBlockAt(player.getLocation().add(-1,-1,1));
                break;
        }

        return block;
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
                if(b.getType().isSolid() == false){
                    if(b.getRelative(BlockFace.DOWN).getType().isSolid()){
                        m = b.getRelative(BlockFace.DOWN).getType();
                        bi = b.getRelative(BlockFace.DOWN).getData();
                        topBlock = true;
                    } else {
                        b = b.getRelative(BlockFace.DOWN);
                    }
                } else {
                    if(b.getRelative(BlockFace.UP).getType().isSolid() == false){
                        m = b.getType();
                        bi = b.getData();
                        topBlock = true;
                    } else {
                        b = b.getRelative(BlockFace.UP);
                    }
                }
            }

            fallingblock = b.getWorld().spawnFallingBlock(b.getLocation(), m, bi);
            Main.instance.thrownBlock.put(fallingblock, player);
            fallingblock.setVelocity(new Vector(0, 0.6, 0));

            fallingblock.getWorld().playSound(fallingblock.getLocation(), Sound.DIG_STONE, 5, 1);
            b.setType(m);
            b.setData(bi);
        }
    }

    public class throwBlock implements Runnable{

        FallingBlock b;
        public throwBlock(FallingBlock b){
            this.b = b;
        }

        @Override
        public void run() {
            Vector v = player.getLocation().getDirection();
            //v.setY(0);
            v = v.multiply(3);

            fallingblock.setVelocity(v);
        }
    }

}
