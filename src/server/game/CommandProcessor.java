package game;

public class CommandProcessor {
    private final GameSession gameSession;

    public CommandProcessor(GameSession session) {
        this.gameSession = session;
    }

    public String processCommand(String playerId, String command) {
        switch (command.toLowerCase()) {
            case "hit": case "h":
                gameSession.hit();
                if (gameSession.isRoundEnded() || gameSession.player.isBusted())
                    return gameSession.getFinalStateAndOutcome();
                break;
            case "stand": case "s":
                gameSession.stand();
                return gameSession.getFinalStateAndOutcome();
            case "round": case "r":
                gameSession.startNewRoundManually();
                return gameSession.getGameState();
            case "start":
                return startGame();
            case "help":
                return "Commands: h[it], s[tand], r[ound], quit, help";
            default:
                return "Error: Unknown command.";
        }

        if (gameSession.playerTurn) {
            return gameSession.getGameState();
        } else {
            gameSession.dealerPlays();
            if (gameSession.isRoundEnded()) {
                return gameSession.getFinalStateAndOutcome();
            } else {
                return gameSession.getGameState();
            }
        }
    }

    private String startGame() {
        GameRulesHandler rulesHandler = new GameRulesHandler();
        String gameRules = rulesHandler.getGameRules();

        // Clear data from the previous round
        gameSession.startNewRoundManually();
        // Combine the game rules and the initial game state
        String initialState = gameSession.getGameState();
        return gameRules + "\n\n" + initialState;
    }
}
