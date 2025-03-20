import java.util.*;

public class PlayerCardPile {
    private List<PlayerColouredStack> stack;

    public PlayerCardPile() {
        this.stack = new ArrayList<>(); // Initialize the list
    }

    public List<PlayerColouredStack> getStack() {
        return stack;
    } 

    public void addCard(Card card) {
        // Find the colored stack for this card's color
        String cardColor = card.getColour();
        PlayerColouredStack colorStack = null;
        
        // Look for existing stack with the same color
        for (PlayerColouredStack cs : stack) {
            if (cs.getColour().equals(cardColor)) {
                colorStack = cs;
                break;
            }
        }
        
        // If no stack exists for this color, create one
        if (colorStack == null) {
            colorStack = new PlayerColouredStack(cardColor);
            stack.add(colorStack);
        }
        
        // Add the card to the appropriate color stack
        colorStack.addCard(card);
    }

    public void printPlayerCardPile(){
        if(stack.isEmpty()){
            System.out.println("No cards collected!");
        }
        for(PlayerColouredStack s : stack){
            for(Card c : s){
                c.printCard();
            }
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
        if(stack.size() == 6){
            return true;
        }
        return false;
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

