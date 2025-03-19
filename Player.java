import java.util.*;

import Testing.PlayerCardPile;

class Player {
    private String name;
    private List<Card> hand;
    private PlayerCardPile stack;
    private int score;
    private boolean isAI;

    // constructor
    public Player (String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        stack = new PlayerCardPile();
        score = 0;
        isAI = false;
    }

    public Player () {
        hand = new ArrayList<Card>();
        stack = new PlayerCardPile();
        score = 0;

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

    public void setName(String name){
        this.name = name;
    }
    // methods
    public void drawFromDeck(Card card) {
        hand.add(card);

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

    public void printHand(){
        String line;
        System.out.println(name + "'s Hand : ");
        for (int i = 0; i < 5; i++) {
            line = "";
            for (Card c: hand) {
                line += (c.cardRepresentation()).get(i);
                line += " ";
            }
            System.out.println(line);

        }
        for (int i = 0;i<hand.size();i++){
            System.out.print("\033[0m" + "   "+i+ "    " );
        }
        System.out.println();
    }
    // public void takeFromParade(Card card) {
    //     stack.add(card);
    // }

}

