import java.util.*;

public class PlayerColouredStack {
    private List<Card> colouredStack;
    private String colour;

    public PlayerColouredStack(String colour){
        this.colouredStack = new ArrayList<>();
        this.colour = colour;
    } 

    // public int calculateScore(){
    //     int sum = 0 ;
    //     for(Card c: colouredStack){
    //         sum += c.getValue();
    //     }
    //     return sum;
    // }

    public List<Card> getColouredStack() {
        return colouredStack;
    }

    public String getColour() {
        return colour;
    }

    public void flipColouredStack(){
        for(Card c : colouredStack){
            c.isFaceUp = false;
        }
    }

    public String toString(){
        String toReturn = "";
        for(Card c : colouredStack){
            toReturn += c.toString() + " ";
        }
        return toReturn;
    }


}
  // public void printColouredStack(){
    //     for(Card c : colouredStack){
    //         c.printCard();
    //     }
    // }