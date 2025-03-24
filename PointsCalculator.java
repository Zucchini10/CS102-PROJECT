import java.util.*;

public class PointsCalculator {
    protected List<Player> players;

    public PointsCalculator(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayersWithMaxCardColor(String color) {
        int maxCount = 0;
        List<Player> max = new ArrayList<>();

        for (Player p : players) {
            List<Card> currentHand = p.getStack().getPlayerCardPileWithColor(color);
            int currentCount = 0;
            for (Card c : currentHand) {
                if (c.getColour().equals(color)) {
                    currentCount++;
                }
            }
            if (currentCount > maxCount) {
                max.clear();
                maxCount = currentCount;
                max.add(p);
            } else if (currentCount == maxCount) {
                max.add(p);
            }
        }

        return max;
    }

    // "RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE"
    public HashMap<String, List<Player>> majorityDecider() {
        HashMap<String, List<Player>> result = new HashMap<>();
        result.put("RED", getPlayersWithMaxCardColor("RED"));
        result.put("BLUE", getPlayersWithMaxCardColor("BLUE"));
        result.put("GREEN", getPlayersWithMaxCardColor("GREEN"));
        result.put("GREY", getPlayersWithMaxCardColor("GREY"));
        result.put("PURPLE", getPlayersWithMaxCardColor("PURPLE"));
        result.put("ORANGE", getPlayersWithMaxCardColor("ORANGE"));

        return result;
    }

    public List<String> getMostColor(HashMap<String, List<Player>> majority, Player player) {
        List<String> mostColor = new ArrayList<>();
        for (Map.Entry<String, List<Player>> entry : majority.entrySet()) {
            String currentCard = entry.getKey();
            List<Player> currentPlayerList = entry.getValue();

            if (currentPlayerList.contains(player)) {
                mostColor.add(currentCard);
            }
        }

        return mostColor;
    }

    public HashMap<Player, Integer> getPlayersScoreAfterMajority() {
        HashMap<Player, Integer> result = new HashMap<>();

        if (players == null) {
            return result;
        }

        HashMap<String, List<Player>> majority = majorityDecider();
        for (Player p : players) {
            int currentScore = p.getStack().getTotalScore();
            List<String> mostColor = getMostColor(majority, p);
            for(String color : mostColor){
                List<Card> stackColor = p.getStack().getPlayerCardPileWithColor(color);
                for(Card c : stackColor){
                    currentScore -= c.getValue();
                    currentScore++;
                }
            }
            result.put(p, currentScore);
        }

        return result;
    }

    public int getLeastScore() {
        HashMap<Player, Integer> playersScoreAfterMajority = getPlayersScoreAfterMajority();
        return Collections.min(playersScoreAfterMajority.values());
    }

    public Player getPlayerWithLeastScore() {
        int leastScore = getLeastScore();
        List<Player> playersWithLeastScore = new ArrayList<>();
        HashMap<Player, Integer> playersScoreAfterMajority = getPlayersScoreAfterMajority();

        if (players == null) {
            return null;
        }

        for (Map.Entry<Player, Integer> entry : playersScoreAfterMajority.entrySet()) {
            if (entry.getValue() == leastScore) {
                playersWithLeastScore.add(entry.getKey());
            }
        }

        if (playersWithLeastScore.size() == 1) {
            return playersWithLeastScore.get(0);
        }

        int leastCardAmount = playersWithLeastScore.get(0).getStack().getTotalAmount();
        Player playerWithLeastCardAmount = playersWithLeastScore.get(0);
        for (Player p : playersWithLeastScore) {
            int currentCardAmount = p.getStack().getTotalAmount();
            if (currentCardAmount < leastCardAmount) {
                leastCardAmount = currentCardAmount;
                playerWithLeastCardAmount = p;
            }
        }

        return playerWithLeastCardAmount;
    }

    public int getMajorityAmount(String color){
        HashMap <String, List<Player>> majority = majorityDecider();
        List<Player> getMajorities = majority.get(color);
        if(getMajorities.size() != 0){
            
            return getMajorities.get(0).getStack().getPlayerCardPileWithColor(color).size();
        }

        return -1;
    }
}
