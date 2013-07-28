package net.lexwebb.mcmoba.Abilities;

import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 28/07/13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class EarthMissile extends Ability{

    public EarthMissile(Player player){
        super(player, "EarthMissile", true, false, 20, false, false);

    }

    @Override
    public void targetEffect(){

    }

    @Override
    public void playerEffect(){

    }
}
