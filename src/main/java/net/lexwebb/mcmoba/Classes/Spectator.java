package net.lexwebb.mcmoba.Classes;

import net.lexwebb.mcmoba.Abilities.EarthSlam;
import net.lexwebb.mcmoba.Abilities.Leap;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 09/07/13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class Spectator extends PlayerClass {

    public Spectator(Player player) {
        super(player, 1, 0, 0, "Spectator");

        ability1 = new Leap(player);
        ability2 = new Leap(player);
        ability3 = new EarthSlam(player);
        ability4 = new EarthSlam(player);
    }
}
