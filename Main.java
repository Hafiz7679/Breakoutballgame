import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GamePlay gameplay = new GamePlay();

        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Breakout Ball Game");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        obj.add(gameplay);   // add panel first
        obj.setVisible(true);

        gameplay.requestFocusInWindow(); // make sure keys work
    }
}
