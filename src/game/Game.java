package game;
import java.util.*;

import models.Card;
import models.Deck;
import models.Parade;
import player.Player;
import player.aiPlayer;

public class Game {
    private boolean isEndGame;
    private int totalPlayers;
    private List<Player> playerList;
    private Parade parade;
    private Deck deck;
    private PointsCalculator pc;
    private String colourResetCode = "\033[0m\033[1m";

    // for testing purposes
    public Game(List<Player> players, Parade parade, int num){
        isEndGame = true;
        this.playerList = players;
        this.parade = parade;
        deck = new Deck();
        totalPlayers = num;


        if (totalPlayers == 2) {
            pc = new PointsCalculator2P(playerList);
        } else {
            pc = new PointsCalculator(playerList);
        }
    }

    public Game() {
        // Initialising attributes
        isEndGame = false;
        playerList = new ArrayList<Player>();
        parade = new Parade();
        deck = new Deck();
        totalPlayers = 0;

        // Intro
        System.out.println(colourResetCode
                + "\n------------------------------------------------------------------ Welcome to PARADE! ------------------------------------------------------------------\n");
        System.out.print("Press Enter to Start ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        // Create players and CPU and add them into playerList
        totalPlayers = initialisePlayers();

        // initialise points calculators for the game, either 2 player game or >2 player game
        System.out.println();
        if (totalPlayers == 2) {
            pc = new PointsCalculator2P(playerList);
        } else {
            pc = new PointsCalculator(playerList);
        }

        // Randomising turn order and printing it out
        Collections.shuffle(playerList);
        System.out.println("Turn order:");
        printTurnOrder();
        System.out.print(colourResetCode + "Press Enter to Continue ");
        sc.nextLine();
        System.out.println();

        // Deal 5 cards to each player and deal 6 cards to parade
        dealStartingCards();

        // print parade before starting game
        parade.printParade();
        System.out.print(colourResetCode + "\nPress Enter to start game > ");
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

    public int start() {
        System.out.println(
                "------------------------------------------------------------------- GAME START! -------------------------------------------------------------------");
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

        // return index of last player
        return endPlayerIndex;
    }

    public void startEndGame(int endPlayerIndex) {
        Scanner sc = new Scanner(System.in);

        // this is the index of the next player
        int nextPlayerIndex = (endPlayerIndex + 1) % playerList.size();

        // get reason why endgame started
        Player lastPlayer = playerList.get(endPlayerIndex);
        String reason = checkEndGame(lastPlayer);
        System.out.print("Endgame is starting, " + reason);
        System.out.println(" Everyone has one last turn!");
        System.out.print(colourResetCode + "Press Enter to Continue");
        sc.nextLine();

        // re-order the playerList to give everyone one last turn, and next player is the first element
        Collections.rotate(playerList, -nextPlayerIndex);
        System.out.println(
                "------------------------------------------------------------------ Endgame starting ... ------------------------------------------------------------------\n");
        printTurnOrder();
        System.out.println();

        // starting from the nextplayer, give everyone one last turn
        for (int i = 0; i < totalPlayers; i++) {
            Player player = playerList.get(i);
            playerTurn(player);
        }

        // discard 2 cards from hand and place remaining 2 cards into cardpile
        System.out.println(
                "------------------------------------------------------------------ Discard Turn ------------------------------------------------------------------\n");
        for (int i = 0; i < totalPlayers; i++) {

            Player player = playerList.get(i);
            System.out.println("----- " + player.getName() + colourResetCode + "'s discard turn -----");
            playerTurnDiscard(player);
        }
    }

    public void calculateWinner() {
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "------------------------------------------------------------------- GAME END! -------------------------------------------------------------------\n");

        System.out.println("Tabulating score ... ");
        
        // find the players who has majority in their playercardpiles and flips them 
        pc.flipMajorityCardPiles();

        // find the winner by finding the player with lowest score and lowest number of
        // cards, then get winner score
        List<Player> winners = pc.getWinners();
        
        // print out
        endingScorePrint();

        System.out.print("Press Enter to get Winner");
        sc.nextLine();

        // finally print out the winner(s)
        winnerPrint(winners);

    }

    private void playerTurn(Player player) {
        Scanner sc = new Scanner(System.in);

        // print out which player turn
        System.out.println("\n----- " + player.getName() + colourResetCode + "'s Turn ! -----\n");

        // Print out parade and playercardpile
        player.printPlayerCardPile();
        parade.printParade();
        Card chosen = null;

        // 1) Get the user to choose card he plays into parade
        if (!(player instanceof aiPlayer)) {
            chosen = confirmSelection(player);
        } else {
            chosen = player.chooseCard(parade);
        }

        // 2) Get the list of cards after playing chosen into parade
        List<Card> paradeDrawn = parade.removedFromParade(chosen);

        // 3) put into player's playercardpile
        player.addIntoPlayerCardPile(paradeDrawn);

        // 4) player draws card from deck
        Card top = null;
        if (deck.isEmpty() == false && isEndGame == false) {
            top = deck.drawCard();
            player.draw(top);
        }

        System.out.println();

        // if player : ending turn - print out drawn card + hand + playercardpile
        // if CPU : ending turn - print out playercardpile
        player.endingTurnPrint(paradeDrawn, top);
    }

    private String checkEndGame(Player player) {
        // check if deck is empty
        String reason = "";
        if (deck.isEmpty() == true) {
            setEndGame(true);
            reason = "Deck has no more cards!";
        }

        // check if player has collected all colours
        if (player.hasAllColours() == true) {
            setEndGame(true);
            reason = player.getName() + colourResetCode + " has collected all the colors!";
        }
        // returns reason why endgame has started, will be empty if endgame criteria not
        // fulfilled
        return reason;
    }

    private int initialisePlayers() {
        int totalPlayers = 0;
        int numPlayers = 0;
        int numCPU = 0;
        while (totalPlayers <= 1 || totalPlayers > 6) {

            // Validate numPlayers input
            numPlayers = validateNumberOfPlayers(false);

            // Validate numCPU input
            numCPU = validateNumberOfPlayers(true);

            // Check if the total number of players is within valid limits
            totalPlayers = numCPU + numPlayers;
            if (totalPlayers <= 1) {
                System.out.println("Invalid number of players, there has to be at least 2 players / CPU.");
            } else if (totalPlayers > 6) {
                System.out.println("Too many players, maximum number of players is 6.");
            }
        }

        // Get Player names
        inputPlayerName(numPlayers);

        System.out.println();
        // Get CPU difficulty
        if (numCPU > 0) {
            inputCPUDifficulty(numCPU);
        }

        return totalPlayers;
    }

    private int validateNumberOfPlayers(boolean CPU) {
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;

        // loop to keep prompting for number if not valid
        while (true) {
            try {
                // if asking for number of players or CPU
                if (!CPU) {
                    System.out.print("\nEnter the number of players > ");
                } else {
                    System.out.print("Enter the number of CPU > ");
                }

                numPlayers = sc.nextInt();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number");
                sc.nextLine(); // Clear the buffer
            }
        }

        return numPlayers;

    }

    private void dealStartingCards() {

        System.out.println("Initialising Game...\n");

        // deal 5 cards to player
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

    private void inputPlayerName(int numPlayers) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        
        // for every player, keep prompting them for player name until valid input or no duplicate name
        for (int i = 1; i <= numPlayers; i++) {
            while (true) {
                try {
                    System.out.print("Enter Player " + i + " name > ");
                    String inputName = sc.nextLine().trim();

                    // Check for duplicate names
                    for (Player p : playerList) {
        
                        String existingName = p.getName();

                        // if name exists, throw error and loop to prompt for another name
                        if (existingName.equals(inputName)) {
                            throw new Exception("Name already taken. Please enter a different name.");
                        }
                    }

                    // if does not exist, create player and put inside player list
                    playerList.add(new Player(inputName));
                    break;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void inputCPUDifficulty(int numCPU) {
        Scanner sc = new Scanner(System.in);

        
        boolean valid = false;
        String difficulty = null;

        // loops until valid input 
        while (!valid) {
            System.out.println("1. Easy   2. Medium    3. Hard");
            System.out.print("Enter CPU difficulty > ");

            String difficultyNum = sc.nextLine();
            if (difficultyNum.equals("1")) {
                difficulty = "Easy";
                valid = true;
            } else if (difficultyNum.equals("2")) {
                difficulty = "Medium";
                valid = true;
            } else if (difficultyNum.equals("3")) {
                difficulty = "Hard";
                valid = true;
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
            }

        }

        // creates number of CPUs with chosen difficulty
        System.out.println("Difficulty chosen : " + difficulty);
        for (int i = 1; i < numCPU + 1; i++) {
            playerList.add(new aiPlayer(difficulty));

        }

    }

    private void printTurnOrder() {

        // print out every player and the order in the list
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            System.out.print(p.getName());

            if (i != playerList.size() - 1) {
                System.out.print(colourResetCode + " -> ");
            }
        }
        System.out.println();
    }

    private void endingScorePrint() {
        Scanner sc = new Scanner(System.in);

        // for every player, print out their playercardpile and their score after game has ended
        for (Player player : playerList) {

            System.out.println("----- " + player.getName() + " -----");
            player.printPlayerCardPile();
            System.out.println("Final Score : " + player.getScore());
            System.out.print("Press Enter to continue > ");
            sc.nextLine();
        }
    }

    public void playerTurnDiscard(Player player) {
        Scanner sc = new Scanner(System.in);
        // Print out parade and playercardpile
        System.out.println();
        player.printPlayerCardPile();
        Card chosen = null;

        // 1) Get the user to choose 2 cards to discard
        for (int i = 0; i < 2; i++) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    chosen = player.discardCard();
                    System.out.println(colourResetCode + "Discarded card : ");
                    chosen.printCard();
                    // while loop for confirm and undo
                    while (true) {
                        try {
                            // only enter if player is not aiplayer
                            if (!(player instanceof aiPlayer)) {
                                System.out.println(colourResetCode + "Press 1 to CONFIRM or 2 to UNDO selection.");

                                int confirmChoice = sc.nextInt();
                                sc.nextLine();
                                // if CONFIRM -> break out of both while loops
                                if (confirmChoice == 1) {
                                    validInput = true; // Confirmed, exit the loop
                                    break;
                                }
                                // else if UNDO -> break out of "while(true)" loop
                                else if (confirmChoice == 2) {
                                    System.out.println("Undoing selection. Please choose again.");
                                    // Loop continues, so no need to change validInput
                                    player.getHand().add(chosen);
                                    break;
                                }
                                // else prompt undo/confirm again
                                else {
                                    System.out.println("Invalid choice! Please press 1 to CONFIRM or 2 to UNDO.");
                                }

                            }
                            // handle for ai player
                            else {
                                validInput = true;
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter either 1 or 2");
                            sc.nextLine();
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please select a valid card number.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid input! Please select a valid card number.");
                }
            }
        }

        // System.out.println(colourResetCode + "Press Enter to Continue");
        // sc.nextLine();

        // 2) put into player's playercardpile
        player.addIntoPlayerCardPile(player.getHand());

        System.out.println();
        player.printPlayerCardPile();
        System.out.println(colourResetCode + "Press Enter to continue");
        sc.nextLine();
    }

    public Card confirmSelection(Player player) {
        Scanner sc = new Scanner(System.in);
        boolean confirm = false;
        boolean valid = false;
        Card chosen = null;

        // loop until player confirms his choice
        while (!confirm) {
            chosen = player.chooseCard(parade);
            valid = false;
            // loop until player puts in a valid input
            while (!valid) {
                System.out.print(colourResetCode + "Press 1 to confirm or 2 to select another card > ");
                String confirmNum = sc.nextLine();
                if (confirmNum.equals("1")) {
                    confirm = true;
                    valid = true;
                } else if (confirmNum.equals("2")) {
                    valid = true;
                } else {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }
        }

        player.removeCardFromHand(chosen);
        return chosen;
    }

    public void winnerPrint(List<Player> winners){
        if (winners.size()==1){
            Player winner = winners.get(0);
            System.out.println(
                "\n\n\nWinner is... " + winner.getName() + colourResetCode + "! with " + winner.getScore() + " points" + ", and " + winner.getStack().getTotalCards() + " cards !");
        } else {
            System.out.print("\n\n\nThere is a tie! Winners are ");
            for (int i = 0;i<winners.size();i++){
                Player p = winners.get(i);
                System.out.print(p.getName());

                if (i!=winners.size()-1){
                    System.out.print(" and ");
                }
            }

            Player winner = winners.get(0);
            System.out.println("! with " + winner.getScore() + " points" + ", and " + winner.getStack().getTotalCards() + " cards !");
        }
    }
}
