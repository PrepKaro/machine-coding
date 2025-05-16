package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Builder
@Getter
@Setter
public class Game {
    private String gameId;
    private List<Leaderboard> leaderboards;

    public void addLeaderboard(Leaderboard leaderboard) {
        leaderboards.add(leaderboard);
    }
}
