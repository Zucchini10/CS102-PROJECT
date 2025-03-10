import java.util.*;

class Player {
    private String name;
    private List<Card> hand;
    private PlayerCardPile stack;
    private int score;
    private boolean isAI;

    // constructor
    public Player (String name) {
        this.name = name;
        hand = new ArrayList<Card>;
        stack = new PlayerCardPile;
        score = 0;
        isAI = false;
    }

    // getters
    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public PlayerCardPile getStack() {
        return stack;
    }

    public int getScore() {
        return score;
    }

    public boolean isAI() {
        return isAI;
    }

    // setter
    public void setAI(boolean isAI) {
        this.isAI = isAI;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // methods
    public void drawFromDeck(Card card) {
        hand.add(card);

    }

    public void printHand() {
        for (Card card : hand) {
            System.out.print(card);
        } 
    }

    public Card playCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            return card;
        } else {
            System.out.println("Invalid card!");
            return null;
        }
    }

    public void takeFromParade(Card card) {
        stack.add(card);
    }

}

