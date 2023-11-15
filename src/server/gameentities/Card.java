package gameentities;

public class Card {
    enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    }

    private static final char heart = '\u2665';
    private static final char diamond = '\u2666';
    private static final char club = '\u2663';
    private static final char spade = '\u2660';

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
        char suit_sign = heart;
        switch (suit) {
            case DIAMONDS:
                suit_sign = diamond; break;
            case CLUBS:
                suit_sign = club; break;
            case SPADES:
                suit_sign = spade; break;
        }
        switch (rank) {
            case TEN:
                return "10" + suit_sign;
            case JACK:
                return "J" + suit_sign;
            case QUEEN:
                return "Q" + suit_sign;
            case KING:
                return "K" + suit_sign;
            case ACE:
                return "A" + suit_sign;
            default: // case of 2-9
                return String.valueOf((char)( '2'+rank.ordinal() )) +
                       String.valueOf(suit_sign);
        }
    }
}

