package net.lexwebb.mcmoba.Abilities.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created with IntelliJ IDEA.
 * User: lex
 * Date: 14/09/13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class EntityCollideEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public Entity getEntity1() {
        return entity1;
    }

    public Entity getEntity2() {
        return entity2;
    }

    Entity entity1, entity2;

    public EntityCollideEvent(Entity entity1, Entity entity2){
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
