//Name:  Yash Tyagi

package casino_game;

public class Card {

    private final String face;
    private final String suit;

    public Card(String cardFace, String cardSuit) {
        face = cardFace;
        suit = cardSuit;
    }

    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public String toString() {
        return face + " of " + suit;
    }

}