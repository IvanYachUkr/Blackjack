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
                if (gameSession.isRoundEnded() || gameSession.player.isBusted()) {
                    return gameSession.getFinalStateAndOutcome();
                }
                break;
            case "stand":
                gameSession.stand();
                return gameSession.getFinalStateAndOutcome();
            case "round":
                gameSession.startNewRoundManually();
                return gameSession.getGameState();
            case "start":
                return startGame();
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

        // Combine the game rules and the initial game state
        String initialState = gameSession.getGameState();
        return gameRules + "\n\n" + initialState;
    }
}
