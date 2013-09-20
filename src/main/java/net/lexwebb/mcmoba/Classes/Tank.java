package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.Dummy;
import net.lexwebb.mcmoba.Abilities.FireVortex;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 04/08/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class Tank extends PlayerClass{

    public Tank(Player player, int team) {
        super(player, team, 20, 200, 0, "Saxum");

        ability1 = new Dummy(player);
        ability2 = new Dummy(player);
        ability3 = new Dummy(player);
        ability4 = new Dummy(player);
    }
}
