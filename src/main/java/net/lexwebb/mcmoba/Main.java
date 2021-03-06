package net.lexwebb.mcmoba;

import net.lexwebb.mcmoba.Abilities.Events.EntityPosChecker;
import net.lexwebb.mcmoba.Classes.*;
import net.lexwebb.mcmoba.defaults.CombatLog;
import net.lexwebb.mcmoba.defaults.IconMenu;
import net.lexwebb.mcmoba.listeners.AbilityLis;
import net.lexwebb.mcmoba.listeners.DamageLis;
import net.lexwebb.mcmoba.listeners.EntityLis;
import net.lexwebb.mcmoba.listeners.PlayerLogLis;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public final PlayerLogLis logLis = new PlayerLogLis(this);
    public final EntityLis entLis = new EntityLis(this);
    public final DamageLis damageLis = new DamageLis(this);

    public final List<Player> players = new ArrayList<>();
    public final HashMap<Player, PlayerClass> playerClass = new HashMap<>();
    public final List<LivingEntity> creeps = new ArrayList<>();
    //public final List<FallingBlock> thrownBlock = new ArrayList<>();

    public CombatLog combatLog;

    public final HashMap<FallingBlock, Player> thrownBlock = new HashMap<>();

    public IconMenu menu;

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(abilityLis, this);
        pm.registerEvents(logLis, this);
        pm.registerEvents(entLis, this);
        pm.registerEvents(damageLis, this);
        combatLog = new CombatLog();
        instance = this;

        menu = new IconMenu("Classes", 9, new IconMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                event.getPlayer().sendMessage("You have chosen " + event.getName());
                event.setWillClose(true);
                Main.instance.playerClass.remove(event.getPlayer());
                switch (event.getPosition()) {
                    case 0:
                        Main.instance.playerClass.put(event.getPlayer(), new FireMage(event.getPlayer(), 1));
                        break;
                    case 1:
                        Main.instance.playerClass.put(event.getPlayer(), new WaterMage(event.getPlayer(), 1));
                        break;
                    case 2:
                        Main.instance.playerClass.put(event.getPlayer(), new EarthWarrior(event.getPlayer(), 1));
                        break;
                    case 3:
                        Main.instance.playerClass.put(event.getPlayer(), new Rogue(event.getPlayer(), 1));
                        break;
                    case 4:
                        Main.instance.playerClass.put(event.getPlayer(), new Ranger(event.getPlayer(), 1));
                        break;
                    case 5:
                        Main.instance.playerClass.put(event.getPlayer(), new Tank(event.getPlayer(), 1));
                        break;
                }

                Main.instance.playerClass.get(event.getPlayer().getPlayer()).setItemSlots();
            }
        }, this)
                .setOption(0, new ItemStack(Material.FIRE, 1), "Ignibus", "Fire Mage")
                .setOption(1, new ItemStack(Material.WATER_BUCKET, 1), "Aquam", "Water Mage")
                .setOption(2, new ItemStack(Material.GRASS, 1), "Terram", "Earth Warrior")
                .setOption(3, new ItemStack(Material.IRON_SWORD, 1), "Perfidus", "Rogue")
                .setOption(4, new ItemStack(Material.ARROW, 1), "Arcus", "Ranger")
                .setOption(5, new ItemStack(Material.IRON_CHESTPLATE, 1), "Saxum", "Heavy Warrior");
    }

    @Override
    public void onDisable() {

    }
}
