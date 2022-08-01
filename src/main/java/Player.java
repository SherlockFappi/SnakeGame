import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player {

    // variables
    public static JLabel player = new JLabel();
    public static short[] playerFollowerX = new short[1294];
    public static short[] playerFollowerY = new short[1294];

    boolean running = true;

    static String lastDir = "";

    public static ArrayList<JLabel> playerFollower = new ArrayList<>();

    ScheduledExecutorService movePlayer = Executors.newScheduledThreadPool(1);

    public Player() {
        for (short i = 0; i < 1293; i++) {
            playerFollower.add(i, new JLabel());
            playerFollower.get(i).setBounds(55 * Frame.widthStep, playerFollowerY[i] * Frame.heightStep, Frame.widthStep, Frame.heightStep);
            playerFollower.get(i).setBackground(Color.GREEN);
            playerFollower.get(i).setVisible(true);
            playerFollower.get(i).setOpaque(true);
        }

        player.setBounds(StartGame.playerPosX * Frame.widthStep, StartGame.playerPosY * Frame.heightStep, Frame.widthStep, Frame.heightStep);
        player.setBackground(new Color(0, 180, 0));
        player.setOpaque(true);

        movePlayer.scheduleAtFixedRate(move_player, 1000, 150, TimeUnit.MILLISECONDS);
    }

    Runnable move_player = () -> {
        if ((Objects.equals(StartGame.nextDir, "right")) && running) {
            StartGame.lastPlayerPosX = (short) (StartGame.playerPosX);
            StartGame.lastPlayerPosY = StartGame.playerPosY;
            StartGame.nextPlayerPosX = (short) (StartGame.playerPosX + 1);
            StartGame.nextPlayerPosY = StartGame.playerPosY;
            lastDir = "right";

            if (StartGame.nextPlayerPosX == 48) {
                StartGame.nextPlayerPosX = 0;
            }
        }

        if ((Objects.equals(StartGame.nextDir, "left")) && running) {
            StartGame.lastPlayerPosX = (short) (StartGame.playerPosX);
            StartGame.lastPlayerPosY = StartGame.playerPosY;
            StartGame.nextPlayerPosX = (short) (StartGame.playerPosX - 1);
            StartGame.nextPlayerPosY = StartGame.playerPosY;
            lastDir = "left";

            if (StartGame.nextPlayerPosX == -1) {
                StartGame.nextPlayerPosX = 47;
            }
        }

        if ((Objects.equals(StartGame.nextDir, "up")) && running) {
            StartGame.lastPlayerPosX = StartGame.playerPosX;
            StartGame.lastPlayerPosY = (short) (StartGame.playerPosY);
            StartGame.nextPlayerPosX = StartGame.playerPosX;
            StartGame.nextPlayerPosY = (short) (StartGame.playerPosY - 1);
            lastDir = "up";

            if (StartGame.nextPlayerPosY == 2) {
                StartGame.nextPlayerPosY = 26;
            }
        }

        if ((Objects.equals(StartGame.nextDir, "down")) && running) {
            StartGame.lastPlayerPosX = StartGame.playerPosX;
            StartGame.lastPlayerPosY = (short) (StartGame.playerPosY);
            StartGame.nextPlayerPosX = StartGame.playerPosX;
            StartGame.nextPlayerPosY = (short) (StartGame.playerPosY + 1);
            lastDir = "down";

            if (StartGame.nextPlayerPosY == 27) {
                StartGame.nextPlayerPosY = 3;
            }
        }

        player.setBounds(StartGame.nextPlayerPosX * Frame.widthStep, StartGame.nextPlayerPosY * Frame.heightStep, Frame.widthStep, Frame.heightStep);

        StartGame.playerPosX = StartGame.nextPlayerPosX;
        StartGame.playerPosY = StartGame.nextPlayerPosY;


        //Körperteile folgen dem Kopf
        if (StartGame.scoreCounter > 1) {

            for (int i = StartGame.scoreCounter - 1; i > 0 ; i--) {
                playerFollower.get(i).setBounds((int) (playerFollower.get(i-1).getBounds().getX()), (int) (playerFollower.get(i-1).getBounds().getY()), Frame.widthStep, Frame.heightStep);
            }

            playerFollower.get(0).setBounds(StartGame.lastPlayerPosX * Frame.widthStep, StartGame.lastPlayerPosY * Frame.heightStep, Frame.widthStep, Frame.heightStep);

        }else if (StartGame.scoreCounter == 1) {
            playerFollower.get(0).setBounds(StartGame.lastPlayerPosX * Frame.widthStep, StartGame.lastPlayerPosY * Frame.heightStep, Frame.widthStep, Frame.heightStep);
        }

        //Frucht fressen
        if (StartGame.playerPosX == Fruit.fruitPosX && StartGame.playerPosY == Fruit.fruitPosY) {
            Frame.score.setText("Punkte: " + String.valueOf(StartGame.scoreCounter+1));

            if (StartGame.scoreCounter > 0) {
                playerFollower.get(StartGame.scoreCounter).setBounds((int)(playerFollower.get(StartGame.scoreCounter - 1).getBounds().getX()) * Frame.widthStep, (int)(playerFollower.get(StartGame.scoreCounter - 1).getBounds().getY()) * Frame.heightStep, Frame.widthStep, Frame.heightStep);
            }
            else {
                playerFollower.get(0).setBounds(StartGame.playerPosX * Frame.widthStep, StartGame.playerPosY * Frame.heightStep, Frame.widthStep, Frame.heightStep);
            }

            StartGame.scoreCounter ++;
            System.out.println("Updating score to: " + StartGame.scoreCounter);
            new Fruit();
        }

        //Kollisionserkennung
        for (int i = 2; i < StartGame.scoreCounter; i++) {

            if (player.getX() == playerFollower.get(i).getX() && player.getY() == playerFollower.get(i).getY()) {
                Frame.score.setText("Game Over");
                if (running) {
                    System.out.println("Collision with " + i);
                    System.out.println("Game Over");
                }
                running = false;
            }
        }
    };
}
