import java.util.*;

class Player {
    private String name;
    private List<Card> hand;
    private PlayerCardPileStack stack;
    private int score;
    private boolean isAI;
    String colourResetCode = "\033[0m\033[1m";

    // constructor
    public Player(String name) {
        this.name = colourResetCode + name;
        hand = new ArrayList<Card>();
        stack = new PlayerCardPileStack();
        score = 0;
        isAI = false;
    }

    public Player() {
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
        score = stack.getTotalScore();
        return score;
    }

    public boolean isAI() {
        return isAI;
    }

    // setters
    public void setAI(boolean isAI) {
        this.isAI = isAI;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    // methods
    public void draw(Card card) {
        hand.add(card);

    }

    public Card getCard(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.get(index);
        }

        System.out.println("Invalid card selection.");
        return null;
    }

    public void printPlayerCardPile() {
        System.out.println(name + colourResetCode + "'s card piles : \n");
        stack.printPlayerCardPileStack();

    }

    public void printHand() {
        System.out.println(name + "'s Hand : ");
        new cardPrinter(hand);
        String handNumberLine = colourResetCode + "   1       2       3       4";
        if (hand.size() > 4) {
            handNumberLine += "       5";
        }
        System.out.println(handNumberLine);
    }

    public void addIntoPlayerCardPile(List<Card> paradeDrawn) {
        for (Card card : paradeDrawn) {
            stack.addCard(card);
        }
    }

    public void endingTurnPrint(List<Card> paradeDrawn, Card top) {
        Scanner sc = new Scanner(System.in);
        System.out
                .println(colourResetCode + "\n----- " + name + "'s Turn Overview -----\n");

        // print drawn cards from parade
        System.out.println("Drawn from Parade : ");
        if (paradeDrawn.isEmpty()) {
            System.out.println("No cards collected from the Parade.");
        } else {
            new cardPrinter(paradeDrawn);
        }
        System.out.println();

        // print drawn card
        System.out.println(colourResetCode + "Drawn from deck: ");
        if (top == null) {
            System.out.println("Deck is empty");
        } else {
            top.printCard();
        }

        System.out.println();

        // print player card piles
        stack.printPlayerCardPileStack();

        System.out.println(colourResetCode + "Press Enter to end turn > ");
        sc.nextLine();

    }

    public Card chooseCard(Parade parade) {
        Scanner sc = new Scanner(System.in);

        // Asking user which card they want to choose
        while (true) {
            printHand(); // Display the player's hand
            System.out.print("Choose a card > ");
            try {
                int chosenCardIndex = sc.nextInt(); // Get the user input

                // Check if the input is within a valid range (1 to hand.size())
                if (chosenCardIndex < 1 || chosenCardIndex > hand.size()) {
                    throw new InputMismatchException(); // If the number is out of range
                }

                // Get the chosen card and print it out
                Card chosen = hand.get(chosenCardIndex - 1);
                System.out.println(colourResetCode + "Chosen card: ");
                chosen.printCard();

                return chosen; // Return the chosen card and exit the loop

            } catch (InputMismatchException e) {
                // Handle invalid input (non-numeric or out of range)
                System.out.println("Invalid input! Please enter a valid number between 1 and " + hand.size() + ".");
                sc.nextLine(); // Clear the invalid input from the scanner buffer
            }
        }
    }

    public void removeCardFromHand(Card c) {
        hand.remove(c);
    }

    public boolean hasAllColours() {
        if (stack.containsAllColours() == true) {
            return true;
        }

        return false;
    }

}
