import java.util.*;

public class Game {
    private boolean isEndGame;
    private int totalPlayers;
    private List<Player> playerList;
    private Parade parade;
    private Deck deck;

    public Game(){
        isEndGame = false;
        playerList = new ArrayList<Player>();
        parade = new Parade();
        deck = new Deck();

        // Intro
        System.out.println("Welcome to PARADE!");
        System.out.println("Press Enter to Start");
        Scanner sc = new Scanner(System.in);

        // Choosing number of players and CPU
        int numPlayers = 0;
        int numCPU = 0;
        while (true){
        System.out.print("Enter the number of players > ");
        numPlayers = sc.nextInt();

        System.out.print("Enter the number of CPU > ");
        numCPU = sc.nextInt();

        if (numPlayers + numCPU > 1 ){
            break;
        }
        // Catch exception here?
        if (numPlayers + numCPU <= 1){
            System.out.println("Invalid number of players, there has to be at least 2 players / CPU");
        } else if (numPlayers + numCPU > 10){
            System.out.println("Too many players, maximum number of players is 10");
        }
        
        }

        totalPlayers = numCPU + numCPU;

        // Initialising players
        for(int i = 0;i<numPlayers;i++){
            System.out.print("Enter Player" + i + " name > ");
            String name = sc.nextLine();
            playerList.add(new Player(name));
        }

        // Adding in CPU with random names
        for (int i = 0;i<numCPU;i++){
            playerList.add(new aiPlayer());
        }
        
        // Randomising turn order
        // Might need to redo how we randomise
        Collections.shuffle(playerList);
        System.out.println("Turn order:");
        System.out.println(playerList);
        System.out.println("Press Enter to Continue");
        sc.nextLine();
    }

    // Getters and Setters
    public boolean isEndGame() {
        return isEndGame;
    }

    public void setEndGame(boolean isEndGame) {
        this.isEndGame = isEndGame;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Parade getParade() {
        return parade;
    }

    public void setParade(Parade parade) {
        this.parade = parade;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int start(){
        
        int endPlayerIndex=0;

        // runs player turn until endgame
        while (isEndGame==false){
            for (int i = 0;i<totalPlayers;i++){
                Player current = playerList.get(i);
                playerTurn(current);
                checkEndGame();
                if (isEndGame==true){

                    // get the index of the last player before endgame started
                    endPlayerIndex = i;
                    break;
                }
            }
        }
        
        // get index of next player
        int nextPlayerIndex = (endPlayerIndex=1)%playerList.size();
        return nextPlayerIndex;
    }

    public void playerTurn(Player player){
        // Print out parade and playercardpile
        parade.printParade();
        
        // CPU
        if (player instanceof aiPlayer){
            // 1) Choose a card to laydown and collect cards from parade
            int playedCardIndex = player.CPUMove();

            // Print out card that player has chosen 
            Card chosen = player.getCard(playedCardIndex);
            chosen.toString();
            List <Card> paradeDrawn = playCard(player,playedCardIndex);

            // 2) put into player's playercardpile
            player.addIntoPlayerCardPile(paradeDrawn);

            // 3) player draws card from deck
            Card top = deck.drawcard();
            player.drawFromDeck(top);

            // End CPU
            player.endTurnPrint();
        } else {
            // Player
            player.printPlayerCardPile();
            // 1) Choose a card to laydown and collect cards from parade
            int playedCardIndex = playerChooseCard(player);

            // Print out card that player has chosen 
            Card chosen = player.getCard(playedCardIndex);
            chosen.toString();

            List <Card> paradeDrawn = playCard(player,playedCardIndex);

            // 2) put into player's playercardpile
            player.addIntoPlayerCardPile(paradeDrawn);

            // 3) player draws card from deck
            Card top = deck.drawcard();
            player.drawFromDeck(top);

            // ending turn - print out drawn card + hand + playercardpile
            player.endingTurnPrint(paradeDrawn,top);
        }

    }

    // should this be in player class???
    public int playerChooseCard(Player player){
        Scanner sc = new Scanner(System.in);
        player.printHand();
        System.out.println("Choose a card >");
        int playedCardIndex = sc.nextInt();
        
        return playedCardIndex;

    }

    public List<Card> playCard(Player player, int playedCardIndex){
        // update playCard function
        Card playedCard= player.playCard(playedCardIndex);

        // get cards from the parade after playing card
        List<Card> paradeDrawn = parade.playedCard(playedCard);
        
        return paradeDrawn;
    }


    public int checkEndGame(){
        boolean playerHasAllColors = false;
        int answer = 0;
        if (deck.getNumberOfRemainingCards()==0){
            setEndGame(true);
            answer = -1;
        }
        if (playerHasAllColors == true){
            setEndGame(true);
            answer = 2;
        }
        // Check which endgame condition it fulfills and returns -1 / index of player that has all the cards
        return answer;
    }

    public void startEndGame(int nextPlayer){
        System.out.print("Endgame is starting, ");

        // get how the endgame has started
        int checkEndGameNum = checkEndGame();
        if (checkEndGameNum == -1){
            System.out.println("Deck has no more cards");
        } else if (checkEndGameNum > 0){
            System.out.println(playerList.get(checkEndGameNum) + " has collected all the colours!");
        }

        System.out.println("Everyone has one last turn!");

        // starting from the nextplayer, give everyone one last turn
        for (int i = nextPlayer;i<totalPlayers;i++){
            playerTurn(playerList.get(i%totalPlayers));
        }

        // implement logic to find player with majority of each color and flip those cards over
        
    }
    
}
