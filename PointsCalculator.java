import java.util.*;

public class PointsCalculator {
    private List<Player> players;

    public PointsCalculator(List<Player> players){
        this.players = players;
    }
    
    public List<Player> getPlayersWithMaxCardColor(String color, List<Player> players){
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
    public HashMap <Card, List<Player>> MajorityDecider (List<Player> players){
        HashMap<Card, List<Player>> result = new HashMap<>();
        result.put(new Card("RED", 0, true), getPlayersWithMaxCardColor("RED", players));
        result.put(new Card("BLUE", 0, true), getPlayersWithMaxCardColor("BLUE", players));
        result.put(new Card("GREEN", 0, true), getPlayersWithMaxCardColor("GREEN", players));
        result.put(new Card("YELLOW", 0, true), getPlayersWithMaxCardColor("YELLOW", players));
        result.put(new Card("PURPLE", 0, true), getPlayersWithMaxCardColor("PURPLE", players));
        result.put(new Card("ORANGE", 0, true), getPlayersWithMaxCardColor("ORANGE", players));

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

    public int getMostScore(List<Player> players){
        int mostScore = Integer.MIN_VALUE;

        if(players == null){
            return mostScore;
        }

        HashMap<Card, List<Player>> majority = MajorityDecider(players);
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

    public Player getPlayerWithMostScore(List<Player> players){
        int mostScore = Integer.MIN_VALUE;
        Player mostPlayer = null;

        if(players == null){
            return mostPlayer;
        }

        HashMap<Card, List<Player>> majority = MajorityDecider(players);
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

    public int getLeastScore(List<Player> players){
        int leastScore = Integer.MAX_VALUE;

        if(players == null){
            return leastScore;
        }

        HashMap<Card, List<Player>> majority = MajorityDecider(players);
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

    public Player getPlayerWithLeastScore(List<Player> players){
        int leastScore = Integer.MAX_VALUE;
        Player leastPlayer = null;

        if(players == null){
            return leastPlayer;
        }

        HashMap<Card, List<Player>> majority = MajorityDecider(players);
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
