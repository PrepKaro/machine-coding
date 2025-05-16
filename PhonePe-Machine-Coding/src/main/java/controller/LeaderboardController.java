package controller;

import model.ScoreEntry;
import service.LeaderboardService;
import service.LeaderboardServiceImpl;

import java.util.List;
import java.util.Set;

public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController() {
        this.leaderboardService = new LeaderboardServiceImpl();
    }

    public void registerGame(String gameID) {
        leaderboardService.registerGame(gameID);
    }

    // Method to create a new leaderboard for a game
    public String createLeaderboard(String gameID, long startEpochSeconds, long endEpochSeconds) {
        return leaderboardService.createLeaderboard(gameID, startEpochSeconds, endEpochSeconds);
    }

    // Method to submit a score for a user in a game
    public void submitScore(String gameID, String userID, int score) {
        leaderboardService.submitScore(gameID, userID, score);
    }

    // Method to get the rank list for a specific leaderboard
    public List<ScoreEntry> getLeaderboard(String leaderboardID) {
        return leaderboardService.getLeaderboard(leaderboardID);
    }

    // Method to fetch the next N players and the previous N players relative to a
    // given user
    public List<ScoreEntry> listPlayersNext(String gameID, String leaderboardID, String userID, int nPlayers) {
        return leaderboardService.listPlayersNext(gameID, leaderboardID, userID, nPlayers);
    }

    public List<ScoreEntry> listPlayersPre(String gameID, String leaderboardID, String userID, int nPlayers) {
        return leaderboardService.listPlayersPrev(gameID, leaderboardID, userID, nPlayers);
    }

    // Method to get a list of supported GAME IDs
    public Set<String> getSupportedGames() {
        return leaderboardService.getSupportedGames();
    }
}
