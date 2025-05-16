package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Builder
@Getter
@Setter
public class Leaderboard {
    private final String leaderboardId;
    private final String gameID;
    private final long startEpochSeconds;
    private final long endEpochSeconds;
    private ConcurrentHashMap<String, ScoreEntry> entries;
    private ConcurrentSkipListSet<ScoreEntry> sortedEntries;


    public synchronized void submitScore(String userID, int score) {
        ScoreEntry current = entries.get(userID);
        if (current == null || score > current.getScore()) {
            if (current != null) {
                sortedEntries.remove(current);
            }
            ScoreEntry newEntry = new ScoreEntry(userID, score);
            entries.put(userID, newEntry);
            sortedEntries.add(newEntry);
        }
    }

    public boolean isActive(long now) {
        return now >= startEpochSeconds && now <= endEpochSeconds;
    }

    public List<ScoreEntry> getSortedEntries() {
        return new ArrayList<>(sortedEntries);
    }

    public List<ScoreEntry> getAdjacentPlayers(String userID, int count, boolean next) {
        ScoreEntry current = entries.get(userID);
        if (current == null) return Collections.emptyList();

        List<ScoreEntry> list = new ArrayList<>(sortedEntries);
        int index = list.indexOf(current);
        if (index == -1) return Collections.emptyList();

        int start = next ? index + 1 : Math.max(0, index - count);
        int end = next ? Math.min(list.size(), index + 1 + count) : index;

        return list.subList(start, end);
    }
}
