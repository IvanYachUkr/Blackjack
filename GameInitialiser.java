import java.io.IOException;

public class GameInitialiser {
    private final CommunicationManager communicationManager;
    private final DisplayManager displayManager;

    public GameInitializer(CommunicationManager communicationManager, DisplayManager displayManager) {
        this.communicationManager = communicationManager;
        this.displayManager = displayManager;
    }

    public void initializeGame() throws IOException {
        // Send a start command to the server to fetch the game rules
        String gameRules = communicationManager.communicate("start");
        displayManager.displayGameState("Game Rules:\n" + gameRules);
    }
}
