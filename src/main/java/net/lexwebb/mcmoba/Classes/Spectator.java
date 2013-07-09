package net.lexwebb.mcmoba.Classes;

import org.bukkit.entity.Player;

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
    }
}
