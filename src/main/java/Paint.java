import javax.swing.*;
import java.awt.*;

public class Paint extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);

        for (byte width = 1; width < 49; width++) {
            g.drawLine(width * Frame.widthStep, 0, width * Frame.widthStep, Frame.winHeight);
        }

        for (byte height = 3; height < 30; height++) {
            g.drawLine(0, height * Frame.heightStep, Frame.winWidth, height * Frame.heightStep);
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Frame.winWidth, 3 * Frame.heightStep);
    }
}
