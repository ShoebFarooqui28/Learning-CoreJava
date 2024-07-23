package Projects.TicTacToeGUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{
    
    //DECLARING VARIABLE INSTANCES
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    //CONSTRUCTOR
    TicTacToe(){
        //FRAME
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setTitle("Tic-Tac-Toe");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(0xff004d));
        
        //ICON
        ImageIcon imgicon = new ImageIcon("D:\\Wallpapers\\ticTacToe.png");
        frame.setIconImage(imgicon.getImage());

        //TITLE : TEXTFIELD
        textField.setBackground(new Color(0xececec));
        textField.setForeground(new Color(0x33030d));
        textField.setFont(new Font("Caviar Dreams",Font.BOLD, 60));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        //TITLE : PANEL
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0, 800, 100);

        //BUTTON : PANEL
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(0x33030d));

        //BUTTONS : ADDING THE BUTTONS IN PANEL
        for (int i = 0; i<9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Caviar Dreams",Font.BOLD, 120));
            buttons[i].setBackground(new Color(0x33030d));
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        //ADDING THE PANELS IN FRAME
        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);


        //REVALIDATING / UPDATING THE FRAME
        frame.revalidate();
        frame.repaint();
        
        //CALLING THE firstTurn() METHOD
        firstTurn();
    }   

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(Color.WHITE);
						buttons[i].setText("X");
						player1_turn = false;
						textField.setText("O's Turn");
						check();
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(Color.WHITE);
						buttons[i].setText("O");
						player1_turn = true;
						textField.setText("X's turn");
						check();
					}
				}
			}			
		}
	}

    public void firstTurn(){

        try {
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2) == 0){
            player1_turn = true;
            textField.setText("X's Turn");
        }
        else{
            player1_turn = false;
            textField.setText("O's Turn");
        }

    }

    public void check(){

        //CHECKING FOR X

        if ((buttons[0].getText() ==  "X") && 
            (buttons[1].getText() ==  "X") && 
            (buttons[2].getText() ==  "X")){
                
                xWins(0, 1, 2);
            }

        if ((buttons[3].getText() ==  "X") && 
            (buttons[4].getText() ==  "X") && 
            (buttons[5].getText() ==  "X")){
                
                xWins(3, 4, 5);
            }

        if ((buttons[6].getText() ==  "X") && 
            (buttons[7].getText() ==  "X") && 
            (buttons[8].getText() ==  "X")){
                
                xWins(6, 7, 8);
            }

        if ((buttons[0].getText() ==  "X") && 
            (buttons[3].getText() ==  "X") && 
            (buttons[6].getText() ==  "X")){
                
                xWins(0, 3, 6);
            }

        if ((buttons[1].getText() ==  "X") && 
            (buttons[4].getText() ==  "X") && 
            (buttons[7].getText() ==  "X")){
                
                xWins(1, 4, 7);
            }

        if ((buttons[2].getText() ==  "X") && 
            (buttons[5].getText() ==  "X") && 
            (buttons[8].getText() ==  "X")){
                
                xWins(2, 5, 8);
            }

        if ((buttons[0].getText() ==  "X") && 
            (buttons[4].getText() ==  "X") && 
            (buttons[8].getText() ==  "X")){
                
                xWins(0, 4, 8);
            }

        if ((buttons[2].getText() ==  "X") && 
            (buttons[4].getText() ==  "X") && 
            (buttons[6].getText() ==  "X")){
                
                xWins(2, 4, 6);
            }
        

        //CHECKING FOR O


        if ((buttons[0].getText() ==  "O") && 
            (buttons[1].getText() ==  "O") && 
            (buttons[2].getText() ==  "O")){
                
                oWins(0, 1, 2);
            }

        if ((buttons[3].getText() ==  "O") && 
            (buttons[4].getText() ==  "O") && 
            (buttons[5].getText() ==  "O")){
                
                oWins(3, 4, 5);
            }

        if ((buttons[6].getText() ==  "O") && 
            (buttons[7].getText() ==  "O") && 
            (buttons[8].getText() ==  "O")){
                
                oWins(6, 7, 8);
            }

        if ((buttons[0].getText() ==  "O") && 
            (buttons[3].getText() ==  "O") && 
            (buttons[6].getText() ==  "O")){
                
                oWins(0, 3, 6);
            }

        if ((buttons[1].getText() ==  "O") && 
            (buttons[4].getText() ==  "O") && 
            (buttons[7].getText() ==  "O")){
                
                oWins(1, 4, 7);
            }

        if ((buttons[2].getText() ==  "O") && 
            (buttons[5].getText() ==  "O") && 
            (buttons[8].getText() ==  "O")){
                
                oWins(2, 5, 8);
            }

        if ((buttons[0].getText() ==  "O") && 
            (buttons[4].getText() ==  "O") && 
            (buttons[8].getText() ==  "O")){
                
                oWins(0, 4, 8);
            }

        if ((buttons[2].getText() ==  "O") && 
            (buttons[4].getText() ==  "O") && 
            (buttons[6].getText() ==  "O")){
                
                oWins(2, 4, 6);
            }



    }   

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(new Color(0x42b883));
        buttons[b].setBackground(new Color(0x42b883));
        buttons[c].setBackground(new Color(0x42b883));

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("X Wins");
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(new Color(0x42b883));
        buttons[b].setBackground(new Color(0x42b883));
        buttons[c].setBackground(new Color(0x42b883));

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("O Wins");
    }
}
