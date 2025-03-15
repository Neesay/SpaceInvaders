# CW4-SpaceInvaders

## Current objective: Display the basic entities on the screen.

## To-Do List:
- Create player class and render a player object on the display
- Create alien class and render a single alien (for now) on the display
- Create barrier class and render a single barrier
- Create laser class and render a single laser


## Classes:
- Window: 
    - the JavaFX window.
    - Has menu bar
    - Has canvas area to display the game
- GameDisplay:
    - Displays the game animations on the window.
    - Connects between the "Window" and "Game" class
- Game: 
    - the thing that simulates the actual game simulation (handles the dynamics of the game).
    - Initialise the player, the aliens, the barriers at the start
    - Handle interactions between entities (player shoots alien, barrier wears down, etc.)
- Player:
    - to determine the stats of the player and store data
    - Move left and right
    - Shoot laser
    - Has lives (reduced when hit by laser)
- Aliens: 
    - diff types for diff levels health values
    - abstract class to branch off different aliens
- Barrier: 
    - the barriers to defend the player against aliens
    - wear down by any laser
    - like 8 stages 
- Laser:
    - Can damage player, alien, barrier
    - Should move vertically across the screen
- Score:
    - Update score based on how many aliens the player has killed


## Extra Ideas:
- moving background to make it look like moving through space
- Formations: different layout for aliens for different levels
- Boss:
- powerups:
  - shielding
  - diff projectiles
- leaderboard
