Breakout Ball Game (Java Swing)

This is a simple Breakout-style arcade game built using Java Swing and AWT.
The player controls a paddle at the bottom of the screen to bounce a ball and break all the bricks.

🕹️ How to Play

Use the Right Arrow ⬅️➡️ and Left Arrow keys to move the paddle.

The ball starts moving as soon as you move the paddle.

Break all the bricks to win.

If the ball touches the bottom edge, the game is over.

Press Enter to restart after Game Over or after winning.

✨ Features

Classic brick-breaker gameplay

Paddle & ball mechanics implemented with collision detection

Score system (+5 points for each brick destroyed)

Winning condition when all bricks are broken

Restart option after Game Over / Win

Built entirely with Java Swing (JFrame, JPanel, Timer, KeyListener)

📂 Project Structure
├── Main.java          # Entry point, creates the game window
├── GamePlay.java      # Main game logic, rendering, controls
└── MapGenerator.java  # Brick grid generator & renderer

⚙️ How to Run

Clone the repository:

git clone https://github.com/your-username/breakout-game-java.git
cd breakout-game-java


Compile the code:

javac *.java


Run the game:

java Main


🚀 Future Improvements

Smooth paddle movement (hold arrow keys instead of tapping)

Power-ups (multi-ball, bigger paddle, etc.)

Difficulty levels (increase ball speed over time)

Custom brick layouts
