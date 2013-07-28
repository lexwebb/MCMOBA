package net.lexwebb.mcmoba.Abilities.interfaces;

import org.bukkit.entity.Entity;

/**
 * User: Diremonsoon
 * Date: 7/27/13
 */
public interface IAbility {

    public int getCooldownTime();

    public int getLevel();

    public Entity getCaster();

    public double getExpEarned();

    public double getExpNeeded();
}
