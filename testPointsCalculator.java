import java.util.*;

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
        HashMap <String, List<Player>> majority = pc.majorityDecider();
        
        System.out.println("-----PLAYERS SCORE BEFORE MAJORITY-----");

        for(Player p : listofPlayer){
            System.out.println(p.getName() + p.getStack().getTotalScore());
            System.out.println();
        }

        System.out.println("-----PLAYERS SCORE AFTER MAJORITY-----");

        HashMap <Player, Integer> finalScore = pc.getPlayersScoreAfterMajority();
        for(Map.Entry <Player, Integer> entry : finalScore.entrySet()){
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
            System.out.println();
        }

        System.out.println("-----PLAYERS AND THEIR MAJORITIES-----");


        for(Player p : listofPlayer){
            List<String> colorMajorities = pc.getMostColor(majority, p);
            System.out.println(p.getName());
            for(String color : colorMajorities){
                System.out.println(color);
            }
            System.out.println("------");
        }

        System.out.println("-----LEAST SCORE-----");

        System.out.println(pc.getLeastScore());
        System.out.println();

        System.out.println("-----PLAYER WITH LEAST SCORE-----");

        Player withLeastScore = pc.getPlayerWithLeastScore();
        System.out.println(withLeastScore.getName() + " " + pc.getLeastScore());
        System.out.println();

        System.out.println("-----MAJORITIES REQUIRED CARD AMOUNT-----");

        for(String color : colors){
            System.out.println(color + " " +  pc.getMajorityAmount(color));
        }


    }
}
