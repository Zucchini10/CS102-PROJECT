import java.util.*;

import src.game.PointsCalculator;
import src.models.Card;
import src.player.Player;

public class testPointsCalculator {
    public static void main(String args[]){
        Random random = new Random();
        List<Player> listofPlayer = new ArrayList<>();
        String[] names = {"Jerry", "Bob", "Alice", "Maria", "Jacob"};
        for(int i = 0; i < 5; i++){
            listofPlayer.add(new Player(names[i]));
        }

        String[] colors = {"RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE"};


        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){
                String randomColor = colors[random.nextInt(colors.length)];
                int randomValue = random.nextInt(11);
                listofPlayer.get(i).getStack().addCard(new Card(randomColor, randomValue));
            }
        }

        System.out.println("-----PLAYERS STACK PILE-----");

        for(int i = 1; i <= 5; i++){
            System.out.println("Player : " + listofPlayer.get(i-1).getName());
            listofPlayer.get(i-1).getStack().printPlayerCardPileStack();
            System.out.println();
        }

        PointsCalculator pc = new PointsCalculator(listofPlayer);
        pc.flipMajorityCardPiles();


        System.out.println("-----PLAYERS SCORE-----");

        for(Player p : listofPlayer){
            System.out.println(p.getName() + p.getStack().getTotalScore());
            System.out.println();
        }

        System.out.println("-----PLAYER WHO WINS-----");

        Player winner = pc.getWinner();
        System.out.println(winner.getName() + " " + winner.getScore());
        System.out.println();

    }
}
