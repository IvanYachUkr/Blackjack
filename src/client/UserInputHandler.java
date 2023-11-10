import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getUserCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }
}
