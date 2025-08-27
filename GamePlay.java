import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // ✅ clears old frame before drawing

        // background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // borders
        g.setColor(Color.blue);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 3, 3, 592);
        g.fillRect(691, 3, 3, 592);

        // score
        g.setColor(Color.green);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Score: " + score, 550, 30);

        // paddle
        g.setColor(Color.orange);
        g.fillRect(playerX, 550, 100, 8);

        // ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);

        // bricks
        map.draw((Graphics2D) g);

        // game over
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("GAME OVER! Score: " + score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        // win condition
        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WON! Score: " + score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press Enter to Restart", 230, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20)
                    .intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            // brick collision
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

                        if (ballRect.intersects(rect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;

            if (ballposX < 0) ballXdir = -ballXdir;
            if (ballposY < 0) ballYdir = -ballYdir;
            if (ballposX > 670) ballXdir = -ballXdir;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

   @Override
    public void keyPressed(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_RIGHT && playerX < 600) {
        play = true;  // ✅ start ball movement
        playerX += 20;
    }

    if (e.getKeyCode() == KeyEvent.VK_LEFT && playerX > 10) {
        play = true;  // ✅ start ball movement
        playerX -= 20;
    }

    if (e.getKeyCode() == KeyEvent.VK_ENTER && !play) {
        play = true;
        ballposX = 120;
        ballposY = 350;
        ballXdir = -1;
        ballYdir = -2;
        playerX = 310;
        score = 0;
        totalBricks = 21;
        map = new MapGenerator(3, 7);

        repaint();
    }
    }

}
