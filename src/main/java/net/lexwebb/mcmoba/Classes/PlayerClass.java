package net.lexwebb.mcmoba.Classes;

import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 08/07/13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlayerClass {
    int baseHealth;
    int baseMana;
    int baseDamage;

    int currentHealth;
    int currentMana;

    Player player;
    String type;

    public PlayerClass(Player player, int baseHealth, int baseMana, int baseDamage, String type){
        this.player = player;
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseMana = baseMana;

        this.currentMana = baseMana;
        this.baseDamage = baseDamage;
        this.type = type;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseMana() {
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
