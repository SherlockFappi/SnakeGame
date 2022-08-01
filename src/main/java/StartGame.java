public class StartGame {

    // variables
    public static short playerPosX = 24;
    public static short playerPosY = 14;

    public static short nextPlayerPosX = playerPosX;
    public static short nextPlayerPosY = playerPosY;

    public static short lastPlayerPosX = playerPosX;
    public static short lastPlayerPosY = playerPosY;

    public static String nextDir;

    public static int scoreCounter = 0;

    public StartGame() {
        System.out.println("Starting Game...");
        new Frame();
        System.out.println("Starting Game... done");
    }

}
