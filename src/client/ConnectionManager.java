import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConnectionManager {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private String serverAddress = "localhost";
    private int port = 7777;

    public ConnectionManager(String[] servinfo) {
        if (servinfo.length > 0) {
            if (isIPAddr(servinfo[0]))
                this.serverAddress = servinfo[0];
            else
                System.out.println("WARNING: Could not parse the IP address." +
                                   " Continuing with the default one.");

            if (servinfo.length > 1) {
                if (isPort(servinfo[1]))
                    this.port = Integer.parseInt(servinfo[1]);
                else
                    System.out.println("WARNING: Could not parse the port. " +
                                       "Continuing with the default one.");

                if (servinfo.length > 2)
                    System.out.println("WARNING: Too many arguments!");
            }
        } // else use the defaults
    }

    public void openConnection() throws IOException {
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
             );
    }

    public void sendToServer(String message) throws IOException {
        if (out != null) {
            out.println(message);
        } else {
            throw new IOException("Connection is not open, " +
                                  "cannot send message");
        }
    }

    public String receiveFromServer() throws IOException {
        if (in != null) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                // Check for the end-of-message marker before appending
                if (line.equals("END_OF_MESSAGE")) {
                    break;
                }
                response.append(line).append("\n");
            }
            return response.toString();
        } else {
            throw new IOException("Connection is not open, " +
                                  "cannot receive message");
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

    private static boolean isIPAddr(String addr) {
        String ipV4Pattern = "^(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\." +
                             "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\." +
                             "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\." +
                             "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";
        String ipV6Pattern = "^([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}$" +
                     "|^([0-9a-fA-F]{1,4}:){1,7}:$" +
                     "|^([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}$" +
                     "|^([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}$" +
                     "|^([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}$" +
                     "|^([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}$" +
                     "|^([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}$" +
                     "|^[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})$";

        Pattern ipv4Pattern = Pattern.compile(ipV4Pattern);
        Pattern ipv6Pattern = Pattern.compile(ipV6Pattern);

        Matcher ipv4Matcher = ipv4Pattern.matcher(addr);
        Matcher ipv6Matcher = ipv6Pattern.matcher(addr);

        return ipv4Matcher.matches() || ipv6Matcher.matches();
    }

    private static boolean isPort(String number) {
        try {
            int port = Integer.parseInt(number);
            if (port > 0 && port < 65536)
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
