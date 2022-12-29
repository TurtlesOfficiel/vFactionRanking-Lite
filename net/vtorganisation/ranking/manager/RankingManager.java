package net.vtorganisation.ranking.manager;

import net.vtorganisation.ranking.FactionObject;
import net.vtorganisation.ranking.Ranking;

public class RankingManager {

    public Boolean isExist(String factionName){
        return Ranking.getInstance().getFactions().stream().anyMatch(factionObject -> factionObject.getFactionName().equalsIgnoreCase(factionName));
    }

    public FactionObject getFaction(String factionName){
        return Ranking.getInstance().getFactions().stream().filter(factionObject -> factionObject.getFactionName().equals(factionName)).findFirst().orElse(null);
    }
}