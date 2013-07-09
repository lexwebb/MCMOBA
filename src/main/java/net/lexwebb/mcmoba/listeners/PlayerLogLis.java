package net.lexwebb.mcmoba.listeners;

import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 09/07/13
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
public class PlayerLogLis extends DefaultListener{
    public PlayerLogLis(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        Main.instance.players.add(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e){
        Main.instance.players.remove(e.getPlayer());
    }
}
