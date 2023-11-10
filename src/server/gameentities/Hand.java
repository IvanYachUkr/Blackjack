import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getHandValue() {
        int handValue = 0;
        int aceCount = 0;

        for (Card card : cards) {
            int value = card.getValue();
            handValue += value;

            // Track aces for possible reduction later
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
            }
        }

        // Reduce aces from 11 to 1 if we would bust otherwise
        while (handValue > 21 && aceCount > 0) {
            handValue -= 10; // Reduce the total hand value by 10
            aceCount--; // One ace has been reduced
        }

        return handValue;
    }

    public boolean isBust() {
        return getHandValue() > 21;
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards); // Return a defensive copy
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : cards) {
            handString.append(card.toString()).append(" ");
        }
        return handString.toString().trim();
    }
}
