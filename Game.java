import java.util.*;

public class Game {
    private boolean isEndGame;
    private int totalPlayers;
    private List<Player> playerList;
    private Parade parade;
    private Deck deck;
    private PointsCalculator pc;

    public Game() {
        isEndGame = false;
        playerList = new ArrayList<Player>();
        parade = new Parade();
        deck = new Deck();
        pc = new PointsCalculator(playerList);
        // Intro
        System.out.println("Welcome to PARADE!");
        System.out.println("Press Enter to Start");
        Scanner sc = new Scanner(System.in);

        // Choosing number of players and CPU
        int numPlayers = 0;
        int numCPU = 0;

        while (true) {
            System.out.print("Enter the number of players > ");
            numPlayers = sc.nextInt();

            System.out.print("Enter the number of CPU > ");
            numCPU = sc.nextInt();

            totalPlayers = numCPU + numPlayers;
            if (totalPlayers > 1 && totalPlayers < 7  ) {
                break;
            }
            // Catch exception here?
            // jared : maybe can do "try catch loop" like in w7 resource q3
            if (numPlayers + numCPU <= 1) {
                System.out.println("Invalid number of players, there has to be at least 2 players / CPU");
            } else if (numPlayers + numCPU > 6) {
                System.out.println("Too many players, maximum number of players is 6");
            }

        }

        // Initialising players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter Player" + i + " name > ");
            String name = sc.nextLine();
            playerList.add(new Player(name));
        }

        // Adding in CPU with random names
        for (int i = 0; i < numCPU; i++) {
            playerList.add(new aiPlayer());
        }

        // Randomising turn order 
        Collections.shuffle(playerList);
        System.out.println("Turn order:");
        System.out.println(playerList);
        System.out.println("Press Enter to Continue");
        sc.nextLine();

        // Deal 5 cards to each player 
        for (Player p: playerList) {
            for (int j = 0; j < 5; j++) {
                Card playerStarting = deck.drawcard();
                p.draw(playerStarting);
            }
        }

        // Deal 6 cards to parade
        for (int i = 0; i < 6; i++) {
            Card paradeStarting = deck.drawcard();
            parade.addCardToParade(paradeStarting);
        }

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

    public int start() {

        int endPlayerIndex = 0;

        // runs player turn until endgame
        while (isEndGame == false) {
            for (int i = 0; i < totalPlayers && isEndGame == false; i++) {
                Player current = playerList.get(i);
                playerTurn(current);
                checkEndGame(current);
                if (isEndGame == true) {

                    // get the index of the last player before endgame started
                    endPlayerIndex = i;

                }
            }
        }

        // get index of next player
        
        return endPlayerIndex;
    }

    public void playerTurn(Player player) {
        // Print out parade and playercardpile
        parade.printParade();
        player.printPlayerCardPile();

        // CPU
        if (player instanceof aiPlayer) {
            // 1) Choose a card to laydown and collect cards from parade
            //ai choose card, should override this in the child class
            Card chosen = player.chooseCard();

            // Print out card that player has chosen
            
            System.out.println("Chosen card : ");
            chosen.printCard();
            
            List<Card> paradeDrawn = parade.removedFromParade(chosen);

            // 2) put into player's playercardpile
            player.addIntoPlayerCardPile(paradeDrawn);

            // 3) player draws card from deck
            Card top = deck.drawcard();
            player.draw(top);

            // End CPU, this should also be overriden in the child class
            player.endTurnPrint();
        } else {
            // Player
            player.printPlayerCardPile();
            // 1) Choose a card to laydown and collect cards from parade
            // prompt user for which card to throw
            Card chosen = player.chooseCard();

            // Print out card that player has chosen
            System.out.println("Chosen card : ");
            chosen.printCard();
  
            // get list of cards drawen from parade
            List<Card> paradeDrawn = parade.removedFromParade(chosen);

            // 2) put into player's playercardpile
            player.addIntoPlayerCardPile(paradeDrawn);

            // 3) player draws card from deck
            Card top = deck.drawcard();
            player.draw(top);

            // ending turn - print out drawn card + hand + playercardpile
            player.endingTurnPrint(paradeDrawn, top);
        }

    }

    public String checkEndGame(Player player) {
        // check if deck is empty
        String reason = "";
        if (deck.getNumberOfRemainingCards() == 0) {
            setEndGame(true);
            reason = "Deck has no more cards!";
        }
        // implement logic to check everyone's playercardpiles, should return index of player that has all colors
        boolean playerHasAllColors = false;
        if (playerHasAllColors == true) {
            setEndGame(true);
            reason = player.getName() + "has collected all the colors!";
        }
        // returns reason why endgame has started, will be empty if endgame criteria not fulfilled
        return reason;
    }

    public void startEndGame(int endPlayerIndex) {
        int nextPlayerIndex = (endPlayerIndex + 1) % playerList.size();
        Player lastPlayer = playerList.get(endPlayerIndex);

        // get reason why endgame started
        String reason = checkEndGame(lastPlayer);
        System.out.print("Endgame is starting, " + reason);
        System.out.println("Everyone has one last turn!");

        // starting from the nextplayer, give everyone one last turn
        for (int i = nextPlayerIndex; i < totalPlayers; i++) {
            Player player = playerList.get(i % totalPlayers);
            playerTurn(player);
        }

        // implement logic to find player with majority of each color and flip those cards over
        // returns hashmap of 6 the majority cardpile of each color and which player owns them
        HashMap <Card,List<Player>> hashmap = pc.majorityDecider();
        List<Player> majorityred = hashmap.get("RED");

        //iterate through each colour 
        for(int i = 0 ; i < 6 ; i++){
            //initialise majority
            int majority = 0;
            //loop through player list 
            for(Player p : playerList){
                if(p.getStack())
            }

        

            //for player with majority, find the number of cards

            //loop through agn, 

            //flip cards of those that match the majority number 
            
        }

    }

    public void printTurnOrder(){
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            System.out.print(p.getName());
            
            if (i != playerList.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();

    }


}
