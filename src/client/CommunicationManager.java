import java.io.IOException;

public class CommunicationManager {
    private final ConnectionManager connectionManager;
    private final MessageFormatter messageFormatter;

    public CommunicationManager(ConnectionManager connectionManager, MessageFormatter messageFormatter) {
        this.connectionManager = connectionManager;
        this.messageFormatter = messageFormatter;
    }

    public String communicate(String command) throws IOException {
        String formattedMessage = messageFormatter.formatMessage(command);
        connectionManager.sendToServer(formattedMessage);
        return connectionManager.receiveFromServer();
    }
}
