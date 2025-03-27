public class app {
    public static void main(String[] args) {

                
        Game g1 = new Game();

        // Start Game 
        int nextPlayerIndex = g1.start();
                   
        g1.startEndGame(nextPlayerIndex);
                
        System.out.println();
        // Calculate winner
        // g1.calculateWinner();
        
                    
            }
        }
        

