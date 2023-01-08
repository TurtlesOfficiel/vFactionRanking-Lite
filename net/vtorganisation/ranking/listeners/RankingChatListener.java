package net.vtorganisation.ranking.listeners;

import net.vtorganisation.ranking.Ranking;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RankingChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    private void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String formats = e.getFormat();
        String factionName = Ranking.getInstance().getFactionManager().getFactionTag(player);
        String format = "";

        if(Ranking.getInstance().getRankingManager().isExist(factionName)) {
            String ranking = Ranking.getInstance().getRankingManager().getFaction(factionName).getRanking();
            format = formats.replace("[Ranking]", new StringBuilder(String.valueOf(ranking)));
        } else {
            format = formats.replace("[Ranking]", ChatColor.RED + "?");
        }
        e.setFormat(format);
    }
}