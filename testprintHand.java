import src.Card;
import src.Deck;
import src.Player;

public class testprintHand {
    public static void main(String[] args) {
        Deck d = new Deck();
        Player p = new Player("Ryan");

        //put 5 cards into player hand
        for (int i = 0; i < 5; i++) {
            Card c1 = d.drawCard();
            p.draw(c1);
        }

        p.printHand();
    }
    
    }


