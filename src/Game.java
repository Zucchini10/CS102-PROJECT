package src;
import java.util.*;

public class Game {
    private boolean isEndGame;
    private int totalPlayers;
    private List<Player> playerList;
    private Parade parade;
    private Deck deck;
    private PointsCalculator pc;
    // string colour reset code = \033[0m\033[1m

    public Game() {
        isEndGame = false;
        playerList = new ArrayList<Player>();
        parade = new Parade();
        deck = new Deck();
        totalPlayers = 0;
        // Intro
        System.out.println("Welcome to PARADE!");
        System.out.println("Press Enter to Start");
        Scanner sc = new Scanner(System.in);

        // Choosing number of players and CPU
        int numPlayers = 0;
        int numCPU = 0;

        do {
            System.out.print("Enter the number of players > ");
            System.out.print("Enter the number of players > ");

            // Validate numPlayers input
            while (true) {
                try {
                    numPlayers = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number for players.");
                    sc.nextLine(); // Clear the buffer
                }
            }

            System.out.print("Enter the number of CPU > ");

            // Validate numCPU input
            while (true) {
                try {
                    numCPU = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number for CPU.");
                    sc.nextLine(); // Clear the buffer
                }
            }

            totalPlayers = numCPU + numPlayers;

            // Check if the total number of players is within valid limits
            if (totalPlayers <= 1) {
                System.out.println("Invalid number of players, there has to be at least 2 players / CPU.");
            } else if (totalPlayers > 6) {
                System.out.println("Too many players, maximum number of players is 6.");
            }
        } while (totalPlayers > 1 && totalPlayers < 7);

        // Initialising players
        sc.nextLine();
        for (int i = 1; i < numPlayers + 1; i++) {
            System.out.print("Enter Player " + i + " name > ");
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
        printTurnOrder();
        System.out.println("Press Enter to Continue");
        sc.nextLine();

        // putting final playerlist into points calculator
        pc = new PointsCalculator(playerList);

        // Deal 5 cards to each player
        for (Player p : playerList) {
            for (int j = 0; j < 5; j++) {
                Card playerStarting = deck.drawCard();
                p.draw(playerStarting);
            }
        }

        // Deal 6 cards to parade
        for (int i = 0; i < 6; i++) {
            Card paradeStarting = deck.drawCard();
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
                // if player is CPU, run CPUturn if it is human player, run normal playerTurn
                if (current instanceof aiPlayer) {
                    CPUTurn(current);
                } else {
                    playerTurn(current);
                }

                checkEndGame(current);
                if (isEndGame == true) {

                    // get the index of the last player before endgame started
                    endPlayerIndex = i;

                }
            }
        }

        // return index of last player

        return endPlayerIndex;
    }

    public void CPUTurn(Player player) {
        // Print out parade and playercardpile
        parade.printParade();
        player.printPlayerCardPile();

        // 1) Choose a card to laydown and collect cards from parade
        // ai choose card, should override this in the child class
        Card chosen = player.chooseCard();

        // Print out card that player has chosen

        System.out.println("Chosen card : ");
        chosen.printCard();
        System.out.print("Press Enter to Continue > ");
        sc.nextLine();
        List<Card> paradeDrawn = parade.removedFromParade(chosen);

        // 2) put into player's playercardpile
        player.addIntoPlayerCardPile(paradeDrawn);

        // 3) player draws card from deck
        Card top = deck.drawCard();
        player.draw(top);

        // End CPU, this should also be overriden in the child class
        // player.endTurnPrint();
    }

    public void playerTurn(Player player) {
        Scanner sc = new Scanner(System.in);

        // Print out parade and playercardpile
        parade.printParade();
        player.printPlayerCardPile();

        // 1) Choose a card to laydown and collect cards from parade
        char input = 'n';
        Card chosen = null;
        while (input != 'y' && input != 'Y'){

        // prompt user for which card to throw
        chosen = player.chooseCard();

        // Print out card that player has chosen
        System.out.println("Chosen card : ");
        chosen.printCard();
        System.out.println("Confirm? (y/n)");
        input = sc.nextLine().charAt(0);

        }
        

        // get list of cards drawen from parade
        List<Card> paradeDrawn = parade.removedFromParade(chosen);

        // 2) put into player's playercardpile
        player.addIntoPlayerCardPile(paradeDrawn);

        // 3) player draws card from deck
        Card top = deck.drawCard();
        player.draw(top);

        // ending turn - print out drawn card + hand + playercardpile
        player.endingTurnPrint(paradeDrawn, top);
        System.out.println("Press enter to confirm");
        sc.nextLine();
    }



    public String checkEndGame(Player player) {
        // check if deck is empty
        String reason = "";
        if (deck.isEmpty() == true) {
            setEndGame(true);
            reason = "Deck has no more cards!";
        }

        // check if player has collected all colours
        if (player.hasAllColours() == true) {
            setEndGame(true);
            reason = player.getName() + "has collected all the colors!";
        }
        // returns reason why endgame has started, will be empty if endgame criteria not
        // fulfilled
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
    }

    public void calculateWinner() {
        Scanner sc = new Scanner(System.in);
        // implement logic to find player with majority of each color and flip those
        // cards over
        // returns hashmap of 6 the majority cardpile of each color and which player
        // owns them
        HashMap<String, List<Player>> majorityHashmap = pc.majorityDecider();
        String[] colours = { "RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE" };

        for (String colour : colours) {
            List<Player> majorityPlayers = majorityHashmap.get(colour);
            flipMajorityCardPile(majorityPlayers, colour);
        }

        // print the total score for each player then decide the winner
        Player winner = null;
        int highest = -1;
        for (Player player : playerList) {
            System.out.println(player.getName() + " :");
            player.printPlayerCardPile();

            System.out.println("Final Score : " + player.getScore());
            System.out.print("Press Enter to continue > ");
            sc.nextLine();

            if (player.getScore() > highest) {
                winner = player;
                highest = player.getScore();
            }
        }
        System.out.println("winner is..." + winner.getName());

    }

    public void flipMajorityCardPile(List<Player> majorityPlayers, String colour) {
        for (Player player : majorityPlayers) {
            PlayerCardPileStack pcps = player.getStack();
            HashMap<String, PlayerCardPile> hm = pcps.getPlayerCardPileStack();
            PlayerCardPile pc = hm.get(colour);
            pc.setFaceUp(true);
        }
    }

    public void printTurnOrder() {
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
