public class test2 {
    public static void main(String[] args) {
        Deck d = new Deck();
        Parade p = new Parade();

        // put 6 cards into parade
        for (int i = 0; i < 6; i++) {
            Card c1 = d.drawcard();
            p.addCardToParade(c1);
        }

        p.displayParade();
    }
}
