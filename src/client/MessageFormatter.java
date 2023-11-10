public class MessageFormatter {
    private final String playerId;

    public MessageFormatter(String playerId) {
        this.playerId = playerId;
    }

    public String formatMessage(String command) {
        return playerId + ":" + command;
    }
}
