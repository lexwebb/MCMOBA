package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.Ability;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 08/07/13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlayerClass extends DefaultListener{
    int baseHealth;
    int baseMana;
    int baseDamage;

    int currentHealth;
    int currentMana;

    Player player;
    String type;

    public Ability ability1;
    public Ability ability2;
    public Ability ability3;
    public Ability ability4;

    public PlayerClass(Player player, int baseHealth, int baseMana, int baseDamage, String type){
        super(Main.instance);
        this.player = player;
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseMana = baseMana;

        this.currentMana = baseMana;
        this.baseDamage = baseDamage;
        this.type = type;
    }

    @EventHandler
    private void playerInteractEvent(PlayerInteractEvent e){
        Action eAction = e.getAction();
        Player p = e.getPlayer();
        if (eAction.equals(Action.RIGHT_CLICK_AIR)) {
            onRightClick();
        }
    }

    public void onRightClick(){

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
