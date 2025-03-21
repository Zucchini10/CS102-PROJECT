import java.util.*;

//import Testing.PlayerCardPile;

class Player {
    private String name;
    private List<Card> hand;
    private PlayerCardPileStack stack;
    private int score;
    private boolean isAI;

    // constructor
    public Player (String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        stack = new PlayerCardPileStack();
        score = 0;
        isAI = false;
    }

    public Player () {
        hand = new ArrayList<Card>();
        stack = new PlayerCardPileStack();
        score = 0;

    }

    // getters
    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public PlayerCardPileStack getStack() {
        return stack;
    }

    public int getScore() {
        score = stack.totalScore();
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
    public void draw(Card card) {
        hand.add(card);

    }

    public Card getCard (int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.get(index);
        }

        System.out.println("Invalid card selection.");
        return null;  
    }
    
    
    public void printPlayerCardPile() {
        stack.printPlayerCardPileStack();
        
    } 
    
    public void printHand(){
        String line;
        System.out.println(name + "'s Hand : ");
        new cardPrinter(hand);
    }


    public void addIntoPlayerCardPile (List<Card> paradeDrawn) {
        for (Card card : paradeDrawn) {
            stack.addCard(card);
        }
    }

    public void endTurnPrint(List<Card> paradeDrawn, Card top) {
        System.out.println("\n========== End of " + name + "'s Turn ==========\n");
        System.out.print("Drawn from parade");
        for (Card card : paradeDrawn){
            System.out.print(card);
        }
        System.out.println();
        System.out.print("Drawn from deck");
        System.out.println(top);
        System.out.println("======================================\n");
    }

    public Card chooseCard() {
        // asking user which card he wants to choose
        Scanner sc = new Scanner(System.in);
        printHand();
        System.out.println("Choose a card >");
        int chosenCardIndex = sc.nextInt();
        Card chosen = hand.get(chosenCardIndex);

        // remove card from hand after playing it
        hand.remove(chosenCardIndex);
        return chosen;

    }


}

