package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.Ability;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

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
        ability1.use();
    }

    public void onAbilityTwo(){
        ability2.use();
    }

    public void onAbilityThree(){
        ability3.use();
    }

    public void onAbilityFour(){
        ability4.use();
    }

    public void onPlayerDamage(EntityDamageEvent e){

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
}
