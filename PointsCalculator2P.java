import java.util.*;

public class PointsCalculator2P extends PointsCalculator {
    public PointsCalculator2P(List<Player> players){
        super(players);
    }

    @Override
    public List<Player> getPlayersWithMaxCardColor(String color){
        Player first = players.get(0);
        Player second = players.get(1);

        List<Card> firstPlayerHand = first.getHand();
        List<Card> secondPlayerHand = second.getHand();

        int first_count = 0;
        int second_count = 0;

        List<Player> result = new ArrayList<>();

        for(Card c : firstPlayerHand){
            if(c.getColour().equals(color)){
                first_count++;
            }
        }

        for(Card c: secondPlayerHand){
            if(c.getColour().equals(color)){
                second_count++;
            }
        }

        if(first_count - second_count >= 2){
            result.add(first);
        }

        if(first_count - second_count <= -2){
            result.add(second);
        }

        return result;
    }
}
