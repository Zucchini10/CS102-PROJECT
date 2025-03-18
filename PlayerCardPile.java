import java.util.*;

public class PlayerCardPile {
    private List<Card> stack;

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

    // public void sortColour(){
    //     Collections.sort(stack, new ColourComparator());
    // }
    //when checking for game end,
    //check for uniqueness in the stack
    private static void addCardsToStack(List<Card> parade, Card playedCard, List<Card> stack) {
        int playedValue = playedCard.getValue();
        int paradeIndex = parade.size() - playedValue - 2;

        while (paradeIndex >= 0) {
            Card currentCard = parade.get(paradeIndex);
            if (currentCard.getColor() == playedCard.getColor() && currentCard.getValue() <= playedValue) {    //go through the paradeLine and add cards that fit the removal criteria
                stack.add(parade.remove(paradeIndex));
            }
            paradeIndex--;
        }
    }


    
}
