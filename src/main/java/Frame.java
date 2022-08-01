import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Frame extends JFrame {

    // variables
    public static final short winWidth = (short) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final short winHeight = (short) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static final byte widthStep = (byte) (winWidth / 48);
    public static final byte heightStep = (byte) (winHeight / 27);
    public static JLabel score = new JLabel();

    public final Container c = getContentPane();

    ScheduledExecutorService repaint = Executors.newScheduledThreadPool(1);

    // Frame initialization
    public Frame () {
        System.out.println("Initializing Frame...");

        System.out.println("Initializing Player...");
        new Player();
        System.out.println("Initializing Player... done");

        System.out.println("Initializing Fruits...");
        new Fruit();
        System.out.println("Initializing Fruits... done");

        System.out.println("Adding Player...");
        this.add(Player.player);
        System.out.println("Adding Player... done");

        for (short i = 0; i < 1293; i++) {
            this.add(Player.playerFollower.get(i));
        }

        score.setText("Punkte: 0" );
        score.setBounds(winWidth / 25, winHeight / 38, winWidth / 3, winHeight / 14);
        score.setFont(new Font("Serif", Font.BOLD, winWidth / 30));

        this.add(score);

        System.out.println("Adding Fruits...");
        this.add(Fruit.fruit);
        System.out.println("Adding Fruits... done");

        this.setSize(winWidth, winHeight);
        System.out.println("Initializing Painter...");
        this.add(new Paint());
        System.out.println("Initializing Painter... done");
        this.setBackground(Color.DARK_GRAY);
        System.out.println("Initializing KeyHandler...");
        this.addKeyListener(new KeyHandler());
        System.out.println("Initializing KeyHandler... done");
        this.setUndecorated(true);
        this.setVisible(true);

        repaint.scheduleAtFixedRate(re_paint, 0, 1, TimeUnit.MILLISECONDS);

        System.out.println("Initializing Frame... done");
    }

    Runnable re_paint = this::repaint;
}
