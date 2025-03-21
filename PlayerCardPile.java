import java.util.*;

public class PlayerCardPile {
    private List<Card> playerCardPile;
    private String colour;
    private boolean faceUp;

    public PlayerCardPile(String colour){
        this.playerCardPile = new ArrayList<>();
        this.colour = colour;
        this.faceUp = true;
    }

    // getters and setters
    public List<Card> getPlayerCardPile() {
        return playerCardPile;
    }

    public void setPlayerCardPile(List<Card> playerCardPile) {
        this.playerCardPile = playerCardPile;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    } 

    public void addCard(Card c){
        playerCardPile.add(c);
    }

    public boolean isEmpty(){
        
        if (playerCardPile.size()==0){
            return true;
        } else{
            return false;
        }
    }

    public int calculateScore(){

        // return total value of all cards in playercardpile
        if (faceUp == true){
            int sum = 0;
        for (Card c: playerCardPile){
            sum+=c.getValue();
        }

        return sum;
        } else {
            // if majority, the cards in playercardpile all count as 1
            return playerCardPile.size();
        }
        
    }

    public void printPlayerCardPile(){

        // print out the cards in pcp
        new cardPrinter(playerCardPile);
    }
}