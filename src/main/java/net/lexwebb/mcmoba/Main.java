package net.lexwebb.mcmoba;

import net.lexwebb.mcmoba.listeners.AbilityLis;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 06/07/13
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
public final class Main extends JavaPlugin {

    public static Main instance;

    public final AbilityLis abilityLis = new AbilityLis(this);

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(abilityLis, this);
    }

    @Override
    public void onDisable() {

    }
}
