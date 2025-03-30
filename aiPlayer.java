import java.util.*;

public class aiPlayer extends Player {

    // static attribute shared across all instances of aiPlayer
    private static Set<String> usedNames = new HashSet<>();
    private String difficulty;

    public aiPlayer(String difficulty) {
        super();
        this.difficulty = difficulty;

        Random rand = new Random();
        String[] Names = { "John", "Jane", "Alex", "Chris", "Emma", "Olivia", "Liam", "Sophia", "Mary", "Gary", "Jerry", "Jason"};
        
        // Pick a random name and ensure it's not already used by another AI player
        String name;
        do {
            name = Names[rand.nextInt(Names.length)];
        } while (usedNames.contains(name));  // If the name has already been used, pick another

        // Add the name to the set of used names to avoid duplicates
        usedNames.add(name);

        // Set the name with green color and bold
        name = "\033[1m\033[38;2;0;153;0m" + name;
        
        super.setName(name);
        super.setAI(true);
    }


    public Card chooseCard(Parade parade) {
        Card chosen;

        // Decide strategy based on difficulty
        if (difficulty.equals("1")) {
            chosen = calculateEasyMove();
        } else if (difficulty.equals("3")) {
            chosen = calculateHardMove(parade);
        } else {
            chosen = calculateNormalMove(parade);
        }

        // Remove the chosen card from AI's hand after playing
        super.getHand().remove(chosen);

        // Return the chosen card to be added to the parade
        return chosen;
    }
    

    private Card calculateEasyMove() {
        List<Card> hand = getHand();
        Card smallestCard = hand.get(0);
        for (Card card : hand) {
            if (card.getValue() < smallestCard.getValue()) {
                smallestCard = card;
            }
        }
        return smallestCard;
    }

    private Card calculateNormalMove(Parade parade) {
        List<Card> hand = getHand();
        int paradeSize = parade.getParadeLine().size();
        Card bestCard = null;
        int bestValue = Integer.MAX_VALUE;
        boolean found = false;
        Card highestCard = hand.get(0);
        int highestValue = highestCard.getValue();

        for (Card card : hand) {
            int value = card.getValue();

            if (value > highestValue) {
                highestValue = value;
                highestCard = card;
            }

            if (value >= paradeSize && value < bestValue) {
                bestValue = value;
                bestCard = card;
                found = true;
            }
        }

        if (!found) {
            return highestCard;
        }

        return bestCard;
    }

    private Card calculateHardMove(Parade parade) {
        List<Card> hand = getHand();
        int paradeSize = parade.getParadeLine().size();
        Card bestCard = null;
        int bestValue = Integer.MAX_VALUE;
        boolean found = false;
        Card highestCard = hand.get(0);
        int highestValue = highestCard.getValue();
        String mostColour = findMajorityColour(parade.getParadeLine());

        for (Card card: hand) {
            int value = card.getValue();
            String colour = card.getColour();

            // get highest value card
            if (value > highestValue) {
                highestValue = value;
                highestCard = card;
            }
            if (value >= paradeSize && value < bestValue) {
                // choose card that do not match most common colour in parade
                if (!colour.equals(mostColour)) {
                    bestValue = value;
                    bestCard = card;
                    found = true;
                }
            }
        }
        // pick highest value if all cards < paradesize
        if (!found) {
            return highestCard;
        }

        return bestCard;
    }
    
    public String findMajorityColour(List<Card> cards) {
        // set each color count to 0
        int redCount = 0;
        int greenCount = 0;
        int orangeCount = 0;
        int blueCount = 0;
        int greyCount = 0;
        int purpleCount = 0;
        
        // count total number of each card colours
        for (Card card : cards) {
            String color = card.getColour();
            if (color.equals("Red")){
                redCount++;
            } else if (color.equals("Green")) {
                greenCount++;
            } else if (color.equals("Orange")) {
                orangeCount++;
            } else if (color.equals("Blue")) {
                blueCount++;
            } else if (color.equals("Grey")) {
                greyCount++;
            } else if (color.equals("Purple")){
                purpleCount++;
            }
        }
        
        int[] count = {redCount, greenCount, orangeCount, blueCount, greyCount, purpleCount};
        String[] colours = {"Red", "Green", "Orange", "Blue", "Grey", "Purple"};
        int maxIndex = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i] > count[maxIndex]) {
                maxIndex = i;
            }
        }
        return colours[maxIndex];
    }

    public Card playCard(Card card) {
        getHand().remove(card);
        return card;
    }

    public void endingTurnPrint(List<Card> paradeDrawn, Card top) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" \033[0m\033[1m \n========== End of " + super.getName() + "\033[0m\033[1m's Turn ==========\n");

        // print player card piles
        super.getStack().printPlayerCardPileStack();

        System.out.println(colourResetCode + "Press Enter to end CPU turn > ");
        sc.nextLine();
    
    }
}
