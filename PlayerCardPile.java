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

    public String toString() {
        String toReturn = "";
        for (PlayerColouredStack temp : stack) {
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

    public void printPlayerCardPile(){
        // List of possible colours
        String[] colours = {"RED","BLUE","GREEN","GREY","PURPLE","ORANGE"};

        // For every colour, print out the player card pile
        for (int i = 0;i<6;i++){
            String colour = colours[i];
            System.out.println(colour + " : ");
            String line = "";
            // get the card pile for that specific colour
            List<Card> pcp = playerCardPile.get(colour);
            for (int j = 0; j < 5; j++) {
                line = "";
                for (int k = 0; k < pcp.size(); k++) {
                    line += (pcp.get(j).cardRepresentation()).get(i);
                    line += " ";
                }
                System.out.println(line);
    
            }
        }
    }
}

// public String toString() {
// StringBuilder toReturn = new StringBuilder();
// for (PlayerColouredStack colorStack : stack) {
// toReturn.append(colorStack.toString()).append("\n");
// }
// return toReturn.toString();
// }

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
// while (paradeIndex >= 0) {
// Card currentCard = parade.get(paradeIndex);
// if (currentCard.getColor() == playedCard.getColor() && currentCard.getValue()
// <= playedValue) { //go through the paradeLine and add cards that fit the
// removal criteria
// stack.add(parade.remove(paradeIndex));
// }
// paradeIndex--;
// }
