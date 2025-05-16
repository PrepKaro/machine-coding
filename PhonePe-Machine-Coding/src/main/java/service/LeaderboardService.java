package service;

import model.ScoreEntry;

import java.util.List;
import java.util.Set;

public interface LeaderboardService {

    void registerGame(String gameID);

    Set<String> getSupportedGames();

    String createLeaderboard(String gameID, long startEpochSeconds, long endEpochSeconds);

    List<ScoreEntry> getLeaderboard(String leaderboardId);

    void submitScore(String gameID, String userID, int score);

    List<ScoreEntry> listPlayersNext(String gameID, String leaderboardID, String userID, int nPlayers);

    List<ScoreEntry> listPlayersPrev(String gameID, String leaderboardID, String userID, int nPlayers);
}
