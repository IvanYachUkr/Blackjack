import java.io.IOException;
import java.util.UUID;

public class ClientApp {
    private static final String QUIT_COMMAND = "quit";
    private final ConnectionManager connectionManager;
    private final UserInputHandler userInputHandler;
    private final DisplayManager displayManager;
    private final CommunicationManager communicationManager;
    private final MessageFormatter messageFormatter;
    private final GameInitialiser gameInitializer;

    public ClientApp() {
        String playerId = generateUniqueID();
        connectionManager = new ConnectionManager();
        userInputHandler = new UserInputHandler();
        displayManager = new DisplayManager();
        messageFormatter = new MessageFormatter(playerId);
        communicationManager = new CommunicationManager(connectionManager, messageFormatter);
        gameInitializer = new GameInitialiser(communicationManager, displayManager);
    }

    private String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public void startGame() {
        try {
            connectionManager.openConnection();
            gameInitializer.initializeGame(); // Initialize game and fetch rules

            //System.out.println("Connected to the game server with Player ID: " + messageFormatter.getPlayerId());
            String command;
            do {
                System.out.println("Enter command (type '" + QUIT_COMMAND + "' to exit): ");
                command = userInputHandler.getUserCommand();
                if (QUIT_COMMAND.equalsIgnoreCase(command)) {
                    break;
                }
                String gameState = communicationManager.communicate(command);
                displayManager.displayGameState(gameState);
            } while (true);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp();
        clientApp.startGame();
    }
}
