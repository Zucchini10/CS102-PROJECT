import java.util.*;

public class Game {
    private boolean isEndGame;
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

        //Initialising players
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

    public void start(){
        
        while (isEndGame==false){
            for (int i = 0;i<playerList.size();i++){
                playerTurn(playerList.get(i));
                if (checkEndGame()==true){
                    break;
                }
            }
        }
    }

    public void playerTurn(Player player){

    }

    public boolean checkEndGame(){
        // Check if draw pile is out or a player has 6 cards
        if (){
            
        }
        return false;
    }
    
}
