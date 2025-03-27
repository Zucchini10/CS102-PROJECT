import java.util.*;

public class PointsCalculator2P extends PointsCalculator {
    public PointsCalculator2P(List<Player> players){
        super(players);
    }

    @Override
    public List<Player> getPlayersWithMaxCardColor(String color){
        Player first = players.get(0);
        Player second = players.get(1);

        List<Card> firstPlayerHandColor = first.getStack().getPlayerCardPileStack().get(color).getPlayerCardPile();
        List<Card> secondPlayerHandColor = second.getStack().getPlayerCardPileStack().get(color).getPlayerCardPile();

        int first_count = firstPlayerHandColor.size();
        int second_count = secondPlayerHandColor.size();

        List<Player> result = new ArrayList<>();

        if(first_count - second_count >= 2){
            result.add(first);
        }

        if(first_count - second_count <= -2){
            result.add(second);
        }

        return result;
    }
}
