package net.vtorganisation.ranking.config;

import net.vtorganisation.ranking.FactionObject;
import net.vtorganisation.ranking.Ranking;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FactionDataFile {

    private final File factionFolder;

    public FactionDataFile(){
        this.factionFolder = new File(Ranking.getInstance().getDataFolder() + File.separator + "factions");
    }

    public void loadRanking() {
        if(!factionFolder.exists()) {
            factionFolder.mkdir();

            Ranking.getInstance().getFactionManager().getAllFactionName().forEach(factionName -> {
                FactionObject factionObject = new FactionObject(factionName);
                factionObject.addData();
            });
            return;
        }

        File[] listFiles;
        for(int length = (listFiles = factionFolder.listFiles()).length, i = 0; i < length; ++i) {
            File file = listFiles[i];

            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
            String factionName = fileConfig.getString("factionName");
            long points = fileConfig.getLong("points");

            if(Ranking.getInstance().getFactionManager().getAllFactionName().contains(factionName)){
                FactionObject factionObject = new FactionObject(factionName);
                factionObject.setPoints(points);
                factionObject.addData();
            } else {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public void saveFactions(){
        Ranking.getInstance().getFactions().forEach(factionObject -> {
            File file = new File(this.factionFolder + File.separator + factionObject.getFactionName() + ".yml");
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

            try {
                fileConfiguration.set("factionName", factionObject.getFactionName());
                fileConfiguration.set("points", factionObject.getPoints());
                fileConfiguration.save(file);
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}