import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class WeatherAppGUI extends JFrame{
    public WeatherAppGUI(){
        //Setting up the GUI.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setTitle("Simply Weather!");

        //Calling the iconTitle() method.   
        iconTitle();   

        //Calling the addGuiComponents() method.
        addGuiComponents();


    }

    //Declaring the properties of the GUI Components.
    private void addGuiComponents(){
        //Search Field
        JTextField searchBar = new JTextField();
        searchBar.setBounds(15, 15, 380, 60);
        searchBar.setBackground(new Color(0xc4bbf0));
        searchBar.setFont(new Font("COCOGOOSE", Font.BOLD, 28));
        searchBar.setForeground(new Color(0x4f3b78));
        searchBar.setBorder(null);
        add(searchBar);

        //Search Button
        JButton searchButton = new JButton(loadImage("src\\Assets\\search-icon.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(410, 15, 60, 60);
        searchButton.setBackground(Color.WHITE);
        searchButton.setBorder(null);
        add(searchButton);


    }
    
    //Icon & Title for the frame.
    private void iconTitle(){
        ImageIcon icon = new ImageIcon("src\\Assets\\weather-title-icon.jpg");
        setIconImage(icon.getImage());

        
    }
    //Creates images in our GUI Components
    private ImageIcon loadImage(String resourcePath){
    try {

        //Reads the image file from the given path
        BufferedImage image = ImageIO.read(new File(resourcePath));
        
        //Returns an image icon so that our component can render it
        return new ImageIcon(image);

    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("Could not find resuource");
    return null;
    }
}
