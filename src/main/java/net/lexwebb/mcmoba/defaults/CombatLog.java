package net.lexwebb.mcmoba.defaults;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 9/20/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class CombatLog {

    public CombatLog(){

    }

    public enum deathType{
        Decimated("Decimated"),
        Wrecked("Wrecked"),
        Squashed("Squashed"),
        Destroyed("Destroyed"),
        Pulverised("Pulverised")
        ;

        private deathType(final String text) {
            this.text = text;
        }

        private final String text;

        @Override
        public String toString() {
            return text;
        }
    }

    private String randomDeath() {
        int pick = new Random().nextInt(deathType.values().length);
        return deathType.values()[pick].toString();
    }

    public void creepKilledPlayer(LivingEntity creep, Player player){

    }

    public void playerDamageByEntity(Player player, double damage, Entity entity, String attack){
        if(entity instanceof Player)
            Bukkit.getServer().broadcastMessage(player.getName() + " was hurt by " + player.getName() +" for " + damage + " damage!");
        else
            Bukkit.getServer().broadcastMessage(player.getName() + " was hurt by a creep for " + damage + " damage!");
    }

    public void playerKilledByEntity(Player player, double damage, Entity entity, String attack){
        if(entity instanceof Player)
            Bukkit.getServer().broadcastMessage(player.getName() + " was " + randomDeath() + " by " + player.getName() +"'s" + attack);
        else
            Bukkit.getServer().broadcastMessage(player.getName() + " was " + randomDeath() + " by a creeps " + attack);
    }
}
