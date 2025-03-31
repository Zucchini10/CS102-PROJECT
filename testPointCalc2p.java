import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class testPointCalc2p {
     public static void main(String args[]){
        Random random = new Random();
        List<Player> listofPlayer = new ArrayList<>();
        String[] names = {"Jerry", "Bob"};
        for(int i = 0; i < 2; i++){
            listofPlayer.add(new Player(names[i]));
        }

        String[] colors = {"RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE"};


        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 10; j++){
                String randomColor = colors[random.nextInt(colors.length)];
                int randomValue = random.nextInt(11);
                listofPlayer.get(i).getStack().addCard(new Card(randomColor, randomValue));
            }
        }

        System.out.println("-----PLAYERS STACK PILE-----");

        for(int i = 1; i <= 2; i++){
            System.out.println("Player : " + listofPlayer.get(i-1).getName());
            listofPlayer.get(i-1).getStack().printPlayerCardPileStack();
            System.out.println();
        }

        PointsCalculator pc = new PointsCalculator2P(listofPlayer);
        pc.majorityDecider();
        
        System.out.println("-----PLAYERS SCORE-----");

        for(Player p : listofPlayer){
            System.out.println(p.getName() + p.getStack().getTotalScore());
            System.out.println();
        }

        System.out.println("-----PLAYER WHO WINS-----");

        Player winner = pc.getWinner();
        System.out.println(winner.getName() + " " + winner.getStack().getTotalScore());
        System.out.println();

    }
}
