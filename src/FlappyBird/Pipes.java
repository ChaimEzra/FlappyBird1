package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static FlappyBird.Window.getWindowHeight;
import static FlappyBird.Window.getWindowWidth;

public class Pipes extends JPanel {

    private final Image UP_PIPE = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\daune.png");
    private final Image DOWN_PIPE = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\UP.png");
    private final int PIPE_SPEED = 9;
    private final int WIDTH = 70;


    private static int passageCounter = 0;
    public int pipe_x;
    public boolean witchPips = true;
    private int floorHeight;
    private int skyHeight;


    Random random = new Random();



    public static int getPassageCounter() {
        return passageCounter;
    }



    public static void setPassageCounter(int passageCounter) {
        Pipes.passageCounter = passageCounter;
    }

    public Pipes(int pipeX, int i, int WIDTH, int skyHeight) {

        this.pipe_x = getWindowWidth() + pipeX;

        new Thread(() -> {
            while (true) {
                this.pipe_x--;

                if (this.pipe_x < -this.WIDTH) {
                    this.pipe_x = getWindowWidth();
                    this.skyHeight = random.nextInt(351 - 50) + 50;  // Random height between 50 and 350
                    floorHeight = 350 - this.skyHeight;
                }


                if (this.pipe_x == GameSans.getBirdX()) {
                    if (witchPips) {
                        passageCounter++;
                    }
                }

                Utils.sleep(PIPE_SPEED);
            }
        }).start();
    }

    public void paint(Graphics g) {
        g.drawImage(DOWN_PIPE, pipe_x, getWindowHeight() - 137 - floorHeight, WIDTH, floorHeight, this);
        g.drawImage(UP_PIPE, pipe_x, 0, WIDTH, skyHeight, this);

    }

    public Rectangle calculateLowerPips() {
        return new Rectangle(this.pipe_x, getWindowHeight() - 137 - floorHeight, WIDTH, floorHeight);
    }

    public Rectangle calculateUpperPipes() {
        return new Rectangle(this.pipe_x, 0, WIDTH, skyHeight);
    }






}
