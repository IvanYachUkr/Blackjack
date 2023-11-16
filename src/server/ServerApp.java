import game.GameSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerApp {
    private static final int PORT = 7777;
    private static final ConcurrentHashMap<String, GameSession> sessionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println(timestamp() + " Server starting on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(timestamp() + " Server is listening on port " +
                               PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                connectionLog(clientSocket.getInetAddress().getHostAddress(),
                              clientSocket.getPort());

                ClientHandler clientHandler = new ClientHandler(clientSocket, sessionMap);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void connectionLog(String client_ip, int client_port) {
        System.out.println(timestamp() + " New client connected from " +
                           client_ip + ":" + client_port);
    }

    public static String timestamp() {
        LocalDateTime localtime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                          "yyyy-MM-dd HH:mm:ss"
                                      );
        return "[" + localtime.format(formatter) + "]";        
    }
}
