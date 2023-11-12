import java.io.PrintWriter;

public class ResponseHandler {
    private PrintWriter out;

    public ResponseHandler(PrintWriter out) {
        this.out = out;
    }

    public void send(String message) {
        // Append the "END_OF_MESSAGE" marker to the message
        String modifiedMessage = message + "\nEND_OF_MESSAGE";
        out.println(modifiedMessage);
        out.flush();
    }
}

