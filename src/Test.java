
import java.util.*;

import game.Game;
import models.Card;
import models.Deck;
import models.Parade;
import player.Player;

public class Test {
    public static void main(String[] args) {
        Card c1 = new Card ("BLUE",0);
        Card c2 = new Card ("BLUE",1);
        Card c3 = new Card ("BLUE",2);
        Card c4 = new Card ("RED",0);
        Card c5 = new Card ("RED",1);
        Card c6 = new Card ("RED",2);
        Card c7 = new Card ("GREY",0);
        Card c8 = new Card ("GREY",1);
        Card c9 = new Card ("GREY",2);
        Card c10 = new Card ("PURPLE",0);
        Card c11 = new Card ("PURPLE",1);
        Card c12 = new Card ("PURPLE",1);


        Parade pa = new Parade();
        pa.addCardToParade(c12);
        pa.addCardToParade(c1);
        pa.addCardToParade(c3);
        pa.addCardToParade(c4);

        List<Card> l1 = new ArrayList<>();
        l1.add(c2);
        l1.add(c5);

        List<Card> l2 = new ArrayList<>();
        l2.add(c8);
        l2.add(c10);

        Player p1 = new Player("Ryan");
        p1.addIntoPlayerCardPile(l1);

        Player p2 = new Player("Jared");
        p2.addIntoPlayerCardPile(l2);
        
        Player p3 = new Player("Le Shen");
        Player p4 = new Player("Kevin");

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);



        Game g1 = new Game(players, pa, 4);

        g1.calculateWinner();
    }
}
