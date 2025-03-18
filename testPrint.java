public class testPrint {
    public static void main(String[] args) {
        Deck d = new Deck();
    Player p = new Player("Ryan");

        //put 5 cards into player hand
        for (int i = 0; i < 5; i++) {
            Card c1 = d.drawcard();
            p.drawFromDeck(c1);
        }

        p.printHand();
    }
    
    }


