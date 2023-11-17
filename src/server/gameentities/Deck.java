package gameentities;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private LinkedList<Card> cards;
    private static final int NUM_DECKS = 6; // Typically, Blackjack uses 6 decks.
    private static final int RESHUFFLE_THRESHOLD = 75; // Threshold to reshuffle.

    public Deck() {
        cards = new LinkedList<>();
        initializeDecks();
    }

    private void initializeDecks() {
        cards.clear();
        for (int i = 0; i < NUM_DECKS; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.size() <= RESHUFFLE_THRESHOLD) {
            initializeDecks();
        }
        return cards.poll();
    }

    public int remainingCards() {
        return cards.size();
    }
}
