import java.util.*;

public class Parade {
    private List<Card> paradeLine;

    public Parade(){
        paradeLine = new ArrayList<Card>();

    }
    
    public void printParade() {
        System.out.println("\033[0m\033[1mPARADE : ");
        new cardPrinter(paradeLine);

    }

    public void addCardToParade(Card card){
        paradeLine.add(card);
    }

    public List<Card> removedFromParade(Card chosen) {
        int playedValue = chosen.getValue();
        int paradeIndex = paradeLine.size() - playedValue - 1;

        List<Card> paradeDrawn = new ArrayList<Card>();
        while (paradeIndex >= 0) {
            Card currentCard = paradeLine.get(paradeIndex);
            if (currentCard.getColour() == chosen.getColour() || currentCard.getValue() <= playedValue) {
                paradeLine.remove(paradeIndex);
                paradeDrawn.add(currentCard);
            }
            paradeIndex--;
        }
        paradeLine.add(chosen);
        return paradeDrawn;

    }
    
    public List<Card> getParadeLine(){
        return paradeLine;
    }
}
