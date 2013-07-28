package net.lexwebb.mcmoba.Abilities.interfaces;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * User: Diremonsoon
 * Date: 7/27/13
 */
public interface IAreaOfEffect extends IAbility {

    public double getArea();

    public List<Player> getAffected();


}
