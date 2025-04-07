import src.models.Card;
import src.models.Deck;
import src.player.PlayerCardPileStack;

public class testprintPlayerCardPileStack {
    public static void main(String[] args) {
        PlayerCardPileStack pcps = new PlayerCardPileStack();
        Deck d = new Deck();

        for (int i = 0; i < 15; i++) {
            Card c1 = d.drawCard();
            pcps.addCard(c1);
        }

        pcps.printPlayerCardPileStack();
    }
}
