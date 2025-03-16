import java.util.*;

public class Parade {
    private List<Player> players;
    private List<Card> paradeLine;
    private int currentTurn;
    private PointsCalculator pc;

    public Parade(List<Player> players, List<Card> paradeLine){
        this.players = players;
        this.paradeLine = paradeLine;
        currentTurn = 0;

        if(players.size() > 2){
            pc = new PointsCalculator(players);
        } else{
            pc = new PointsCalculator2P(players);
        }
    }

    public void startGame(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players (2 or more): ");
        int numPlayers = scanner.nextInt();
        
        if(numPlayers < 2) {
            System.out.println("Invalid number of players.");
            return;
        }
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter player " + (i + 1) + " name (or 'computer'): ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName, playerName.equalsIgnoreCase("computer")));
        }

        List<Card> deck = initialiseDeck();
        Collections.shuffle(deck);

        List<Card> parade = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            parade.add(deck.drawcard(i));
        }

        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                player.getHand().add(deck.drawcard(i));
            }
        }
        int currentPlayerIndex = 0;
        while(true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() +"'s turn:");
            displayParade(parade);
            displayHand(currentPlayer);

            if(currentPlayer.isAI()) {
                computerTurn(currentPlayer, parade, deck);
            } else {
                playerTurn(currentPlayer, parade, deck, scanner);
            }
            if (deck.isEmpty() && allHandsEmpty(players)) {
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        }
        endGame();
    }
    
    private static void displayParade(List<Card> parade) {
        System.out.println("\nParade:");
        for (int i = 0; i < parade.size(); i++) {
            System.out.print(parade.get(i) + " ");
        }
        System.out.println();
    }

    private static void displayHand(List<Card> hand) {
        System.out.println("Your Hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }
    
    public void endGame(){
        calculateFinalScores();
    }

    public void playerTurn(Player player, List<Card> parade, List<Card> deck, Scanner scanner) {
        if (player.getHand().isEmpty()) {
            System.out.println("Your hand is empty. Skipping turn.");
            return;
        }
        int cardIndex;
        while (true) {
            System.out.print("Enter the index of the card to play (0-" + (player.getHand().size() - 1) + "): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            cardIndex = scanner.nextInt();
            if (cardIndex >= 0 && cardIndex < player.getHand().size()) {
                break;
            } else {
                System.out.println("Invalid card index.");
            }
        }

        Card playedCard = player.getHand().remove(cardIndex);
        parade.add(playedCard);
        takeCards(parade, playedCard, player.getHand());

        if (!deck.isEmpty()) {
            player.getHand().add(deck.remove(0));
        }
    }

    public Player checkWinner(){
        
    }

    public void addCardToParade(Card card){

    }

    private static void takeCards(List<Card> parade, Card playedCard, List<Card> playerHand) {
        int playedValue = playedCard.getValue();
        int paradeIndex = parade.size() - playedValue - 2;

        while (paradeIndex >= 0) {
            Card currentCard = parade.get(paradeIndex);
            if (currentCard.getColor() == playedCard.getColor() && currentCard.getValue() <= playedValue) {
                playerHand.add(parade.remove(paradeIndex));
            }
            paradeIndex--;
        }
    }
    
    
    public void evaluateParade(Player player){
        
    }
}
