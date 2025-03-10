import java.util.*;

public class PlayerCardPile {
    List<Card> stack;

    public List<Card> getStack() {
        return stack;
    }

    public int calculateStackScore(){
        int sum = 0;
        for(Card c : stack){
            sum += c.getValue();
        }
        return sum;
    } 

    public String toString(){
        String s = "";
        for(Card c : stack){
            s += c.toString() + " ";
        }
        return s.substring(0, s.length()-1);
    }

    

    
}