package net.vtorganisation.ranking.api;

import net.vtorganisation.ranking.Ranking;
import org.bukkit.entity.Player;

public class vFactionAPI {

    /*
     Add faction points
    */
    public static void addPointsToFaction(String faction, long points){
        Ranking.getInstance().getRankingManager().getFaction(faction).addPoints(points);
    }
    /*
     Remove faction points
    */
    public static void removePointsToFaction(String faction, long points){
        Ranking.getInstance().getRankingManager().getFaction(faction).removePoints(points);
    }
    /*
     Apply faction points
    */
    public static void setPointsToFaction(String faction, long points){
        Ranking.getInstance().getRankingManager().getFaction(faction).setPoints(points);
    }
    /*
     Retrieve the faction points
    */
    public static long getPointsWithFaction(String faction){
        return Ranking.getInstance().getRankingManager().getFaction(faction).getPoints();
    }
    /*
      Retrieve the faction name
    */
    public static String getFactionNameWithPlayer(Player player){
        return Ranking.getInstance().getFactionManager().getFactionTag(player);
    }
}