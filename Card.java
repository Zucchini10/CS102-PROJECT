public class Card {
    private String colour;
    private int value;
    // private boolean faceUp;

    public Card(String colour, int value, boolean faceUp){
        this.colour = colour;
        this.value = value;
        // this.faceUp = faceUp;
    }

    public String getColour() {
        return colour;
    }
    public int getValue() {
        return value;
    }

    // public boolean isFaceUp() {
    //     return faceUp;
    // }

    public String toString(){
        return colour + "-" + value;
    }

   

    
}
