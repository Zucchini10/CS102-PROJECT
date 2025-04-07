package utils;
import java.util.List;

import models.Card;

public class cardPrinter {

    // Helper class to print out a list of cards using their card representation
    public cardPrinter(List<Card> cards){

        // will append the lines of card representation and print out sequentially for the 4 lines
        String line;
        for (int i = 0; i < 5; i++) {
            line = "\033[1m";
            for (int j = 0; j < cards.size(); j++) {
                line += (cards.get(j).cardRepresentation()).get(i);
                line += " ";
            }
            System.out.println(line);

        }
    }


}
