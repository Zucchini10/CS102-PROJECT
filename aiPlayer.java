import java.util.*;

public class aiPlayer extends Player {
    
    public aiPlayer() {
        super();

        Random rand = new Random();
        String[] Names = {"John", "Jane", "Alex", "Chris", "Emma", "Olivia", "Liam", "Sophia"};
        String name = "\033[38;2;0;153;0m" + Names[rand.nextInt(Names.length)];
        super.setName(name);
        super.setAI(true);
    }

    // public Card calculateBestMove(Parade parade) {
    //     int paradeSize = parade.getParadeLine().size();

    //     Card bestCard = hand.get(0);
    //     int bestValue = Integer.MAX_VALUE;
    //     boolean found = false;
    //     for (Card card : hand) {
    //         int value = card.getValue();

    //         if (value >= paradeSize && value < bestValue) {
    //             bestValue = value;
    //             bestCard = card;
    //             found = true;
    //         }
    //     }

    //     // pick the smallest card
    //     if (!found) {
    //         bestCard = hand.get(0);
    //         bestValue = bestCard.getValue();

    //         for (Card card : hand) {
    //             int value = card.getValue();
    //             if (value < bestValue) {
    //                 bestValue = value;
    //                 bestCard = card;
    //             }
    //         }
    //     }

    //     return bestCard;
    // }

    // public Card playCard(Card card) {
    //     hand.remove(card);
    //     return card;
    // }
}
