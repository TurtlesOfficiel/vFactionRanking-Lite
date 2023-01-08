package net.vtorganisation.ranking.utils;

import net.vtorganisation.ranking.config.ConfigHandler;
import org.bukkit.ChatColor;

public class RankingUtils {

    public static String rankingColor(int i) {
        if (i == 1) {
            return ChatColor.getByChar(ConfigHandler.colorIconsTop1) + "➊";
        }
        if (i == 2) {
            return ChatColor.getByChar(ConfigHandler.colorIconsTop2) + "➋";
        }
        if (i == 3) {
            return ChatColor.getByChar(ConfigHandler.colorIconsTop3) + "➌";
        }
        if(i == 4) {
            return ChatColor.getByChar(ConfigHandler.colorIconsTop4) + "➍";
        }
        if(i == 5) {
            return ChatColor.getByChar(ConfigHandler.colorIconsOthers) + "➎";
        }
        if(i == 6) {
            return ChatColor.getByChar(ConfigHandler.colorIconsOthers) + "➏";
        }
        if(i == 7) {
            return ChatColor.getByChar(ConfigHandler.colorIconsOthers) + "➐";
        }
        if(i == 8) {
            return ChatColor.getByChar(ConfigHandler.colorIconsOthers) + "➑";
        }
        if(i == 9) {
            return ChatColor.getByChar(ConfigHandler.colorIconsOthers) + "➒";
        }
        if(i == 10) {
            return ChatColor.getByChar(ConfigHandler.colorIconsOthers) + "➓";
        }
        return String.valueOf(ChatColor.getByChar(ConfigHandler.colorIconsOthers)) + i;
    }
}
