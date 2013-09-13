package net.lexwebb.mcmoba.Abilities;

import net.lexwebb.mcmoba.Main;
import net.minecraft.server.v1_6_R2.Packet61WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class Dummy extends Ability {

    int taskID;
    public Dummy(Player player){
        super(player, "Dummy", false, true, 0, false, true);

    }

    @Override
    public void targetEffect(){
       player.sendMessage("Dummy Ability");
    }

    @Override
    public void playerEffect(){
        player.sendMessage("Dummy Ability");
    }
}

