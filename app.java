public class app {
    public static void main(String[] args) {

                
        Game g1 = new Game();

        // Start Game and return the index of the player that is first for endgame 
        int nextPlayerIndex = g1.start();
        
        // start endgame
        g1.startEndGame(nextPlayerIndex);
        
        System.out.println();
        
        // Calculate winner
        g1.calculateWinner();
        
                    
            }
        }