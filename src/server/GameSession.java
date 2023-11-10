public class GameSession {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private final GameStats stats;
    private boolean playerTurn;

    public GameSession() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Player();
        this.stats = new GameStats();
        this.playerTurn = true;

        startNewRound();
    }

    private void startNewRound() {
        player.clearHand();
        dealer.clearHand();
        player.hit(deck.draw());
        player.hit(deck.draw());
        dealer.hit(deck.draw());
        dealer.hit(deck.draw());
    }

    public void hit() {
        if (playerTurn) {
            player.hit(deck.draw());
            if (player.isBusted()) {
                playerTurn = false;
                endRound(false);
            }
        }
    }

    public void stand() {
        if (playerTurn) {
            playerTurn = false;
            dealerPlays();
        }
    }

    private void dealerPlays() {
        while (dealer.getHand().getHandValue() < 17) {
            dealer.hit(deck.draw());
        }
        determineOutcome();
    }

    private void determineOutcome() {
        int dealerValue = dealer.getHand().getHandValue();
        int playerValue = player.getHand().getHandValue();

        if (dealerValue > 21 || playerValue > dealerValue) {
            endRound(true);
        } else if (playerValue < dealerValue) {
            endRound(false);
        } else {
            stats.recordDraw();
            startNewRound();
        }
    }

    private void endRound(boolean playerWins) {
        if (playerWins) {
            stats.recordWin();
        } else {
            stats.recordLoss();
        }
        startNewRound();
    }

    public String getGameState() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player Hand: ").append(player.getHand().toString());
        sb.append("\nDealer Hand: ");
        if (playerTurn) {
            // Hide the dealer's first card and show only the second card
            sb.append("[Hidden], ");
            sb.append(dealer.getHand().getCards().get(1));
        } else {
            // Show all dealer's cards
            sb.append(dealer.getHand().toString());
        }
        sb.append("\nPlayer Turn: ").append(playerTurn ? "Yes" : "No");
        return sb.toString();
    }

    public GameStats getStats() {
        return stats;
    }
}
