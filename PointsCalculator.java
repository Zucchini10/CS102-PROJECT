import java.util.*;

public class PointsCalculator {
    protected List<Player> players;

    public PointsCalculator(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayersWithMaxCardColor(String color) {
        int maxCount = 0;
        List<Player> max = new ArrayList<>();

        // Go through each player's card pile based on color and get the max count, while also updating the arraylist result
        for (Player p : players) { 
            HashMap<String, PlayerCardPile> currentCardPileStack = p.getStack().getPlayerCardPileStack();
            List<Card> currentColorPile = currentCardPileStack.get(color).getPlayerCardPile();
            int currentSize = currentColorPile.size();
            if (currentSize  > maxCount) {
                max.clear(); // If there is a new max count, we clear the arraylist result
                maxCount = currentColorPile.size();
                max.add(p); // Add the player with the new max count to the arraylist
            } else if (currentSize == maxCount) {
                max.add(p); // If it is the same as max count, then we add to the arraylist
            }
        }

        return max;
    }

    // "RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE"
    public HashMap<String, List<Player>> majorityDecider() {
        // Get the list of majorities holder for each color
        HashMap<String, List<Player>> result = new HashMap<>();
        // Get the players with max card color for each color and put them in hashmap
        result.put("RED", getPlayersWithMaxCardColor("RED")); 
        result.put("BLUE", getPlayersWithMaxCardColor("BLUE"));
        result.put("GREEN", getPlayersWithMaxCardColor("GREEN"));
        result.put("GREY", getPlayersWithMaxCardColor("GREY"));
        result.put("PURPLE", getPlayersWithMaxCardColor("PURPLE"));
        result.put("ORANGE", getPlayersWithMaxCardColor("ORANGE"));

        return result;
    }

    public void flipMajorityCardPile(List<Player> majorityPlayers, String colour) {
        // For each player in the majority players, we set their specific colour card pile to face up
        for (Player player : majorityPlayers) {
            PlayerCardPileStack pcps = player.getStack();
            HashMap<String, PlayerCardPile> hm = pcps.getPlayerCardPileStack();
            PlayerCardPile pc = hm.get(colour);
            pc.setFaceUp(false);
        }
    }

    public HashMap<Player, Integer> getPlayersScore() {
        HashMap<Player, Integer> result = new HashMap<>();

        if (players == null) {
            return result;
        }

        for (Player p : players) {
            // Go through each player and get their score
            // Get the player's score and put it in the hashmap
            int currentScore = p.getStack().getTotalScore();
            result.put(p, currentScore);
        }

        return result;
    }

    public Player getWinner() {
        if (players == null) {
            return null;
        }

        // Get the player with the least score
        int leastScore = Collections.min(getPlayersScore().values());
        List<Player> playersWithLeastScore = new ArrayList<>();
        HashMap<Player, Integer> playersScore = getPlayersScore();

        for (Map.Entry<Player, Integer> entry : playersScore.entrySet()) {
            if (entry.getValue() == leastScore) {
                // Put player in the arraylist if they have the least score
                playersWithLeastScore.add(entry.getKey());
            }
        }

        if (playersWithLeastScore.size() == 1) {
            // If arraylist only contain one player then that is the winner
            return playersWithLeastScore.get(0);
        }

        // If there are multiple players with the same least score, we need to check their stack amount
        int leastCardAmount = playersWithLeastScore.get(0).getStack().getTotalCards();
        Player playerWithLeastCardAmount = playersWithLeastScore.get(0);
        // Get the player with least stack amount
        for (Player p : playersWithLeastScore) {
            int currentCardAmount = p.getStack().getTotalCards();
            if (currentCardAmount < leastCardAmount) {
                leastCardAmount = currentCardAmount;
                playerWithLeastCardAmount = p;
            }
        }

        return playerWithLeastCardAmount;
    }

    public int getMajorityAmount(String color){
        // Majority amount is the amount of cards needed to be majority holder
        // If there is no majority holder, return -1
        HashMap <String, List<Player>> majority = majorityDecider();
        List<Player> getMajorities = majority.get(color);
        if(getMajorities.size() != 0){
            return getMajorities.get(0).getStack().getPlayerCardPileStack().get(color).getPlayerCardPile().size();
        }

        return -1;
    }
}
