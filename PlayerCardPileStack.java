import java.util.*;

public class PlayerCardPileStack {
    private HashMap<String, PlayerCardPile> playerCardPileStack;


    public PlayerCardPileStack() {

        // add in 6 playercardpiles of each colour
        this.playerCardPileStack = new HashMap<>();
        playerCardPileStack.put("RED", new PlayerCardPile("RED"));
        playerCardPileStack.put("BLUE", new PlayerCardPile("BLUE"));
        playerCardPileStack.put("GREEN", new PlayerCardPile("GREEN"));
        playerCardPileStack.put("GREY", new PlayerCardPile("GREY"));
        playerCardPileStack.put("PURPLE", new PlayerCardPile("PURPLE"));
        playerCardPileStack.put("ORANGE", new PlayerCardPile("ORANGE"));
    }


    public void addCard(Card c){
        // get colour of card
        String colour = c.getColour();

        // get the playercardpile of that colour
        PlayerCardPile pcp = playerCardPileStack.get(colour);
        
        // add card
        pcp.addCard(c);
        
    }   


    public void printPlayerCardPileStack(){
        String[] colours = {"RED","BLUE","GREEN","GREY","PURPLE","ORANGE"};

        for (String colour:colours){
            PlayerCardPile pcp = playerCardPileStack.get(colour);
            if (pcp.getPlayerCardPile().size()==0){
                System.out.print( "\033[0m\033[1m" + colour + " CARD PILE :");
                System.out.println("Is empty ");
                System.out.println();
            } else if (pcp.isFaceUp()==true){
                System.out.println( "\033[0m\033[1m" + colour + " CARD PILE :");
                pcp.printPlayerCardPile();
            } else {
                System.out.println( "\033[0m\033[1m" + colour + " CARD PILE : (Majority)");
                pcp.printPlayerCardPile();
            }
            
        }
        
    }


    public boolean containsAllColours(){
        boolean playerHasAllColors = true;
        Set<Map.Entry<String, PlayerCardPile>> entrySet = playerCardPileStack.entrySet();
        for (Map.Entry<String, PlayerCardPile> entry : entrySet) {
            PlayerCardPile pile = entry.getValue();
            if (pile.isEmpty()){
                playerHasAllColors = false;
            }
        }

        return playerHasAllColors;
    }

    public int getTotalScore(){

        int total = 0;
        Set<Map.Entry<String, PlayerCardPile>> entrySet = playerCardPileStack.entrySet();
        for (Map.Entry<String, PlayerCardPile> entry : entrySet) {
            PlayerCardPile pile = entry.getValue();
            total += pile.calculateScore();
        }


        return total;
    }


    public HashMap<String, PlayerCardPile> getPlayerCardPileStack() {
        return playerCardPileStack;
    }


    public void setPlayerCardPileStack(HashMap<String, PlayerCardPile> playerCardPileStack) {
        this.playerCardPileStack = playerCardPileStack;
    }
    
}


