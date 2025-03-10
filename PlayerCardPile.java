import java.util.Collections;

public class PlayerCardPile {
    List<Card> stack;

    public List<Card> getStack() {
        return stack;
    }

    public int calcualteStackScore(){
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

    public void sortColour(){
        Collections.sort(stack, new ColourComparator());
    }
    //when checking for game end,
    //check for uniqueness in the stack



    
}