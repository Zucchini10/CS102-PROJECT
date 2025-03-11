import java.util.*;

public class Parade {
    private List<Player> players;
    private List<Card> paradeLine;
    private int currentTurn;
    private PointsCalculator pc;

    public Parade(List<Player> players, List<Card> paradeLine){
        this.players = players;
        this.paradeLine = paradeLine;
        currentTurn = 0;

        if(players.size() > 2){
            pc = new PointsCalculator(players);
        } else{
            pc = new PointsCalculator2P(players);
        }
    }

    public void startGame(){
        
    }

    public void endGame(){

    }

    public void nextTurn(){

    }

    public Player checkWinner(){
        
    }

    public void addCardToParade(Card card){

    }

    public void removeCard(Card card){

    }
    
    public void evaluateParade(Player player){
        
    }
}
