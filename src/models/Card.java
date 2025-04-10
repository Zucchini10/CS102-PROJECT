package models;
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
        
        // Set color based on the card's color with appropriate ANSII code for each color
        String colourCode;
        String colour1;
        if (colour.equalsIgnoreCase("Red")) {
            colourCode = "\033[38;2;255;0;0m"; 
            colour1 = "R";
        } else if (colour.equalsIgnoreCase("Blue")){
            colourCode = "\033[38;2;0;0;255m"; 
            colour1 = "B";
        } else if (colour.equalsIgnoreCase("Green")){
            colourCode = "\033[38;2;0;153;0m"; 
            colour1 = "G";
        }else if (colour.equalsIgnoreCase("Grey")){
            colourCode = "\033[38;2;204;204;204m"; 
            colour1 = "g";
        }else if (colour.equalsIgnoreCase("Purple")){
            colourCode = "\033[35m"; 
            colour1 = "P";
        }else{
            colourCode = "\033[38;2;255;130;0m"; // Orange text color code
            colour1 = "O";
        }
    
        // Add lines to the card with proper formatting and color
        cardLines.add(colourCode + "+-----+"); 
        cardLines.add(colourCode + "|" + colour1 + "    |"); 
        if (value == 10){
            cardLines.add(colourCode + "| " + value + "  |"); 
        } else {
            cardLines.add(colourCode + "|  " + value + "  |"); 
        }
        
        
        cardLines.add(colourCode + "|    " + colour1 + "|");
        cardLines.add(colourCode + "+-----+"); 
    
        return cardLines;
    }

   public void printCard(){
        List<String> cardLines = cardRepresentation();
        for (int i = 0;i<5;i++){
                System.out.println(cardLines.get(i));
            
        }
   }
    
}
