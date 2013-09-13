package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.*;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 04/08/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class FireMage extends PlayerClass{

    public FireMage(Player player) {
        super(player, 1, 0, 0, "Ignibus");

        ability1 = new FireVortex(player);
        ability2 = new Dummy(player);
        ability3 = new Dummy(player);
        ability4 = new Dummy(player);
    }
}
