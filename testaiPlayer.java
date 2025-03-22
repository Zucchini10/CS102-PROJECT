// Testing my card string representation 

import java.util.*;

public class testaiPlayer {
    public static void main(String[] args) {
        Deck d = new Deck();
        Player p = new aiPlayer();
        System.out.println(p.getName());
        // draw 20 different random cards
        for (int i = 0; i < 5; i++) {
            Card c1 = d.drawcard();
            p.drawFromDeck(c1);
        }
        p.printHand();

    }
}