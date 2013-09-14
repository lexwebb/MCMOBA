package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.Ability;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import net.minecraft.server.v1_6_R2.EntityPlayer;
import net.minecraft.server.v1_6_R2.IInventory;
import net.minecraft.server.v1_6_R2.Packet103SetSlot;
import net.minecraft.server.v1_6_R2.Slot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    public ItemStack slot1 = new ItemStack(Material.ARROW);
    public ItemStack slot2 = new ItemStack(Material.ARROW);
    public ItemStack slot3 = new ItemStack(Material.ARROW);
    public ItemStack slot4 = new ItemStack(Material.ARROW);

    public ItemStack weapon = new ItemStack(Material.WOOD_SWORD);

    public PlayerClass(Player player, double baseHealth, double baseMana, int baseDamage, String type){
        super(Main.instance);
        this.player = player;
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseMana = baseMana;

        this.currentMana = baseMana;
        this.baseDamage = baseDamage;
        this.type = type;

        player.sendMessage("You are " + type);

        setItemSlots();
    }

    public void onRightClick(){

    }

    public void onLeftClick(){

    }

    public void setItemSlots(){

        ItemMeta meta1 = slot1.getItemMeta();
        ItemMeta meta2 = slot2.getItemMeta();
        ItemMeta meta3 = slot3.getItemMeta();
        ItemMeta meta4 = slot4.getItemMeta();

        try{
        meta1.setDisplayName(ability1.getName());
        meta2.setDisplayName(ability2.getName());
        meta3.setDisplayName(ability3.getName());
        meta4.setDisplayName(ability4.getName());
        } catch(Exception e){

        }

        slot1.setItemMeta(meta1);
        slot2.setItemMeta(meta2);
        slot3.setItemMeta(meta3);
        slot4.setItemMeta(meta4);

        player.getInventory().setItem(0, slot1);
        player.getInventory().setItem(1, slot2);
        player.getInventory().setItem(2, slot3);
        player.getInventory().setItem(3, slot4);


        player.getInventory().setItem(4, weapon);

        ItemStack menu = new ItemStack(Material.BOOK);
        ItemMeta meta = menu.getItemMeta();
        meta.setDisplayName("Menu");
        menu.setItemMeta(meta);

        player.getInventory().setItem(8, menu);

        player.getInventory().setHeldItemSlot(4);
    }

    public void onAbilityOne(){
        if(!ability1.getOnCoolDown()){
            ability1.use();
            AOneTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 1), 0, 1);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }

    }

    public void onAbilityTwo(){
        if(!ability2.getOnCoolDown()){
            ability2.use();
            ATwoTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 2), 0, 1);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }
    }

    public void onAbilityThree(){
        if(!ability3.getOnCoolDown()){
            ability3.use();
            AThreeTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 3), 0, 1);
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "That Ability is on cooldown!");
        }
    }

    public void onAbilityFour(){
        if(!ability4.getOnCoolDown()){
            ability4.use();
            AFourTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new cooldown(player, 4), 0, 1);
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
                    if(ability1.getCoolDownLeft() >= 0){
                        p.getInventory().getItem(0).setAmount(ability1.getCoolDownLeft() +1);
                        if(ability1.getCoolDownLeft() == 0){
                            p.sendMessage(ability1.getName() + " is off cooldown!");
                            Bukkit.getScheduler().cancelTask(AOneTaskId);
                        }
                    }
                    break;
                case 2:
                    if(ability2.getCoolDownLeft() >= 0){
                        p.getInventory().getItem(1).setAmount(ability2.getCoolDownLeft() +1);
                        if(ability2.getCoolDownLeft() == 0){
                            p.sendMessage(ability2.getName() + " is off cooldown!");
                            Bukkit.getScheduler().cancelTask(ATwoTaskId);
                        }
                    }
                    break;
                case 3:
                    if(ability3.getCoolDownLeft() >= 0){
                        p.getInventory().getItem(2).setAmount(ability3.getCoolDownLeft() +1);
                        if(ability3.getCoolDownLeft() == 0){
                            p.sendMessage(ability3.getName() + " is off cooldown!");
                            Bukkit.getScheduler().cancelTask(AThreeTaskId);
                        }
                    }
                    break;
                case 4:
                    if(ability4.getCoolDownLeft() >= 0){
                        p.getInventory().getItem(3).setAmount(ability4.getCoolDownLeft() +1);
                        if(ability4.getCoolDownLeft() == 0){
                            p.sendMessage(ability4.getName() + " is off cooldown!");
                            Bukkit.getScheduler().cancelTask(AFourTaskId);
                        }
                    }
                    break;
            }
        }
    }
}
