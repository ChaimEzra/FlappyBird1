package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static FlappyBird.Window.getWindowWidth;

public class GameSans extends JPanel implements KeyListener {


    private final ResetManager RESET_MANAGER;
    private static final int BIRD_HIGH = 30;
    private static final int BIRD_WIDTH = 40;
    private final int PIPE_DISTANCE = 223;
    private final int GRAVITY_SPEED = 40;
    private final int GRAVITY_UP = -9;
    private final int GROUND_HEIGHT = 100;

    private  int skyHeight;
    private static int  bird_y;
    private int gravity;
    private int bestScore = 0 ;
    private Pipes first_pipe;
    private Pipes second_pipe;
    private int passageCounter = 0;
    

    private static int bird_x = getWindowWidth() / 2- BIRD_WIDTH /2;

    
    private final Image image = Toolkit.getDefaultToolkit().createImage(".idea\\image1\\MyGif.gif");
    private final Image imageBackground = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\backgraund.jpg");


    

    public static int getBirdX() {
        return bird_x;
    }

    public GameSans() {

         bird_y = getHeight() / 2;
         gravity = 0;

        this.first_pipe = new Pipes(0, 0, WIDTH, skyHeight);
        this.second_pipe = new Pipes(PIPE_DISTANCE , 0, WIDTH, skyHeight);

        RESET_MANAGER = new ResetManager(this);
        
        setFocusable(true);
        addKeyListener(this);

        new Thread(() -> {
            while (true) {

                Rectangle lowerPips = this.first_pipe.calculateLowerPips();
                Rectangle upperPips = this.first_pipe.calculateUpperPipes();
                Rectangle lowerPips1 = this.second_pipe.calculateLowerPips();
                Rectangle upperPips1 = this.second_pipe.calculateUpperPipes();

                gravity++;
                this.bird_y += gravity;

                if ( bird_y > getHeight() - GROUND_HEIGHT- BIRD_HIGH) {
                    RESET_MANAGER.resetGame();
                }
                if ( bird_y < 0) {
                    RESET_MANAGER.resetGame();
                }
                if (Utils.checkCollision(lowerPips,upperPips,calculateBird()) || Utils.checkCollision(lowerPips1,upperPips1,calculateBird())) {
                   RESET_MANAGER.resetGame();
                }

                repaint();
                Utils.sleep(GRAVITY_SPEED);
            }
        }).start();


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageBackground, 0, 0, getWidth(), getHeight() - GROUND_HEIGHT, this);

        first_pipe.paint(g);
        second_pipe.paint(g);

        g.setColor(Color.orange);
        g.drawImage(image, bird_x,  bird_y, BIRD_WIDTH, BIRD_HIGH, this);

        g.fillRect(0, getHeight() - GROUND_HEIGHT, getWidth(), GROUND_HEIGHT);

        Font font = new Font("skyHeight", Font.BOLD, 25);
        g.setFont(font);
        g.setColor(Color.white);
        if (bestScore < Pipes.getPassageCounter()){
            bestScore =  Pipes.getPassageCounter();
        }
        String scoreText = "Score: " + Pipes.getPassageCounter();
        String bestScoreText = "Best Score: " + bestScore ;


        g.drawString(scoreText, 10, 30);
        g.drawString(bestScoreText, 10, 50);
        
    }

    public static Rectangle calculateBird () {
        return new Rectangle(bird_x,  bird_y, BIRD_WIDTH, BIRD_HIGH -1);

    }
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE||e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W) {
            gravity = GRAVITY_UP;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void resetVariables() {
         bird_y = getHeight() / 2;
        gravity = 0;
        Pipes.setPassageCounter(0);
    }


    void resetPipes() {
         first_pipe.pipe_x=getWindowWidth();
         second_pipe.pipe_x=getWindowWidth()+PIPE_DISTANCE ;
    }
}
