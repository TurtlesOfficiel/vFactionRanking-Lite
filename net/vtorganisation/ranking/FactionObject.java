package net.vtorganisation.ranking;

import net.vtorganisation.ranking.utils.RankingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FactionObject {

    private final String factionName;
    private String ranking;
    private long points;

    public FactionObject(String factionName) {
        this.factionName = factionName;
        this.points = 0;
    }

    public String getFactionName() {
        return this.factionName;
    }

    public long getPoints() {
        return this.points;
    }
    
    public void setPoints(long amount) {
        points = amount;
    }
    
    public void addPoints(long amount) {
    	points = this.points + amount;
    }
    
    public void removePoints(long amount) {
    	points = this.points < amount ? 0 : this.points - amount;
    }

    public void addData(){
        Ranking.getInstance().getFactions().add(this);
    }

    public void removeData() {
        Ranking.getInstance().getFactions().remove(this);
    }

    public void changeName(String newName) {
        FactionObject oldFaction = Ranking.getInstance().getRankingManager().getFaction(this.factionName);
        FactionObject newFaction = new FactionObject(newName);
        newFaction.setPoints(oldFaction.getPoints());
        Ranking.getInstance().getFactions().add(newFaction);
        Ranking.getInstance().getFactions().remove(oldFaction);
    }

    public String getRanking(){
        HashMap<String, Long> map = new HashMap<>();
        Ranking.getInstance().getFactions().forEach(factionObject -> map.put(factionObject.getFactionName(), factionObject.getPoints()));

        List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        int i = 0;
        int factionSize = Ranking.getInstance().getFactionManager().getAllFactionName().size();

        for(Map.Entry<String, Long> entry : list.stream().limit(factionSize).collect(Collectors.toList())) {
            String factionName = entry.getKey();
            if(this.factionName.equals(factionName)){
                this.ranking = RankingUtils.rankingColor(i + 1);
            }
            ++i;
        }
        return this.ranking;
    }
}