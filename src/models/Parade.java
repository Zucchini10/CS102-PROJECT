package models;
import java.util.*;

import utils.cardPrinter;

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

        // iterate through the parade and for every card in removal mode that is <= chosen cards value or same colour, will be added into list of cards that will be returned
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

    public List<Card> simulateRemovedFromParade(Card chosen) {
        int playedValue = chosen.getValue();
        int paradeIndex = paradeLine.size() - playedValue - 1;
    
        List<Card> simulatedDrawn = new ArrayList<>();
    
        while (paradeIndex >= 0) {
            Card currentCard = paradeLine.get(paradeIndex);
            if (currentCard.getColour().equals(chosen.getColour()) || currentCard.getValue() <= playedValue) {
                simulatedDrawn.add(currentCard);  // Simulate without removing
            }
            paradeIndex--;
        }
        return simulatedDrawn;
    }
    
    
    public List<Card> getParadeLine(){
        return paradeLine;
    }
}
