import java.util.*;

public class PlayerCardPileStack {
    private HashMap<String, PlayerCardPile> playerCardPileStack;
    String colourResetCode = "\033[0m\033[1m";

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

        // will print out the list of cards.
        // if empty, will print out empty. If majority after endgame , will print out it is majority
        for (String colour:colours){
            PlayerCardPile pcp = playerCardPileStack.get(colour);
            if (pcp.getPlayerCardPile().size()==0){
                System.out.print( colourResetCode + colour + " CARD PILE :");
                System.out.println("Is empty ");
                System.out.println();
            } else if (pcp.isFaceUp()==true){
                System.out.println( colourResetCode + colour + " CARD PILE :");
                pcp.printPlayerCardPile();
            } else {
                System.out.println( colourResetCode + colour + " CARD PILE : (Majority)");
                pcp.printPlayerCardPile();
            }
            
        }
        
    }


    public boolean containsAllColours(){
        boolean playerHasAllColors = true;

        // for every playercardpile in stack, if any of them are empty, return false
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

        // for every playercardpile in the stack, get their individual score and sum it up
        Set<Map.Entry<String, PlayerCardPile>> entrySet = playerCardPileStack.entrySet();
        for (Map.Entry<String, PlayerCardPile> entry : entrySet) {
            PlayerCardPile pile = entry.getValue();
            total += pile.calculateScore();
        }


        return total;
    }

    public int getTotalCards(){

        // get number of cards in stack 
        int result = 0;
        for(PlayerCardPile current : playerCardPileStack.values()){
            result += current.getPlayerCardPile().size();
        }

        return result;
    }


    public HashMap<String, PlayerCardPile> getPlayerCardPileStack() {
        return playerCardPileStack;
    }


    public void setPlayerCardPileStack(HashMap<String, PlayerCardPile> playerCardPileStack) {
        this.playerCardPileStack = playerCardPileStack;
    }
    
}


