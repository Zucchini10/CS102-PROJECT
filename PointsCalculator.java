import java.util.*;

public class PointsCalculator {
    protected List<Player> players;

    public PointsCalculator(List<Player> players){
        this.players = players;
    }
    
    public List<Player> getPlayersWithMaxCardColor(String color){
        int maxCount = 0;
        List<Player> max = new ArrayList<>();

        for(Player p : players){
            List<Card> currentHand = p.getHand();
            int currentCount = 0;
            for(Card c : currentHand){
                if(c.getColour().equals(color)){
                    currentCount++;
                }
            }
            if(currentCount > maxCount){
                maxCount = currentCount;
                max = new ArrayList<>();
                max.add(p);
            } else if(currentCount == maxCount){
                max.add(p);
            }
        }

        return max;
    }

    //"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE"
    public HashMap <Card, List<Player>> majorityDecider (){
        HashMap<Card, List<Player>> result = new HashMap<>();
        result.put(new Card("RED", 0, true), getPlayersWithMaxCardColor("RED"));
        result.put(new Card("BLUE", 0, true), getPlayersWithMaxCardColor("BLUE"));
        result.put(new Card("GREEN", 0, true), getPlayersWithMaxCardColor("GREEN"));
        result.put(new Card("YELLOW", 0, true), getPlayersWithMaxCardColor("YELLOW"));
        result.put(new Card("PURPLE", 0, true), getPlayersWithMaxCardColor("PURPLE"));
        result.put(new Card("ORANGE", 0, true), getPlayersWithMaxCardColor("ORANGE"));

        return result;
    }

    public List<String> getMostColor(HashMap<Card, List<Player>> majority, Player player){       
        List<String> mostColor = new ArrayList<>();      
        for(Map.Entry<Card, List<Player>> entry : majority.entrySet()){
            Card currentCard = entry.getKey();
            List<Player> currentPlayerList = entry.getValue();

            if(currentPlayerList.contains(player)){
                mostColor.add(currentCard.getColour());
            }
        }

        return mostColor;
    }

    public int getMostScore(){
        int mostScore = Integer.MIN_VALUE;

        if(players == null){
            return mostScore;
        }

        HashMap<Card, List<Player>> majority = majorityDecider();
        for(Player p : players){
            int tempScore = p.getScore();
            List<String> mostColor = getMostColor(majority, p);
            List<Card> currentHand = p.getHand();
            for(Card c : currentHand){
                if(mostColor.contains(c.getColour())){
                    tempScore -= c.getValue();
                    tempScore++;
                }
            }
            if(tempScore > mostScore){
                mostScore = tempScore;
            }
        }
        
        return mostScore;
    }

    public Player getPlayerWithMostScore(){
        int mostScore = Integer.MIN_VALUE;
        Player mostPlayer = null;

        if(players == null){
            return mostPlayer;
        }

        HashMap<Card, List<Player>> majority = majorityDecider();
        for(Player p : players){
            int tempScore = p.getScore();
            List<String> mostColor = getMostColor(majority, p);
            List<Card> currentHand = p.getHand();
            for(Card c : currentHand){
                if(mostColor.contains(c.getColour())){
                    tempScore -= c.getValue();
                    tempScore++;
                }
            }
            if(tempScore > mostScore){
                mostScore = tempScore;
                mostPlayer = p;
            }
        }
        
        return mostPlayer;
    }

    public int getLeastScore(){
        int leastScore = Integer.MAX_VALUE;

        if(players == null){
            return leastScore;
        }

        HashMap<Card, List<Player>> majority = majorityDecider();
        for(Player p : players){
            int tempScore = p.getScore();
            List<String> mostColor = getMostColor(majority, p);
            List<Card> currentHand = p.getHand();
            for(Card c : currentHand){
                if(mostColor.contains(c.getColour())){
                    tempScore -= c.getValue();
                    tempScore++;
                }
            }
            if(tempScore < leastScore){
                leastScore = tempScore;
            }
        }
        
        return leastScore;
    }

    public Player getPlayerWithLeastScore(){
        int leastScore = Integer.MAX_VALUE;
        Player leastPlayer = null;

        if(players == null){
            return leastPlayer;
        }

        HashMap<Card, List<Player>> majority = majorityDecider();
        for(Player p : players){
            int tempScore = p.getScore();
            List<String> mostColor = getMostColor(majority, p);
            List<Card> currentHand = p.getHand();
            for(Card c : currentHand){
                if(mostColor.contains(c.getColour())){
                    tempScore -= c.getValue();
                    tempScore++;
                }
            }
            if(tempScore < leastScore){
                leastScore = tempScore;
                leastPlayer = p;
            }
        }
        
        return leastPlayer;
    }
}
