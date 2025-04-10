package models;
import java.util.*;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();

        // create 11 cards for the 6 colors and add to deck before shuffling
        String[] colors = { "RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE" };
        for (String color : colors) {
            for (int i = 0; i < 11; i++) {
                Card newcard = new Card(color, i);
                cards.add(newcard);
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);

    }

    public boolean isEmpty() {
        if (cards.size() == 0) {
            return true;
        }
        return false;
    }

    public int getNumberOfRemainingCards() {
        return cards.size();
    }

    public Card drawCard() {

        // get top most card form deck before removing it
        Card top = cards.get(0);
        cards.remove(0);
        return top;
    }

    // getters and setters
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
