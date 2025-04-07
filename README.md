#CS102-PROJECT G1TX

This project is our terminal-based adaptation of the card game Parade, designed to be played locally on a single device.

Assumptions:

1) The official rulebook states that "The start player is determined randomly. Starting with the start player, the game continues clockwise." This suggests that turn order is fixed once the game begins, with each player always following the same opponent. However, we found this too restrictive, as it limits player interactions. Instead, we randomize the turn order at the start of each round to introduce more variability.

2) The assignment also states "It should allow 2 or more players: human vs computer(s) or human vs human(s)". For our game, we are able to allow play within more than 1 Human and more than 1 CPU. Human(s) vs computer(s).

3) If 2 players have the same amount of cards in player card pile, they are both considered majority. If all 6 players have the same number of cards from their playercardpiles, they will all be considered to have the majority

Things to Note:

- The game will not display the CPU's hand / card drawn from deck, only his player card piles
- For the best experience, play with the terminal fully expanded.
- Sometimes the display may exceed the space given and the player is required to scroll up to see all his cards
- The game does not support networking and does not use images 