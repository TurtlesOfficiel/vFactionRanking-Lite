package net.vtorganisation.ranking.task;

import net.vtorganisation.ranking.Ranking;
import net.vtorganisation.ranking.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSaves extends BukkitRunnable {

    @Override
    public void run() {
        long l = System.currentTimeMillis();
        Ranking.getInstance().getFactionDataFile().saveFactions();

        if(!ConfigHandler.enableBackupMessage) return;

        Bukkit.getOperators().stream().filter(OfflinePlayer::isOnline).forEach(offlinePlayer ->
                Bukkit.getPlayer(offlinePlayer.getName()).sendMessage(
                        "§6[§evFactionRanking-Lite§6]§a Auto-saves complete. §7(§d" + (System.currentTimeMillis() - l) + "ms§7)"));
    }
}