package game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class GameRulesHandler {

    public String getGameRules() {
        String rulesFilePath = "game_rules_blackjack.txt"; // Replace with the actual path
        InputStream inputStream = getClass().getResourceAsStream(rulesFilePath);

        if (inputStream == null) {
            return "Game rules file not found.";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            return "Error reading game rules: " + e.getMessage();
        }
    }
}
