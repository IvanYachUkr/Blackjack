package gameentities;

public class Card {
    enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        if (rank.ordinal() >= Rank.JACK.ordinal() && rank.ordinal() <= Rank.KING.ordinal()) {
            return 10;
        } else if (rank == Rank.ACE) {
            return 11; // The value of ACE can be 1 or 11 depending on the hand's value.
        } else {
            return rank.ordinal() + 2; // TWO is 0 ordinal, so +2 makes it the card's face value
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
