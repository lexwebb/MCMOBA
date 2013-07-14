package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.Ability;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 08/07/13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlayerClass extends DefaultListener{
    double baseHealth;
    double baseMana;
    int baseDamage;

    double currentHealth;
    double currentMana;

    Player player;
    String type;

    public Ability ability1;
    public Ability ability2;
    public Ability ability3;
    public Ability ability4;
    int AOneTaskId;
    int ATwoTaskId;
    int AThreeTaskId;
    int AFourTaskId;

    public PlayerClass(Player player, double baseHealth, double baseMana, int baseDamage, String type){
        super(Main.instance);
        this.player = player;
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseMana = baseMana;

        this.currentMana = baseMana;
        this.baseDamage = baseDamage;
        this.type = type;
    }

    public void onRightClick(){

    }

    public void onLeftClick(){

    }

    public void onAbilityOne(){
        if(!ability1.getOnCoolDown()){
            ability1.use();
            AOneTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 1), 0, 10);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }

    }

    public void onAbilityTwo(){
        if(!ability2.getOnCoolDown()){
            ability2.use();
            ATwoTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 2), 0, 10);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }
    }

    public void onAbilityThree(){
        if(!ability3.getOnCoolDown()){
            ability3.use();
            AThreeTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 3), 0, 10);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }
    }

    public void onAbilityFour(){
        if(!ability4.getOnCoolDown()){
            ability4.use();
            AFourTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 4), 0, 10);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }
    }

    public double getCurrentHealth() {
        currentHealth = player.getHealth();
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public double getBaseMana() {
        return baseMana;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public Player getPlayer() {
        return player;
    }

    public String getType() {
        return type;
    }

    public class cooldown implements Runnable{

        Player p;
        int abilNo;
        public cooldown(Player p, int abilNo){
            this.p = p;
            this.abilNo = abilNo;
        }

        @Override
        public void run() {
            switch (abilNo) {
                case 1:
                    if(ability1.getCoolDownLeft() > 0){
                        p.getInventory().getItem(0).setAmount(ability1.getCoolDownLeft());
                    } else {
                        Bukkit.getScheduler().cancelTask(AOneTaskId);
                    }
                    break;
                case 2:
                    if(ability2.getCoolDownLeft() > 0){
                        p.getInventory().getItem(1).setAmount(ability2.getCoolDownLeft());
                    } else {
                        Bukkit.getScheduler().cancelTask(ATwoTaskId);
                    }
                    break;
                case 3:
                    if(ability3.getCoolDownLeft() > 0){
                        p.getInventory().getItem(2).setAmount(ability3.getCoolDownLeft());
                    } else {
                        Bukkit.getScheduler().cancelTask(AThreeTaskId);
                    }
                    break;
                case 4:
                    if(ability4.getCoolDownLeft() > 0){
                        p.getInventory().getItem(3).setAmount(ability4.getCoolDownLeft());
                    } else {
                        Bukkit.getScheduler().cancelTask(AFourTaskId);
                    }
                    break;
            }
        }
    }
}
