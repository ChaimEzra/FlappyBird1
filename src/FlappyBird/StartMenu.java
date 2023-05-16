package FlappyBird;

import javax.swing.*;


public class StartMenu extends JFrame {


     private ImageIcon buttonIcon = new ImageIcon(".idea\\image1\\button_Img.jpg");
     private final ImageIcon LOGO_ICON = new ImageIcon(".idea\\image1\\logo.jpeg");


     public StartMenu(){

          this.setSize(Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT);
          this.setDefaultCloseOperation(EXIT_ON_CLOSE);
          this.setResizable(false);
          this.setTitle("Flappy bird");
          this.setLocationRelativeTo(null);
          this.setLayout(null);
          this.setIconImage(LOGO_ICON.getImage());
          Background background = new Background(Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT);




          JButton start = new JButton(buttonIcon);
          this.add(background);
          start.setBounds(Window.WINDOW_WIDTH / 2 -100,Window.WINDOW_HEIGHT / 2 - 150,200,100);
          start.setBorderPainted(true);



          start.addActionListener((event) -> {
               SwingUtilities.invokeLater(Window::new);
               this.setVisible(false);
          });

          this.add(start);
          this.setVisible(true);
     }


}
