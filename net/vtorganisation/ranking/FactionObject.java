package net.vtorganisation.ranking;

public class FactionObject {

    private final String factionName;
    private int points;
    
    public FactionObject(String factionName) {
        this.factionName = factionName;
        this.points = 0;
    }

    public String getFactionName() {
        return this.factionName;
    }

    public int getPoints() {
        return this.points;
    }
    
    public void setPoints(int amount) {
        points = amount;
    }
    
    public void addPoints(int amount) {
    	points = this.points + amount;
    }
    
    public void removePoints(int amount) {
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
}