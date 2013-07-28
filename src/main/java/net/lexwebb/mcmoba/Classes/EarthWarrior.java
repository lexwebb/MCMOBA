package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.EarthMissile;
import net.lexwebb.mcmoba.Abilities.EarthSlam;
import net.lexwebb.mcmoba.Abilities.EarthSmash;
import net.lexwebb.mcmoba.Abilities.EarthLeap;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 09/07/13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class EarthWarrior extends PlayerClass {

    public EarthWarrior(Player player) {
        super(player, 1, 0, 0, "EarthWarrior");

        ability1 = new EarthLeap(player);
        ability2 = new EarthSlam(player);
        ability3 = new EarthSmash(player);
        ability4 = new EarthMissile(player);
    }
}
