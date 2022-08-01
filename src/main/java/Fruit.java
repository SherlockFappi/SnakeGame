import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fruit {

    static JLabel fruit = new JLabel();
    Random random = new Random();
    public static byte fruitPosX;
    public static byte fruitPosY;

    public Fruit() {
        randomizeFood();

        fruit.setBounds(fruitPosX * Frame.widthStep, fruitPosY * Frame.heightStep, Frame.widthStep, Frame.heightStep);
        fruit.setBackground(Color.RED);
        fruit.setOpaque(true);
    }

    private void randomizeFood() {
        fruitPosX = (byte)random.nextInt(48);
        fruitPosY = (byte)(random.nextInt(24) + 3);
    }
}
