package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.Object;
import java.util.Objects;

@Builder
@Getter
@Setter
public class ScoreEntry implements Comparable<ScoreEntry>{
    private String userID;
    private int score;

    @Override
    public int compareTo(ScoreEntry other) {
        int scoreCompare = Integer.compare(other.score, this.score); // descending
        return scoreCompare != 0 ? scoreCompare : this.userID.compareTo(other.userID);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScoreEntry)) return false;
        ScoreEntry o = (ScoreEntry) obj;
        return userID.equals(o.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    @Override
    public String toString() {
        return "User: " + userID + ", Score: " + score;
    }
}
