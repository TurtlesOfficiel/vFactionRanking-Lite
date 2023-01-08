package net.vtorganisation.ranking.listeners;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FPlayerLeaveEvent;
import com.massivecraft.factions.event.FactionCreateEvent;
import com.massivecraft.factions.event.FactionDisbandEvent;
import com.massivecraft.factions.event.FactionRenameEvent;
import com.massivecraft.factions.struct.Role;
import net.vtorganisation.ranking.FactionObject;
import net.vtorganisation.ranking.Ranking;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class RankingListener implements Listener {

    @EventHandler
    private void onFactionCreate(FactionCreateEvent e) {
	    String factionName = e.getFactionTag();
        if(!Ranking.getInstance().getRankingManager().isExist(factionName)){
            FactionObject factionObject = new FactionObject(factionName);
            factionObject.addData();
        }
    }

    @EventHandler
    private void onFactionDisband(FactionDisbandEvent e) {
        String factionName = e.getFaction().getTag();
        if(Ranking.getInstance().getRankingManager().isExist(factionName)){
            Ranking.getInstance().getRankingManager().getFaction(factionName).removeData();
        }
    }

	@EventHandler
    private void onFactionRename(FactionRenameEvent e) {
        String factionName = e.getFactionTag();
        String factionOldName = e.getOldFactionTag();
        if(Ranking.getInstance().getRankingManager().isExist(factionOldName)){
            Ranking.getInstance().getRankingManager().getFaction(factionOldName).changeName(factionName);
        }
    }

    @EventHandler
    private void onFPlayerLeave(FPlayerLeaveEvent e) {
        FPlayer fplayer = e.getfPlayer();
        Faction faction = e.getFaction();
        String factionName = faction.getTag();
        FPlayerLeaveEvent.PlayerLeaveReason reason = e.getReason();

        if(fplayer.getPlayer() == null) return;

        if(!(reason == FPlayerLeaveEvent.PlayerLeaveReason.LEAVE)) return;

        if(faction.isPermanent()) return;

        if(!fplayer.getRole().isAtLeast(Role.valueOf(getRole()))) return;

        if(!Ranking.getInstance().getRankingManager().isExist(factionName)) return;

        Ranking.getInstance().getRankingManager().getFaction(factionName).removeData();
    }

    private boolean isFactionUUID(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
        PluginDescriptionFile desc = plugin.getDescription();
        return desc.getVersion().contains("U0");
    }

    private String getRole(){
        return isFactionUUID() ? "ADMIN" : "LEADER";
    }
}