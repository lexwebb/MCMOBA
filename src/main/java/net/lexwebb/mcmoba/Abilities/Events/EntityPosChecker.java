package net.lexwebb.mcmoba.Abilities.Events;

import net.lexwebb.mcmoba.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lex
 * Date: 14/09/13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class EntityPosChecker implements Runnable{

    @Override
    public void run() {
        for(World world: Bukkit.getServer().getWorlds()){

        List <Entity> entList = world.getEntities();
        for(Entity entity: entList){
            for(Entity ent: entList){
                if(entity.getLocation().getBlock() == ent.getLocation().getBlock()){
                    EntityCollideEvent entCollideEvent = new EntityCollideEvent(entity, ent);
                    Bukkit.getServer().getPluginManager().callEvent(entCollideEvent);
                    Bukkit.getServer().broadcastMessage(entity.getEntityId() + " collided with " + ent.getEntityId());
                }
            }
        }

    }
    }
}
