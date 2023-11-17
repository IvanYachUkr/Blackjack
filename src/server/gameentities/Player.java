package gameentities;

public class Player {
    private final Hand hand;

    public Player() {
        hand = new Hand();
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public boolean isBusted() {
        return hand.getHandValue() > 21;
    }

    public boolean isBlackJack() {
        return hand.getHandValue() == 21;
    }

    public Hand getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }
}
