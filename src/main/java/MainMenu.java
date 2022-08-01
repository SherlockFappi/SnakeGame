import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    private static final short winWidth = (short) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final short winHeight = (short) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static final JFrame mainMenu = new JFrame();

    private static final Container c = mainMenu.getContentPane();

    private static final JLabel title = new JLabel("SNAKE");

    private static final JButton startGame = new JButton("Start Game");

    public static void main(String[] args) {
        title.setBounds(0, winHeight / 5, winWidth, winHeight / 10);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, winHeight / 10));
        title.setForeground(Color.RED);
        title.setBackground(Color.DARK_GRAY);
        title.setOpaque(true);
        c.add(title);

        startGame.setBounds((int) ((int)winWidth / 2.5), winHeight / 3, (int)(winWidth / 2.5) / 2, winHeight / 20);
        startGame.setHorizontalAlignment(SwingConstants.CENTER);
        startGame.setVerticalAlignment(SwingConstants.CENTER);
        startGame.setFont(new Font("Serif", Font.PLAIN, winHeight / 20));
        startGame.setForeground(Color.WHITE);
        startGame.setBackground(Color.LIGHT_GRAY);
        startGame.setOpaque(true);
        startGame.addActionListener(new ActionHandler());
        c.add(startGame);

        System.out.println("Initializing Main Menu with resolution " + winWidth + "x" + winHeight);
        mainMenu.setSize(winWidth, winHeight);
        mainMenu.add(new PaintComponent());
        mainMenu.setUndecorated(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);
    }

    private static class PaintComponent extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, winWidth, winHeight);
        }
    }

    private static class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startGame) {
                new StartGame();
            }
        }
    }
}
