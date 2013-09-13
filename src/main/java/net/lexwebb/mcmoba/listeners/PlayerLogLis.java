package net.lexwebb.mcmoba.listeners;

import net.lexwebb.mcmoba.Classes.EarthWarrior;
import net.lexwebb.mcmoba.Classes.FireMage;
import net.lexwebb.mcmoba.Main;
import net.lexwebb.mcmoba.defaults.DefaultListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new doLater(e.getPlayer()), 40);
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e){
        Main.instance.players.remove(e.getPlayer());
        Main.instance.playerClass.remove(e.getPlayer());
    }

    public class doLater implements Runnable{
        Player p;

        public doLater(Player p){
            this.p = p;
        }
        @Override
        public void run() {
            Main.instance.players.add(p);
            Main.instance.playerClass.put(p, new EarthWarrior(p));
        }
    }
}
