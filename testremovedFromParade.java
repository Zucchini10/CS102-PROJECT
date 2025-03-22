import src.Card;
import src.Deck;
import src.Parade;
import src.cardPrinter;

public class testremovedFromParade {
    public static void main(String[] args) {
        Deck d = new Deck();

        Parade p = new Parade();

        for (int i = 0; i < 15; i++) {
            Card c1 = d.drawCard();
            p.addCardToParade(c1);
        }

        Card chosen = d.drawCard();
        chosen.printCard();
        p.printParade();

         new cardPrinter(p.removedFromParade(chosen));

    }
}
