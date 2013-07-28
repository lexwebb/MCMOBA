package net.lexwebb.mcmoba.Abilities;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lex
 * Date: 16/07/13
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
public class EarthWall extends Ability{

    Player player;
    public EarthWall(Player player){
        super(player, "EarthWall", false, true, 20, false, true);
        this.player = player;
    }

    @Override
    public void targetEffect(){

    }

    @Override
    public void playerEffect(){
        List<Block> blockList = new ArrayList<>();
    String direction = getCardinalDirection(player);
        switch (direction) {
            case "N":
                blockList.add(player.getWorld().getBlockAt(player.getLocation().add(0,0,-3)));  //start
                blockList.add(player.getWorld().getBlockAt(player.getLocation().add(1,0,-3)));  //right of start
                blockList.add(player.getWorld().getBlockAt(player.getLocation().add(-1,0,-3)));  //left of start
                blockList.add(player.getWorld().getBlockAt(player.getLocation().add(2,0,-3)));  //right 2 of start
                blockList.add(player.getWorld().getBlockAt(player.getLocation().add(-2,0,-3)));  //left 2 of start
                break;
            case "NE":

                break;
            case "E":

                break;
            case "SE":

                break;
            case "S":

                break;
            case "SW":

                break;
            case "W":

                break;
            case "NW":

                break;
        }
    }

    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
}
