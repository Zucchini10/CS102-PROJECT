package player;
import java.util.*;

import models.Card;
import models.Parade;
import utils.cardPrinter;

public class Player {
    private String name;
    private List<Card> hand;
    private PlayerCardPileStack stack;
    private int score;
    private boolean isAI;
    protected String colourResetCode = "\033[0m\033[1m";

    // constructor
    public Player(String name) {
        //bold name 
        this.name = "\033[1m" + name;
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

    public void printPlayerCardPile() {
        System.out.println(colourResetCode + "==============================================================================================================\n");
        System.out.println(name + colourResetCode + "'s card piles : \n");
        stack.printPlayerCardPileStack();
        System.out.println(colourResetCode + "==============================================================================================================\n");
    }

    public void printHand() {
        System.out.println(colourResetCode + name + "'s Hand : ");
        new cardPrinter(hand);
        String handNumberLine = colourResetCode + "   1       2       3";
        if (hand.size() == 4) {
            handNumberLine += "       4";
        } else if (hand.size() > 4) {
            handNumberLine += "       4       5";
        }
        System.out.println(handNumberLine);
    
    }

    public void addIntoPlayerCardPile(List<Card> paradeDrawn) {
        //Add a list of cards into playercardpile
        for (Card card : paradeDrawn) {
            stack.addCard(card);
        }
    }

    public void endingTurnPrint(List<Card> paradeDrawn, Card top) {
        Scanner sc = new Scanner(System.in);
        System.out.println(colourResetCode + "\n----- " + name + "'s Turn Overview -----\n");

        // print drawn cards from parade
        System.out.println("Drawn from Parade : ");
        if (paradeDrawn.isEmpty()) {
            System.out.println("No cards collected from the Parade.");
        } else {
            new cardPrinter(paradeDrawn);
        }
        System.out.println();

        // print drawn card
        if (!(top == null)) {
            System.out.println(colourResetCode + "Drawn from deck: ");
            top.printCard();
        } 

        System.out.println();

        // print player card piles
        printPlayerCardPile();

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
    public Card discardCard() {
        // asking user which card he wants to choose
        Scanner sc = new Scanner(System.in);
        printHand();
        System.out.print("Choose a card to discard > ");
        int chosenCardIndex = sc.nextInt();
        Card chosen = hand.get(chosenCardIndex - 1);

        // remove card from hand after playing it
        hand.remove(chosenCardIndex - 1);
        return chosen;

    }

    public boolean hasAllColours() {
        if (stack.containsAllColours() == true) {
            return true;
        }

        return false;
    }

}
