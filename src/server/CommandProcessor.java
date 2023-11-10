public class CommandProcessor {
    private final GameSession gameSession;

    public CommandProcessor(GameSession session) {
        this.gameSession = session;
    }

    public String processCommand(String playerId, String command) {
        switch (command.toLowerCase()) {
            case "hit":
                gameSession.hit();
                break;
            case "stand":
                gameSession.stand();
                break;
            default:
                return "Error: Unknown command.";
        }

        if (gameSession.isPlayerTurn()) {
            return gameSession.getGameState();
        } else {
            // Automatically play for the dealer if it's their turn
            gameSession.dealerPlays();
            return gameSession.getGameState() + "\n" + gameSession.getRoundOutcome();
        }
    }
}
