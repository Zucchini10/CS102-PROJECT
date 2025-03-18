// Testing my card string representation 

import java.util.*;

public class test {
    public static void main(String[] args) {
        Deck d = new Deck();
        List<Card> paradeExample = new ArrayList<>();

        // draw 20 different random cards
        for (int i = 0; i < 20; i++) {
            Card c1 = d.drawcard();
            paradeExample.add(c1);
        }
        // print out the 20 diff cards
        String line;
        System.out.println("PARADE : ");
        for (int i = 0; i < 4; i++) {
            line = "";
            for (int j = 0; j < paradeExample.size(); j++) {
                line += (paradeExample.get(j).cardRepresentation()).get(i);
                line += " ";
            }
            System.out.println(line);

        }

    }
}