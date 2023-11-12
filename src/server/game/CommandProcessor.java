package game;

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
            case "start":
                return startGame();
            default:
                return "Error: Unknown command.";
        }

        if (gameSession.playerTurn) {
            return gameSession.getGameState();
        } else {
            // Automatically play for the dealer if it's their turn
            gameSession.dealerPlays();
            return gameSession.getGameState() + "\n"; //+ gameSession.getRoundOutcome();
        }
    }

    private String startGame() {
        GameRulesHandler rulesHandler = new GameRulesHandler();
        String gameRules = rulesHandler.getGameRules();

        // Combine the game rules and the initial game state
        String initialState = gameSession.getGameState();
        return gameRules + "\n\n" + initialState;
    }
}
