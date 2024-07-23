package CoreJava.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(0, 0, 150, 150);
        bluePanel.setLayout(new BorderLayout());

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setBounds(150, 0, 150, 150);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(0, 150, 300, 150);

        ImageIcon icon = new ImageIcon("CoreJava\\Panels\\photo.jpg");

        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        //JFRAME
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        bluePanel.add(label);
        frame.add(bluePanel);
        frame.add(redPanel);
        frame.add(greenPanel);
        


    }
}
