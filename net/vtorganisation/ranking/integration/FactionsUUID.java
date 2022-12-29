package net.vtorganisation.ranking.integration;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Relation;
import com.massivecraft.factions.struct.Role;
import net.vtorganisation.ranking.Ranking;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FactionsUUID implements FactionsManager {

    @Override
    public boolean isWilderness(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFaction().isWilderness();
    }

    @Override
    public boolean isWilderness(Block block) {
        return Board.getInstance().getFactionAt(new FLocation(block.getLocation())).isWilderness();
    }

    @Override
    public boolean isWilderness(Location location) {
        return Board.getInstance().getFactionAt(new FLocation(location)).isWilderness();
    }

    @Override
    public boolean isWilderness(String s) {
        return Factions.getInstance().getByTag(s).isWilderness();
    }

    @Override
    public boolean isEnemyTerritory(Player player) {
        Faction faction = Board.getInstance().getFactionAt(new FLocation(player.getLocation()));
        Faction faction2 = FPlayers.getInstance().getByPlayer(player).getFaction();
        return faction.isNormal() && faction != faction2;
    }

    @Override
    public boolean isSameFaction(Player player, Player player2) {
        return FPlayers.getInstance().getByPlayer(player).getFaction() == FPlayers.getInstance().getByPlayer(player2).getFaction();
    }

    @Override
    public boolean isSameFaction(Player player, Block block) {
        Faction faction = FPlayers.getInstance().getByPlayer(player).getFaction();
        Faction faction2 = Board.getInstance().getFactionAt(new FLocation(block.getLocation()));
        return faction == faction2 && !faction2.isWilderness();
    }

    @Override
    public boolean isInOwnClaim(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFaction() == Board.getInstance().getFactionAt(new FLocation(player.getLocation()));
    }

    @Override
    public boolean hasFaction(Player player) {
        return FPlayers.getInstance().getByPlayer(player).hasFaction();
    }


    @Override
    public String getFactionTag(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFaction().getTag();
    }

    @Override
    public String getFactionID(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFaction().getId();
    }

    @Override
    public void onDeath(Player player) {
        FPlayers.getInstance().getByPlayer(player).onDeath();
    }

    @Override
    public void sendFactionMessage(Player player, String s) {
        FPlayers.getInstance().getByPlayer(player).getFaction().sendMessage(s);
    }

    @Override
    public boolean isAdmin(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getRole() == Role.ADMIN;
    }

    @Override
    public boolean isMod(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getRole() == Role.MODERATOR;
    }

    @Override
    public boolean isAtLeastMod(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getRole().isAtLeast(Role.MODERATOR);
    }

    @Override
    public ArrayList<Player> getOnlinePlayers(Player player) {
        Faction faction = FPlayers.getInstance().getByPlayer(player).getFaction();
        if (faction.isWilderness()) {
            return null;
        }
        return faction.getOnlinePlayers();
    }

    @Override
    public ArrayList<Player> getOnlinePlayers(String s) {
        Faction byTag = Factions.getInstance().getByTag(s);
        if (byTag == null || byTag.isWilderness() || byTag.isWarZone() || byTag.isSafeZone()) {
            return null;
        }
        return byTag.getOnlinePlayers();
    }

    @Override
    public boolean isAlly(Player player, Player player2) {
        return FPlayers.getInstance().getByPlayer(player).getFaction().getRelationTo(FPlayers.getInstance().getByPlayer(player2).getFaction()) == Relation.ALLY;
    }

    @Override
    public List<UUID> getPlayerUUIDS(Player player) {
        Faction faction = FPlayers.getInstance().getByPlayer(player).getFaction();
        ArrayList<UUID> list = new ArrayList<>();
        for (FPlayer fPlayer : faction.getFPlayersWhereOnline(true)) {
            list.add(fPlayer.getPlayer().getUniqueId());
        }
        return list;
    }

    @Override
    public void disbandAllFactions() {
        new BukkitRunnable() {
            public void run() {
                for (Faction faction : Factions.getInstance().getAllFactions()) {
                    if (!faction.isSafeZone() && !faction.isWarZone()) {
                        if (faction.isWilderness()) {
                            continue;
                        }
                        Factions.getInstance().removeFaction(faction.getId());
                    }
                }
            }
        }.runTaskAsynchronously(Ranking.getInstance());
    }

    @Override
    public Location getHome(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFaction().getHome();
    }

    @Override
    public List<?> getNearestClaims(Player player) {
        ArrayList<FLocation> list = new ArrayList<>();
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (i != 0 || j != 0) {
                    FLocation relative = new FLocation(player.getLocation()).getRelative(i, j);
                    if (Board.getInstance().getFactionAt(relative).isNormal()) {
                        list.add(relative);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<String> getAllFactionName() {
        ArrayList<String> list = new ArrayList<>();
        List<Faction> factions = Factions.getInstance().getAllFactions();
        factions.remove(Factions.getInstance().getWilderness());
        factions.remove(Factions.getInstance().getSafeZone());
        factions.remove(Factions.getInstance().getWarZone());
        factions.forEach(factionName -> list.add(factionName.getTag()));
        return list;
    }
}