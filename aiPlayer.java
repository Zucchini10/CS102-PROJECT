import java.util.*;

public class aiPlayer extends Player {
    private String difficulty;

    public aiPlayer(String difficulty) {
        super();
        this.difficulty = difficulty;

        Random rand = new Random();
        String[] Names = { "John", "Jane", "Alex", "Chris", "Emma", "Olivia", "Liam", "Sophia" };
        String name = "\033[38;2;0;153;0m" + Names[rand.nextInt(Names.length)];
        super.setName(name);
        super.setAI(true);
    }

    public Card chooseCard(Parade parade) {
        if (difficulty.equals("easy")) {
            return calculateEasyMove();
        } else {
            return calculateNormalMove(parade);
        }
    }

    private Card calculateEasyMove() {
        Card smallestCard = hand.get(0);
        for (Card card : hand) {
            if (card.getValue() < smallestCard.getValue()) {
                smallestCard = card;
            }
        }
        return smallestCard;
    }

    private Card calculateNormalMove(Parade parade) {
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

    public Card playCard(Card card) {
        hand.remove(card);
        return card;
    }

    public Card chooseCard() {
        Card chosen = null;
        if (difficulty.equals("easy")) {
            chosen = calculateEasyMove();
        } else if (difficulty.equals("normal")) {
            chosen = calculateNormalMove(null);
        } else if (difficulty.equals("hard")) {
            chosen = calculateHardMove();
        }

        hand.remove(chosen);
        return chosen;

    }
}
