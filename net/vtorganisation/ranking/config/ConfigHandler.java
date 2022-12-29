package net.vtorganisation.ranking.config;

import net.vtorganisation.ranking.Ranking;

import java.util.List;

public class ConfigHandler {

    public static String prefix;
    public static String playerIsOffline;
    public static String noPermission;
    public static String invalidNumber;
    public static String playerHasNotFaction;
    public static String cannotWithdrawMore;
    public static String pointsAddToFaction;
    public static String pointsRemoveToFaction;
    public static String pointsSetToFaction;
    public static String pointsResetToFaction;
    public static String rankingFormat;

    public static List<String> rankingHeader;
    public static List<String> rankingFooter;

    public static int backupInterval;
    public static boolean enableBackupMessage;

    private ConfigHandler() { throw new IllegalStateException("Utility class"); }

    public static void register(){
        Ranking ranking = Ranking.getInstance();

        enableBackupMessage = ranking.getConfigFile().getBoolean("ranking.backup-message");
        backupInterval = ranking.getConfigFile().getInt("ranking.backup-interval");

        rankingHeader = ranking.getConfigFile().getStringList("ranking.header");
        rankingFormat = ranking.getConfigFile().getString("ranking.format");
        rankingFooter = ranking.getConfigFile().getStringList("ranking.footer");

        prefix = ranking.getConfigFile().getString("messages.prefix");
        playerIsOffline = ranking.getConfigFile().getString("messages.not_online");
        noPermission = ranking.getConfigFile().getString("messages.not_permission");
        invalidNumber = ranking.getConfigFile().getString("messages.invalid_number");
        playerHasNotFaction = ranking.getConfigFile().getString("messages.player_has_not_faction");
        cannotWithdrawMore = ranking.getConfigFile().getString("messages.cannot_withdraw_more");
        pointsAddToFaction = ranking.getConfigFile().getString("messages.points_add_to_faction");
        pointsRemoveToFaction = ranking.getConfigFile().getString("messages.points_remove_to_faction");
        pointsSetToFaction = ranking.getConfigFile().getString("messages.points_set_to_faction");
        pointsResetToFaction = ranking.getConfigFile().getString("messages.points_reset_to_faction");
    }
}