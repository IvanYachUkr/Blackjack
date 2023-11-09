public class GameStats {
    private int wins;
    private int losses;
    private int draws;
    private int roundsPlayed;

    public void recordWin() {
        wins++;
        roundsPlayed++;
    }

    public void recordLoss() {
        losses++;
        roundsPlayed++;
    }

    public void recordDraw() {
        draws++;
        roundsPlayed++;
    }

    // Getters
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }
}
