package game;

import gameentities.Deck;
import gameentities.GameStats;
import gameentities.Player;

public class GameSession {
    private final Deck deck;
    final Player player;
    private final Player dealer;
    private final GameStats stats;
    boolean playerTurn;

    public GameSession() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Player();
        this.stats = new GameStats();
        this.playerTurn = true;

        startNewRound();
    }

    public boolean isRoundEnded() {
        return !playerTurn && !dealer.isBusted() && dealer.getHand().getHandValue() >= 17;
    }

    public String getFinalStateAndOutcome() {
        String finalState = getGameState();
        String outcome;
        if (player.isBusted()) {
            outcome = "Player Busted. Dealer Wins.";
        } else if (dealer.isBusted() || player.getHand().getHandValue() > dealer.getHand().getHandValue()) {
            outcome = "Player Wins.";
        } else if (player.getHand().getHandValue() < dealer.getHand().getHandValue()) {
            outcome = "Dealer Wins.";
        } else {
            outcome = "It's a Draw.";
        }
        return finalState + "\nOutcome: " + outcome;
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
                // Do not call endRound here, just change the playerTurn to false
            }
        }
    }
    public void startNewRoundManually() {
        startNewRound();
        playerTurn = true;
    }

    public void stand() {
        if (playerTurn) {
            playerTurn = false;
            dealerPlays();
        }
    }

    void dealerPlays() {
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
        // Do not start a new round here
    }

    public String getGameState() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player Hand: ").append(player.getHand().toString());
        sb.append("\nDealer Hand: ");
        if (playerTurn) {
            sb.append("[Hidden], ");
            sb.append(dealer.getHand().getCards().get(1));
        } else {
            sb.append(dealer.getHand().toString());
        }
        sb.append("\nPlayer Turn: ").append(playerTurn ? "Yes" : "No");
        return sb.toString();
    }

    public GameStats getStats() {
        return stats;
    }
}
