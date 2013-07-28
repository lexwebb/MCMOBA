package net.lexwebb.mcmoba.Abilities.interfaces;

import net.lexwebb.mcmoba.Abilities.interfaces.IAbility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;

/**
 * User: Diremonsoon
 * Date: 7/27/13
 */
public interface Targetable extends IAbility {

    public Entity getTarget();

    public Projectile getProjectile();


}
