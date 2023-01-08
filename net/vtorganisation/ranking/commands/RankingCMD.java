package net.vtorganisation.ranking.commands;

import net.vtorganisation.ranking.Ranking;
import net.vtorganisation.ranking.config.ConfigHandler;
import net.vtorganisation.ranking.utils.NumberFormatted;
import net.vtorganisation.ranking.utils.RankingUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sendRankingValues(sender);
		return false;
    }

	private void sendRankingValues(CommandSender sender) {
		HashMap<String, Long> map = new HashMap<>();
		Ranking.getInstance().getFactions().forEach(factionObject -> map.put(factionObject.getFactionName(), factionObject.getPoints()));

		List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
		list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

		ConfigHandler.rankingHeader.forEach(sender::sendMessage);

		int i = 0;
		for(Map.Entry<String, Long> entry : list.stream().limit(10).collect(Collectors.toList())) {
			String ranking = RankingUtils.rankingColor(i + 1);
			String factionName = entry.getKey();
			long points = entry.getValue();

			sender.sendMessage(ConfigHandler.rankingFormat
					.replace("%ranking%", ranking)
					.replace("%factionName%", factionName)
					.replace("%points%", NumberFormatted.formatted(points)));
			++i;
		}
		ConfigHandler.rankingFooter.forEach(sender::sendMessage);
	}
}