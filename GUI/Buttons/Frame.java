package GUI.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame implements ActionListener{
    JButton button;
    Frame(){
        button = new JButton("Click me");
        button.setBounds(100,100,100,50);
        button.setFocusable(false);
        button.addActionListener(this);

        this.setLayout(null);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(button);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button) {
            System.out.println("Button Clicked");
        }
    }
}
