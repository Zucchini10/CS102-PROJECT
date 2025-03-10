import java.util.*;


public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        String[] colors = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE"};
            for (String color : colors){
                for (int i = 0;i<11;i++){
                    Card newcard = new Card(color,i);
                    Deck.add(newcard);
                }
            }
            shuffle();
        }

    
    public void shuffle() {
        Collections.shuffle(cards);

    }


    public List<Card> getCards() {
        return cards;
    }

    public boolean isEmpty(){
        if (cards.size()==0){
            return true;
        }
        return false;
    }

    public int getNumberOfRemainingCards(){ 
        return cards.size();;
    }
    public void drawcard(){
        cards.remove(0);
    }

    public boolean alwaystrue(){
        return true;
    }
}

