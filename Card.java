import java.util.ArrayList;
import java.util.List;

public class Card {
    private String colour;
    private int value;

    public Card(String colour, int value){
        this.colour = colour;
        this.value = value;
    }

    public String getColour() {
        return colour;
    }
    public int getValue() {
        return value;
    }

    public List<String> cardRepresentation(){
        // Create a list to hold the card's lines
        List<String> cardLines = new ArrayList<>();
        
        // Set color based on the card's color
        String colourCode;
        String colour1;
        if (colour.equalsIgnoreCase("Red")) {
            colourCode = "\033[91m"; // Red text color code
            colour1 = "R";
        } else if (colour.equalsIgnoreCase("Blue")){
            colourCode = "\033[94m"; // Black text color code
            colour1 = "B";
        } else if (colour.equalsIgnoreCase("Green")){
            colourCode = "\033[92m"; // Black text color code
            colour1 = "G";
        }else if (colour.equalsIgnoreCase("Grey")){
            colourCode = "\033[37m"; // Black text color code
            colour1 = "g";
        }else if (colour.equalsIgnoreCase("Purple")){
            colourCode = "\033[35m"; // Black text color code
            colour1 = "P";
        }else{
            colourCode = "\033[38;2;255;130;0m"; // Black text color code
            colour1 = "O";
        }
    
        // Add lines to the card with proper formatting and color
        cardLines.add(colourCode + "+----+");  // Top border
        if (value == 10){
            cardLines.add(colourCode + "|" + value + colour1 +" |");  // Card value
        } else {
            cardLines.add(colourCode + "|" + value + colour1 + "  |");  // Card value
        }
        
        cardLines.add(colourCode + "|    |");  // Empty line for spacing
        cardLines.add(colourCode + "+----+");  // Bottom border
    
        return cardLines;
    }

   

    
}
