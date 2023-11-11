import game.CommandProcessor;
import game.GameSession;

import java.util.Map;

public class RequestHandler {
    private Map<String, GameSession> sessionMap;

    public RequestHandler(Map<String, GameSession> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String handleRequest(String request) {
        // The request pattern is "playerId:command"
        String[] parts = request.split(":");
        if (parts.length != 2) {
            return "Error: Invalid request format.";
        }

        String playerId = parts[0];
        String command = parts[1];

        GameSession session = sessionMap.computeIfAbsent(playerId, k -> new GameSession());
        CommandProcessor processor = new CommandProcessor(session);
        return processor.processCommand(playerId, command);
    }
}
