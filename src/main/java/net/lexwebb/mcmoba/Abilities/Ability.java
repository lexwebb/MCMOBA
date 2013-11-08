package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 08/07/13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class Ability {
    public Player player;
    String name;
    Boolean singleTarget;
    Boolean AoE;
    int cooldownLength;
    Boolean onCoolDown = false;
    int coolDownLeft = 0;
    boolean inUse;

    boolean customTimedPlayerEffect;
    boolean customTimedtargetEffect;

    int taskId;

    List<LivingEntity> targets = new ArrayList<>();

    public Ability() {}

    public Ability(Player player, String name, Boolean singleTarget, Boolean AoE, int cooldownLength, boolean customTimedPlayerEffect, boolean customTimedtargetEffect){
        this.player = player;
        this.name = name;
        this.singleTarget = singleTarget;
        this.cooldownLength = cooldownLength;
        this.AoE = AoE;
        this.customTimedPlayerEffect = customTimedPlayerEffect;
        this.customTimedtargetEffect = customTimedtargetEffect;
    }

    public String getName() {
        return name;
    }

    public void playerEffect(){

    }

    public void targetEffect(){

    }

    public void use(){
        if(!onCoolDown){
            if(!customTimedPlayerEffect)
                playerEffect();
            if(!customTimedtargetEffect)
                targetEffect();
            inUse = true;
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player), 0, 20);
        }
    }

    public class cooldown implements Runnable{

        Player p;
        public cooldown(Player p){
            this.p = p;
            onCoolDown = true;
            coolDownLeft = cooldownLength;
        }

        @Override
        public void run() {
            if(coolDownLeft == 0){
                onCoolDown = false;
                Bukkit.getScheduler().cancelTask(taskId);
            } else {
                coolDownLeft--;
            }
        }
    }

    public int getCoolDownLeft() {
        return coolDownLeft;
    }

    public Boolean getOnCoolDown() {
        return onCoolDown;
    }
}
