package net.vtorganisation.ranking.integration;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface FactionsManager {

  boolean isWilderness(Player paramPlayer);

  boolean isWilderness(Block paramBlock);

  boolean isWilderness(Location paramLocation);
  
  boolean isWilderness(String paramString);
  
  boolean isEnemyTerritory(Player paramPlayer);
  
  boolean isSameFaction(Player paramPlayer1, Player paramPlayer2);
  
  boolean isSameFaction(Player paramPlayer, Block paramBlock);
  
  boolean isInOwnClaim(Player paramPlayer);
  
  String getFactionTag(Player paramPlayer);

  boolean hasFaction(Player paramPlayer);

  String getFactionID(Player paramPlayer);
  
  void onDeath(Player paramPlayer);
  
  void sendFactionMessage(Player paramPlayer, String paramString);
  
  boolean isAdmin(Player paramPlayer);
  
  boolean isMod(Player paramPlayer);
  
  boolean isAtLeastMod(Player paramPlayer);
  
  ArrayList<Player> getOnlinePlayers(Player paramPlayer);
  
  ArrayList<Player> getOnlinePlayers(String paramString);
  
  boolean isAlly(Player paramPlayer1, Player paramPlayer2);
  
  List<UUID> getPlayerUUIDS(Player paramPlayer);

  void disbandAllFactions();
  
  Location getHome(Player paramPlayer);
  
  List<?> getNearestClaims(Player paramPlayer);

  List<String> getAllFactionName();
}