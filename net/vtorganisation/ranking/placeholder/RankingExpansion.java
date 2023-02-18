package net.vtorganisation.ranking.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.vtorganisation.ranking.FactionObject;
import net.vtorganisation.ranking.Ranking;
import net.vtorganisation.ranking.manager.RankingManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class RankingExpansion extends PlaceholderExpansion {

    private final PluginDescriptionFile desc;

    public RankingExpansion(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("vFactionRanking-Lite");
        this.desc = plugin.getDescription();
    }

    @Override
    public String getIdentifier() {
        return "ranking";
    }

    @Override
    public String getAuthor() {
        return desc.getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return desc.getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String params){
        if(player == null){
            return "";
        }
        String factionName = Ranking.getInstance().getFactionManager().getFactionTag(player);
        RankingManager rankingManager = Ranking.getInstance().getRankingManager();

        if(!rankingManager.isExist(factionName)){
            return ChatColor.RED + "?";
        }
        FactionObject factionObject = rankingManager.getFaction(factionName);

        if(params.equals("position")){
            return factionObject.getRanking();
        }
        if(params.equals("points")){
            return String.valueOf(factionObject.getPoints());
        }
        return null;
    }
}