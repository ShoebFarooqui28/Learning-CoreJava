package GUI.BasicGUI;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

    MyFrame(){

        this.setVisible(true);
        this.setSize(500,500);
        this.setTitle("HELLO!");
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("D:\\Wallpapers\\my2pc9ld387.png");
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(0x880808)); //used hex code for own new color
    
    }
}
