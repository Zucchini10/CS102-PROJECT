import src.models.Card;
import src.models.Deck;
import src.models.Parade;

public class testprintCard {
    public static void main(String[] args) {
        Deck d = new Deck();
        Parade p = new Parade();

            Card c1 = d.drawCard();
            c1.printCard();
        
    }
}
