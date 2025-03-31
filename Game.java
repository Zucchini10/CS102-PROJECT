import java.util.*;

public class Game {
    private boolean isEndGame;
    private int totalPlayers;
    private List<Player> playerList;
    private Parade parade;
    private Deck deck;
    private PointsCalculator pc;
    private String colourResetCode = "\033[0m\033[1m";

    public Game() {
        // Initialising attributes
        isEndGame = false;
        playerList = new ArrayList<Player>();
        parade = new Parade();
        deck = new Deck();
        totalPlayers = 0;

        // Intro
        System.out.println(colourResetCode
                + "\n================================================================= Welcome to PARADE! =================================================================\n");
        System.out.print("Press Enter to Start ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        // Choosing number of players and CPU
        int numPlayers = 0;
        int numCPU = 0;

        while (totalPlayers <= 1 || totalPlayers > 6) {

            // Validate numPlayers input
            while (true) {
                try {
                    System.out.print("\nEnter the number of players > ");
                    numPlayers = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number for players.");
                    sc.nextLine(); // Clear the buffer
                }
            }

            // Validate numCPU input
            while (true) {
                System.out.print("Enter the number of CPU > ");
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
        }

        // initialise points calculators for the game, either 2 player game or >2 player
        // game
        if (totalPlayers == 2) {
            pc = new PointsCalculator2P(playerList);
        } else {
            pc = new PointsCalculator(playerList);
        }

        // clear buffer
        sc.nextLine();
        System.out.println();

        // Initialising players
        for (int i = 1; i < numPlayers + 1; i++) {
            System.out.print("Enter Player " + i + " name > ");

            String name = sc.nextLine();
            playerList.add(new Player(name));
        }

        // Adding in CPU with random names
        System.out.println();

        // get CPU difficulty
        while (true) {
            System.out.println("1. Easy   2. Medium    3. Hard");
            System.out.print("Enter CPU difficulty > ");

            try {

                int difficultyNum = sc.nextInt();
                String difficulty = null;
                if (difficultyNum == 1) {
                    difficulty = "easy";
                } else if (difficultyNum == 2) {
                    difficulty = "medium";
                } else if (difficultyNum == 3) {
                    difficulty = "hard";
                } else {
                    throw new InputMismatchException();
                }

                System.out.println("Difficulty chosen : " + difficulty);
                for (int i = 1; i < numCPU + 1; i++) {
                    playerList.add(new aiPlayer(difficulty));

                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear the buffer
            }
        }

        sc.nextLine();

        // Randomising turn order
        Collections.shuffle(playerList);
        System.out.println("\nTurn order:");
        printTurnOrder();
        System.out.print(colourResetCode + "Press Enter to Continue ");
        sc.nextLine();

        // Deal 5 cards to each player
        System.out.println("\nInitialising Game...\n");
        for (Player p : playerList) {
            for (int j = 0; j < 5; j++) {
                Card playerStarting = deck.drawCard();
                p.draw(playerStarting);
            }
        }

        // Deal 6 cards to parade
        // System.out.println("Initialising parade... ");
        for (int i = 0; i < 6; i++) {
            Card paradeStarting = deck.drawCard();
            parade.addCardToParade(paradeStarting);
        }

        // print parade before starting game
        parade.printParade();
        System.out.println(colourResetCode + "\nPress Enter to start game > ");
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
                "------------------------------------------------------------------- GAME START! -------------------------------------------------------------------\n");
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

    public void playerTurn(Player player) {
        Scanner sc = new Scanner(System.in);

        // print out which player turn
        System.out.println("----- " + player.getName() + "'s Turn ! -----\n");
        // System.out.println(colourResetCode +
        // "===========================================================================================================");

        // Print out parade and playercardpile
        player.printPlayerCardPile();
        parade.printParade();
        Card chosen = null;

        // 1) Get the user to choose card he plays into parade
        boolean confirm = false;
        boolean valid = false;

        if (!(player instanceof aiPlayer)){
        while (!confirm) {
            chosen = player.chooseCard(parade);
            valid = false;
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
            if (confirm){
                player.removeCardFromHand(chosen);
            }
        } else {
            chosen = player.chooseCard(parade);
        }
        // while (!validInput) {
        // try {
        // chosen = player.chooseCard(parade);

        // // while loop for confirm and undo
        // while (true) {
        // try {
        // // only enter if player is not aiplayer
        // if (!(player instanceof aiPlayer)) {
        // System.out.println(colourResetCode + "Press 1 to CONFIRM or 2 to UNDO
        // selection.");

        // int confirmChoice = sc.nextInt();
        // sc.nextLine();
        // // if CONFIRM -> break out of both while loops
        // if (confirmChoice == 1) {
        // validInput = true; // Confirmed, exit the loop
        // break;
        // }
        // // else if UNDO -> break out of "while(true)" loop
        // else if (confirmChoice == 2) {
        // System.out.println("Undoing selection. Please choose again.");
        // // Loop continues, so no need to change validInput
        // player.getHand().add(chosen);
        // break;
        // }
        // // else prompt undo/confirm again
        // else {
        // System.out.println("Invalid choice! Please press 1 to CONFIRM or 2 to
        // UNDO.");
        // }

        // }
        // // handle for ai player
        // else {
        // validInput = true;
        // break;
        // }
        // } catch (InputMismatchException e) {
        // System.out.println("Invalid input! Please enter either 1 or 2");
        // sc.nextLine();
        // }
        // }
        // } catch (InputMismatchException e) {
        // System.out.println("Invalid input! Please select a valid card number.");
        // } catch (IndexOutOfBoundsException e) {
        // System.out.println("Invalid input! Please choose a card from 1 to 5.");
        // }
        // }

        // System.out.println(colourResetCode + "Press Enter to Continue");
        // sc.nextLine();
        List<Card> paradeDrawn = parade.removedFromParade(chosen);

        // 2) put into player's playercardpile
        player.addIntoPlayerCardPile(paradeDrawn);

        // 3) player draws card from deck
        Card top = null;
        if (deck.isEmpty() == false) {
            top = deck.drawCard();
            player.draw(top);
        }

        System.out.println();

        // if player : ending turn - print out drawn card + hand + playercardpile
        // if CPU : ending turn - print out playercardpile
        player.endingTurnPrint(paradeDrawn, top);
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
            reason = player.getName() + " has collected all the colors!";
        }
        // returns reason why endgame has started, will be empty if endgame criteria not
        // fulfilled
        return reason;
    }

    public void startEndGame(int endPlayerIndex) {
        Scanner sc = new Scanner(System.in);
        int nextPlayerIndex = (endPlayerIndex + 1) % playerList.size();
        Player lastPlayer = playerList.get(endPlayerIndex);

        // get reason why endgame started
        String reason = checkEndGame(lastPlayer);
        System.out.print("Endgame is starting, " + reason);
        System.out.println("Everyone has one last turn!");
        System.out.println(colourResetCode + "Press Enter to Continue");
        sc.nextLine();

        // re-order the playerList to give everyone one last turn, and next player is
        // the first element
        Collections.rotate(playerList, -nextPlayerIndex);
        printTurnOrder();
        // starting from the nextplayer, give everyone one last turn
        for (int i = 0; i < totalPlayers; i++) {
            Player player = playerList.get(i % totalPlayers);
            playerTurn(player);
        }
    }

    public void calculateWinner() {
        Scanner sc = new Scanner(System.in);

        // returns hashmap of 6 of the majority cardpile of each color and which
        // player(s) owns them,
        HashMap<String, List<Player>> majorityHashmap = pc.majorityDecider();
        String[] colours = { "RED", "BLUE", "GREEN", "GREY", "PURPLE", "ORANGE" };

        for(String colour : colours){
            List<Player> majorityPlayers = majorityHashmap.get(colour);
            pc.flipMajorityCardPile(majorityPlayers, colour);
        }

        // find the winner by finding the player with lowest score and lowest number of
        // cards, then get winner score
        Player winner = pc.getWinner();
        int winnerScore = winner.getScore();

        // for every player, print out his playercardpiles and their score
        for (Player player : playerList) {
            System.out.println(player.getName() + " :");

            player.printPlayerCardPile();
            System.out.println("Final Score : " + player.getScore());
            System.out.print("Press Enter to continue > ");
            sc.nextLine();
        }

        // finally print out the winner
        System.out.println("Winner is... " + winner.getName() + " with " + winnerScore + " points");

    }

    public void printTurnOrder() {
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            System.out.print(p.getName());

            if (i != playerList.size() - 1) {
                System.out.print(colourResetCode + " -> ");
            }
        }
        System.out.println();
    }
}
