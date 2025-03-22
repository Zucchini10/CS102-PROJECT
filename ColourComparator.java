public class ColourComparator implements Comparator<Card> {
    
    public int compare(Card c1, Card c2) {
        return c1.getColour().compareTo(c2.getColour());
    }
}
