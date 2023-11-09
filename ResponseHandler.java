import java.io.PrintWriter;

public class ResponseHandler {
    private PrintWriter out;

    public ResponseHandler(PrintWriter out) {
        this.out = out;
    }

    public void send(String message) {
        out.println(message);
        out.flush();
    }
}
