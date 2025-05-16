
import controller.LeaderboardController;
import model.ScoreEntry;
import java.util.List;
import java.util.Set;

public class Driver {

    public static final String USER = "user";
    public static final String COLON_SEPERATOR = " : ";
    public static final String LINE_SEPERATOR = "-------------------------";
    public static final String GAME1 = "game1";
    public static final String GAME2 = "game2";
    public static final String RANK_LIST_FOR_LEADERBOARD = "Rank list for leaderboard :";
    public static final String SUPPORTED_GAMES = "Supported games : ";

    public static void main(String[] args) {

        // Pass the leaderboard service to the controller
        LeaderboardController leaderboardController = new LeaderboardController();

        leaderboardController.registerGame(GAME1);
        leaderboardController.registerGame(GAME2);

        long currentMillis = System.currentTimeMillis();
        long oneDayMillis = 24 * 60 * 60 * 1000; // Milliseconds in a day

        // Create a new leaderboard for a game
        String leaderboardID = leaderboardController.createLeaderboard(GAME1, currentMillis / 1000,
                (currentMillis + oneDayMillis) / 1000);

        // Submit scores for users
        leaderboardController.submitScore(GAME1, USER + 1, 1000);
        leaderboardController.submitScore(GAME1, USER + 2, 2000);
        leaderboardController.submitScore(GAME1, USER + 3, 1500);
        leaderboardController.submitScore(GAME1, USER + 4, 1200);
        leaderboardController.submitScore(GAME1, USER + 5, 1800);

        // Fetch the rank list for the leaderboard
        List<ScoreEntry> rankList = leaderboardController.getLeaderboard(leaderboardID);

        System.out.println(RANK_LIST_FOR_LEADERBOARD);
        System.out.println(LINE_SEPERATOR);
        for (ScoreEntry entry : rankList) {
            System.out.println(entry.getUserID() + COLON_SEPERATOR + entry.getScore());
        }
        System.out.println(LINE_SEPERATOR);

        // Fetch the next and previous players relative to a user & it should only user5
        // as previous, user4 as next players for user3
        List<ScoreEntry> prevPlayers = leaderboardController.listPlayersPre(GAME1, leaderboardID, USER + 3, 1);
        List<ScoreEntry> nextPlayers = leaderboardController.listPlayersNext(GAME1, leaderboardID, USER + 3, 1);
        System.out.println("Previous [1] & Next [1] players relative to [user3] :");
        System.out.println(LINE_SEPERATOR);
        for (ScoreEntry entry : prevPlayers) {
            System.out.println(entry.getUserID() + ": " + entry.getScore());
        }
        for (ScoreEntry entry : nextPlayers) {
            System.out.println(entry.getUserID() + ": " + entry.getScore());
        }
        System.out.println(LINE_SEPERATOR);

        // Fetch the next and previous players relative to a user & it should only
        // user3, user4 as previous player & there is no next players for user1
        prevPlayers = leaderboardController.listPlayersPre(GAME1, leaderboardID, USER + 1, 2);
        nextPlayers = leaderboardController.listPlayersNext(GAME1, leaderboardID, USER + 1, 2);

        System.out.println("Previous [2] & Next [2] players relative to [user1] :");
        System.out.println(LINE_SEPERATOR);
        for (ScoreEntry entry : prevPlayers) {
            System.out.println(entry.getUserID() + COLON_SEPERATOR + entry.getScore());
        }
        for (ScoreEntry entry : nextPlayers) {
            System.out.println(entry.getUserID() + COLON_SEPERATOR + entry.getScore());
        }
        System.out.println(LINE_SEPERATOR);

        // Get the list of supported games
        Set<String> supportedGames = leaderboardController.getSupportedGames();
        System.out.println(SUPPORTED_GAMES);
        System.out.println(LINE_SEPERATOR);
        System.out.println(supportedGames);
        System.out.println(LINE_SEPERATOR);

        // Create a new leaderboard for a another game
        currentMillis = System.currentTimeMillis();
        oneDayMillis = 24 * 60 * 60 * 1000; // Milliseconds in a day
        String leaderboardID2 = leaderboardController.createLeaderboard(GAME2, currentMillis / 1000,
                (currentMillis + oneDayMillis) / 1000);

        leaderboardController.submitScore(GAME2, USER + 1, 500);
        leaderboardController.submitScore(GAME2, USER + 2, 800);
        leaderboardController.submitScore(GAME2, USER + 3, 600);
        leaderboardController.submitScore(GAME2, USER + 4, 700);

        // Fetch the rank list for the second leaderboard
        List<ScoreEntry> rankList2 = leaderboardController.getLeaderboard(leaderboardID2);
        System.out.println(RANK_LIST_FOR_LEADERBOARD);
        System.out.println(LINE_SEPERATOR);
        for (ScoreEntry entry : rankList2) {
            System.out.println(entry.getUserID() + ": " + entry.getScore());
        }
        System.out.println(LINE_SEPERATOR);

        // Fetch the next and previous players relative to a user3 for the second
        // leaderboard
        List<ScoreEntry> nextPlayers2 = leaderboardController.listPlayersNext(GAME2, leaderboardID2, USER + 3, 1);
        System.out.println("Next [1] players relative to [user3] :");
        System.out.println(LINE_SEPERATOR);
        for (ScoreEntry entry : nextPlayers2) {
            System.out.println(entry.getUserID() + ": " + entry.getScore());
        }
        System.out.println(LINE_SEPERATOR);

        // Get the list of supported games again
        Set<String> supportedGames2 = leaderboardController.getSupportedGames();
        System.out.println(SUPPORTED_GAMES);
        System.out.println(LINE_SEPERATOR);
        System.out.println(supportedGames2);
        System.out.println(LINE_SEPERATOR);
    }
}
