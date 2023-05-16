package FlappyBird;

import javax.swing.*;

public class Window extends JFrame {


    public static final int WINDOW_WIDTH = 375;
    public static final int WINDOW_HEIGHT = 600;

    private final ImageIcon LOGO_ICON = new ImageIcon(".idea\\image1\\logo.jpeg");

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }
    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public Window(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setTitle("Flappy bird");
        this.setLocationRelativeTo(null);
        this.setIconImage(LOGO_ICON.getImage());

        GameSans background = new GameSans();
        add(background);



    }



}
