#CS102-PROJECT G1T6

This project is our terminal-based adaptation of the card game Parade, designed to be played locally on a single device.
Our main method is on src/App.java. To start game, please run ./compile.bat and ./run.bat

Assumptions:

1) The assignment also states "It should allow 2 or more players: human vs computer(s) or human vs human(s)". For our game, we are able to allow play within more than 1 Human and more than 1 CPU. Human(s) vs computer(s).

2) If 2 players have the same amount of cards in player card pile, they are both considered majority. If all 6 players have the same number of cards from their playercardpiles, they will all be considered to have the majority

Things to Note:

- The game will not display the CPU's hand / card drawn from deck, only his player card piles
- For the best experience, play with the terminal fully expanded.
- Sometimes the display may exceed the space given and the player is required to scroll up to see all his cards
- The game does not support networking and does not use images
- Our Test.java contains another main method used for testing purposes to check for edge cases. By passing in playerlist and a parade, can check if our code works for different scenarios. The code has been commented out. Run this code to run Test.java : javac -d "classes" -cp "src" src/Test.java ; java -cp "classes" src.App