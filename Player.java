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
        System.out.println(name + "'s Hand : ");
        new cardPrinter(hand);
        String handNumberLine = "\033[0m\033[1m   1       2       3       4";
        if (hand.size() > 4){
            handNumberLine += "       5";
        }
        System.out.println(handNumberLine);
    }


    public void addIntoPlayerCardPile (List<Card> paradeDrawn) {
        for (Card card : paradeDrawn) {
            stack.addCard(card);
        }
    }

    public void endingTurnPrint(List<Card> paradeDrawn, Card top) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n========== End of " + name + "'s Turn ==========\n");

        // print drawn cards from parade
        System.out.println("Drawn from Parade : ");
        new cardPrinter(paradeDrawn);

        // print drawn card
        System.out.println("Drawn from deck");
        top.printCard();
        System.out.println("Press Enter to continue > ");
        sc.nextLine();

        // print player card piles
        stack.printPlayerCardPileStack();

        // print hand
        printHand();

        System.out.println("Press Enter to end turn > ");
        sc.nextLine();
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

    public boolean hasAllColours(){
        if (stack.containsAllColours()==true){
            return true;
        } 

        return false;
    }


}

