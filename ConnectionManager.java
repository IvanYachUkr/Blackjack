import java.io.*;
import java.net.Socket;

public class ConnectionManager {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    // Localhost IP and the port number are hardcoded for simplicity
    private final String serverAddress = "localhost";
    private final int port = 7777;

    public ConnectionManager() {
        // The constructor doesn't need to do anything since the address and port are hardcoded
    }

    public void openConnection() throws IOException {
        // Always connect to the hardcoded server address and port
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendToServer(String message) throws IOException {
        if (out != null) {
            out.println(message);
        } else {
            throw new IOException("Connection is not open, cannot send message");
        }
    }

    public String receiveFromServer() throws IOException {
        if (in != null) {
            return in.readLine();
        } else {
            throw new IOException("Connection is not open, cannot receive message");
        }
    }

    public void closeConnection() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
