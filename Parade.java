import java.util.*;

public class Parade {
    private List<Card> paradeLine;

    public Parade(){
        paradeLine = new ArrayList<Card>();

    }
    
    public void printParade() {
        String line;
        System.out.println("PARADE : ");
        for (int i = 0; i < 5; i++) {
            line = "";
            for (int j = 0; j < paradeLine.size(); j++) {
                line += (paradeLine.get(j).cardRepresentation()).get(i);
                line += " ";
            }
            System.out.println(line);

        }
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
    
    public void evaluateParade(Player player){
        
    }
}
