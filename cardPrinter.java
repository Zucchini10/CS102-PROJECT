import java.util.List;

public class cardPrinter {
    private List<Card> cards;

    public cardPrinter(List<Card> cards){
        String line;
        System.out.println("CARDS : ");
        for (int i = 0; i < 5; i++) {
            line = "";
            for (int j = 0; j < cards.size(); j++) {
                line += (cards.get(j).cardRepresentation()).get(i);
                line += " ";
            }
            System.out.println(line);

        }
    }


}
