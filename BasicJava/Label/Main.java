package BasicJava.Label;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Main {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        // frame.setLayout(null);


        ImageIcon icon = new ImageIcon("D:\\Wallpapers\\photo.jpg");
        Border border = BorderFactory.createLineBorder(Color.ORANGE, 3);

        JLabel label = new JLabel();
        label.setText("Hello!");    
        label.setIcon(icon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0xFFC100));
        label.setFont(new Font("COCOGOOSE", Font.PLAIN, 20));
        label.setIconTextGap(10);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.add(label);
        frame.revalidate();
    }
    void scaleImage(){

    }
}
