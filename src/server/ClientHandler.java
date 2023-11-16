import game.GameSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Map<String, GameSession> sessionMap;

    public ClientHandler(Socket socket, Map<String, GameSession> sessionMap) {
        this.clientSocket = socket;
        this.sessionMap = sessionMap;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            RequestHandler requestHandler = new RequestHandler(sessionMap);
            ResponseHandler responseHandler = new ResponseHandler(out);

            while ((inputLine = in.readLine()) != null) {
                String response = requestHandler.handleRequest(inputLine);
                responseHandler.send(response);
            }
        } catch (IOException e) {
            System.err.println("Exception caught when trying to listen on " +
                               "port or listening for a connection");
            System.err.println(e.getMessage());
        } finally {
            try {
                disconnectLog(clientSocket.getInetAddress().getHostAddress(),
                              clientSocket.getPort());
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void disconnectLog(String client_ip, int client_port) {
        LocalDateTime localtime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                          "yyyy-MM-dd HH:mm:ss"
                                      );
        System.out.println("[" + localtime.format(formatter) + "] Client " + 
                           client_ip + ":" + client_port + " disconnected");
    }
}
