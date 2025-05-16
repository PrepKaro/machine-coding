package service;

import model.Game;
import model.Leaderboard;
import model.ScoreEntry;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class LeaderboardServiceImpl implements LeaderboardService {

    private final Map<String, Game> games = new ConcurrentHashMap<>();
    private final Map<String, Leaderboard> leaderboards = new ConcurrentHashMap<>();

    public Set<String> getSupportedGames() {
        return games.keySet();
    }

    public void registerGame(String gameID) {
        Game game = Game.builder()
                .gameId(gameID)
                .leaderboards(new LinkedList<>())
                .build();
        games.putIfAbsent(gameID, game);
    }

    public String createLeaderboard(String gameID, long startTime, long endTime) {
        Game game = games.get(gameID);
        if (game == null) throw new IllegalArgumentException("Game not found");

        String leaderBoardId = UUID.randomUUID().toString();

        Leaderboard leaderboard = Leaderboard.builder()
                .gameID(gameID)
                .leaderboardId(leaderBoardId)
                .startEpochSeconds(startTime)
                .endEpochSeconds(endTime)
                .entries(new ConcurrentHashMap<>())
                .sortedEntries(new ConcurrentSkipListSet<>())
                .build();

        game.addLeaderboard(leaderboard);
        leaderboards.put(leaderBoardId, leaderboard);
        return leaderboard.getLeaderboardId();
    }

    public List<ScoreEntry> getLeaderboard(String leaderboardID) {
        Leaderboard lb = leaderboards.get(leaderboardID);
        if (lb == null) throw new IllegalArgumentException("Leaderboard not found");
        return lb.getSortedEntries();
    }

    public void submitScore(String gameID, String userID, int score) {
        Game game = games.get(gameID);
        if (game == null) throw new IllegalArgumentException("Game not found");

        long now = System.currentTimeMillis() / 1000;
        for (Leaderboard lb : game.getLeaderboards()) {
            if (lb.isActive(now)) {
                lb.submitScore(userID, score);
            }
        }
    }

    public List<ScoreEntry> listPlayersNext(String gameID, String leaderboardID, String userID, int nPlayers) {
        return getAdjacentPlayers(leaderboardID, userID, nPlayers, true);
    }

    public List<ScoreEntry> listPlayersPrev(String gameID, String leaderboardID, String userID, int nPlayers) {
        return getAdjacentPlayers(leaderboardID, userID, nPlayers, false);
    }

    public List<ScoreEntry> getAdjacentPlayers(String leaderboardID, String userID, int nPlayers, boolean next) {
        Leaderboard lb = leaderboards.get(leaderboardID);
        if (lb == null) throw new IllegalArgumentException("Leaderboard not found");
        return lb.getAdjacentPlayers(userID, nPlayers, next);
    }
}
