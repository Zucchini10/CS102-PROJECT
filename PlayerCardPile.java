import java.util.*;

public class PlayerCardPile {
    private HashMap<String, List<Card>> playerCardPile;
`   

    public PlayerCardPile() {
        HashMap<String, List<Card>> playerCardPile = new HashMap<>();
        playerCardPile.put("RED", new ArrayList<>());
        playerCardPile.put("BLUE", new ArrayList<>());
        playerCardPile.put("GREEN", new ArrayList<>());
        playerCardPile.put("GREY", new ArrayList<>());
        playerCardPile.put("PURPLE", new ArrayList<>());
        playerCardPile.put("ORANGE ", new ArrayList<>());
    }

    public List<PlayerColouredStack> getStack() {
        return stack;
    } 

    public void addCard(Card c){
        if(playerCardPile.containsKey(c.getColour())){
            playerCardPile.get(c.getColour()).add(c);
        }
        return;
    }   


    public void printPlayerCardPile(){
        for(Map.Entry<String, List<Card>> entry : playerCardPile.entrySet()){
            for(Card c : )
        }
    }
    
    public String toString(){
        String toReturn = "";
        for(PlayerColouredStack temp : stack){
            toReturn += temp.toString() + "%n";
        }
        return toReturn;
    }

    public boolean containsAllColours(){
        boolean allColours = true;
        for(Map.Entry<String, List<Card>> entry : playerCardPile.entrySet()){
            String key = entry.getKey();
            List<Card> value = entry.getValue();

            if(value.isEmpty()){
                allColours = false;
            }
        }
        return allColours;
    }
    }

    // public String toString() {
    //     StringBuilder toReturn = new StringBuilder();
    //     for (PlayerColouredStack colorStack : stack) {
    //         toReturn.append(colorStack.toString()).append("\n");
    //     }
    //     return toReturn.toString();
    // }

      //     while (paradeIndex >= 0) {
    //         Card currentCard = parade.get(paradeIndex);
    //         if (currentCard.getColor() == playedCard.getColor() && currentCard.getValue() <= playedValue) {    //go through the paradeLine and add cards that fit the removal criteria
    //             stack.add(parade.remove(paradeIndex));
    //         }
    //         paradeIndex--;
    //     }

    // public void addCard(Card card) {
    //     // Find the colored stack for this card's color
    //     String cardColor = card.getColour();
    //     PlayerColouredStack colorStack = null;
        
    //     // Look for existing stack with the same color
    //     for (PlayerColouredStack cs : stack) {
    //         if (cs.getColour().equals(cardColor)) {
    //             colorStack = cs;
    //             break;
    //         }
    //     }
        
    //     // If no stack exists for this color, create one
    //     if (colorStack == null) {
    //         colorStack = new PlayerColouredStack(cardColor);
    //         stack.add(colorStack);
    //     }
        
    //     // Add the card to the appropriate color stack
    //     colorStack.addCard(card);
    // }