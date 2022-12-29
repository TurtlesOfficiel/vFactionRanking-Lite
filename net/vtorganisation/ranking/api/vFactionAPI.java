package net.vtorganisation.ranking.api;

import net.vtorganisation.ranking.Ranking;
import org.bukkit.entity.Player;

public class vFactionAPI {

    /*
     Add faction points
    */
    public static void addPointsToFaction(String faction, int points){
        Ranking.getInstance().getRankingManager().getFaction(faction).addPoints(points);
    }
    public static void addPointsToFaction(Player player, int points){
        Ranking.getInstance().getRankingManager().getFaction(getFactionNameWithPlayer(player)).addPoints(points);
    }

    /*
     Remove faction points
    */
    public static void removePointsToFaction(String faction, int points){
        Ranking.getInstance().getRankingManager().getFaction(faction).removePoints(points);
    }
    public static void removePointsToFaction(Player player, int points){
        Ranking.getInstance().getRankingManager().getFaction(getFactionNameWithPlayer(player)).removePoints(points);
    }

    /*
     Apply faction points
    */
    public static void setPointsToFaction(String faction, int points){
        Ranking.getInstance().getRankingManager().getFaction(faction).setPoints(points);
    }
    public static void setPointsToFaction(Player player, int points){
        Ranking.getInstance().getRankingManager().getFaction(getFactionNameWithPlayer(player)).setPoints(points);
    }
    /*
     Retrieve the faction points
    */
    public static int getPointsWithFaction(String faction){
        return Ranking.getInstance().getRankingManager().getFaction(faction).getPoints();
    }
    public static int getPointsWithFaction(Player player){
        return Ranking.getInstance().getRankingManager().getFaction(getFactionNameWithPlayer(player)).getPoints();
    }
    /*
      Retrieve the faction name
    */
    public static String getFactionNameWithPlayer(Player player){
        return Ranking.getInstance().getFactionManager().getFactionTag(player);
    }
}