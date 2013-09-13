package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.FireVortex;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 04/08/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class WaterMage extends PlayerClass{

    public WaterMage(Player player) {
        super(player, 1, 0, 0, "WaterMage");

        ability1 = new FireVortex(player);
        ability2 = new FireVortex(player);
        ability3 = new FireVortex(player);
        ability4 = new FireVortex(player);
    }
}
