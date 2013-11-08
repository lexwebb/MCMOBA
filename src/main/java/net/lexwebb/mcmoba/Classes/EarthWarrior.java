package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.EarthMissile;
import net.lexwebb.mcmoba.Abilities.EarthSlam;
import net.lexwebb.mcmoba.Abilities.EarthSmash;
import net.lexwebb.mcmoba.Abilities.EarthLeap;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 09/07/13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class EarthWarrior extends PlayerClass {

    public EarthWarrior(Player player, int team) {
        super(player, team, 20, 200, 0, "Terram");

        ability1 = new EarthLeap(player);
        ability2 = new EarthSlam(player);
        ability3 = new EarthSmash(player);
        ability4 = new EarthMissile(player);

        slot1 = new ItemStack(Material.INK_SACK, 1, (short)2);
        slot4 = new ItemStack(Material.SLIME_BALL, 1);
        slot3 = new ItemStack(Material.EMERALD, 1);
        slot2 = new ItemStack(2260, 1); //Record-Far
        setItemSlots();
    }
}
