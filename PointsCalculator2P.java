import java.util.*;

public class PointsCalculator2P extends PointsCalculator {
    public PointsCalculator2P(List<Player> players){
        super(players);
    }

    @Override
    public List<Player> getPlayersWithMaxCardColor(String color){
        Player first = players.get(0);
        Player second = players.get(1);

        HashMap<String, PlayerCardPile> firstCardPileStack = first.getStack().getPlayerCardPileStack();
        HashMap<String, PlayerCardPile> secondCardPileStack = second.getStack().getPlayerCardPileStack();
        // Get the specific color card pile for the first player
        List<Card> firstPlayerColorPile = firstCardPileStack.get(color).getPlayerCardPile();
        // Get the specific color card pile for the second player
        List<Card> secondPlayerColorPile = secondCardPileStack.get(color).getPlayerCardPile();

        int first_count = firstPlayerColorPile.size();
        int second_count = secondPlayerColorPile.size();

        List<Player> result = new ArrayList<>();

        // Check if the first player has 2 or more card amount than the second player for a specific color card pile
        if(first_count - second_count >= 2){
            result.add(first);
        }

        // Check if the second player has 2 or more card amount than the second player for a specific color card pile
        if(first_count - second_count <= -2){
            result.add(second);
        }

        return result;
    }
}
