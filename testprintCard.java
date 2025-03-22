import src.Card;
import src.Deck;
import src.Parade;

public class testprintCard {
    public static void main(String[] args) {
        Deck d = new Deck();
        Parade p = new Parade();

            Card c1 = d.drawcard();
            c1.printCard();
        
    }
}
