package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Abilities.Ability;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.ParticleEffect;
import net.lexwebb.mcmoba.defaults.Utilities;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 04/08/13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public class FireVortex extends Ability {

    List<Location> posList = new ArrayList<>();
    int range = 10;

    int taskID;
    public FireVortex(Player player){
        super(player, "FireVortex", false, true, 5, false, true);

    }

    @Override
    public void playerEffect(){

        Location loc = player.getTargetBlock(null, range).getLocation();
        if(loc.distance(player.getLocation()) < range){
            posList = circle(loc, 5, 1, true, false, 1);

            for(int i = 0; i < 5; i++){
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new FireSpin(loc), i * 10);
            }
        } else {
            this.coolDownLeft = 0;
            this.onCoolDown = false;
            player.sendMessage(ChatColor.DARK_RED + "Out of Range!");
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

    public class FireSpin implements Runnable{

        Location loc;
        public FireSpin(Location loc){
            this.loc = loc;
        }

        @Override
        public void run() {
            for(int i = 0; i < posList.size(); i++){
                //player.getWorld().playEffect(posList.get(i), Effect.MOBSPAWNER_FLAMES, 0);
                try {
                    ParticleEffect.sendToLocation(ParticleEffect.FLAME, posList.get(i),0,0,0,0.05f,5);
                } catch (Exception ex){

                }
            }
            Utilities util = new Utilities();
            for(Entity ent: util.getNearbyLocationEntities(player, loc, 5)){
                if(ent instanceof LivingEntity && ent != player){
                    ent.setFireTicks(40);
                    ((LivingEntity) ent).damage(1.0);
                }
            }
        }
    }
}
