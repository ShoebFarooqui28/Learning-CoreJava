import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class WeatherAppGUI extends JFrame{
    public WeatherAppGUI(){
        //Setting up the GUI.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(500,800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setTitle("Simply Weather!");
        getContentPane().setBackground(new Color(0xc4bbf0));

        //Calling the iconTitle() method.   
        iconTitle();   

        //Calling the addGuiComponents() method.
        addGuiComponents();


    }

    //Declaring the properties of the GUI Components.
    public void addGuiComponents(){
        //Search Field
        RoundedTextField searchBar = new RoundedTextField(15);
        searchBar.setText("Enter Location");
        searchBar.setBounds(15, 15, 380, 60);
        searchBar.setBackground(new Color(0x927fbf));
        searchBar.setFont(new Font("Space Grotesk", Font.BOLD, 28));
        searchBar.setForeground(new Color(0x4f3b78));
        searchBar.setBorder(null);
        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event){
                if (searchBar.getText().equals("Enter Location")) {
                        searchBar.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent event){
                if (searchBar.getText().equals("")) {
                        searchBar.setText("Enter Location");
                }
            }   
        });
        add(searchBar);

        //Search Button
        JButton searchButton = new JButton(loadImage("src\\Assets\\search-icon.png",60,60));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(410, 15, 60, 60);
        searchButton.setBackground(new Color(0xc4bbf0));
        searchButton.setBorder(null);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        add(searchButton);

        //Weather Image
        JLabel weatherConditionImage = new JLabel(loadImage("src\\Assets\\cloudy-icon.png",256,256));
        weatherConditionImage.setBounds(115,160,256,256);
        add(weatherConditionImage);

        //Temperature Title
        JLabel tempText = new JLabel();
        tempText.setText("10° C");
        tempText.setBounds(145, 400, 200, 100);
        tempText.setForeground(new Color(0x363b4e));
        tempText.setHorizontalAlignment(JLabel.CENTER);
        tempText.setVerticalAlignment(JLabel.CENTER);
        tempText.setFont(new Font("Space Grotesk", Font.BOLD, 60));
        add(tempText);

        //Weather Condition Description
        JLabel weatherConditionDesc = new JLabel();
        weatherConditionDesc.setText("Cloudy");
        weatherConditionDesc.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        weatherConditionDesc.setBounds(145, 460, 200, 100);
        weatherConditionDesc.setForeground(new Color(0x363b4e));
        weatherConditionDesc.setHorizontalAlignment(JLabel.CENTER);
        weatherConditionDesc.setVerticalAlignment(JLabel.CENTER);
        add(weatherConditionDesc);

        //Humidity Icon
        JLabel humidityIcon = new JLabel(loadImage("src\\Assets\\humidity.png",60,60));
        humidityIcon.setBounds(110, 580, 60, 60);
        //humidityIcon.setBackground(Color.RED);
        //humidityIcon.setOpaque(true);
        add(humidityIcon);

        //Humidity Text
        JLabel humidityText = new JLabel();
        humidityText.setText("<html><b>Humidity</b> : 100%</html>");
        humidityText.setBounds(180, 580, 200, 60);
        humidityText.setFont(new Font("Space Grotesk", Font.PLAIN, 20));
        humidityText.setHorizontalAlignment(JLabel.CENTER);
        humidityText.setVerticalAlignment(JLabel.CENTER);
        //humidityText.setBackground(Color.GREEN);
        //humidityText.setOpaque(true);
        add(humidityText);        

        //Wind Icon
        JLabel windIcon = new JLabel(loadImage("src\\Assets\\wind.png",50, 50));
        windIcon.setBounds(110, 680,60,60);
        //windIcon.setBackground(Color.RED);
        //windIcon.setOpaque(true);
        add(windIcon);

        //Wind Text
        JLabel windText = new JLabel();
        windText.setText("<html><b>Wind</b> : 100 km/hr</html>");
        windText.setBounds(180, 680, 200, 60);
        windText.setFont(new Font("Space Grotesk", Font.PLAIN, 20));
        windText.setHorizontalAlignment(JLabel.CENTER);
        windText.setVerticalAlignment(JLabel.CENTER);
        //windText.setBackground(Color.GREEN);
        //windText.setOpaque(true);
        add(windText); 
    }
    
    //Icon & Title for the frame.
    private void iconTitle(){
        ImageIcon icon = new ImageIcon("src\\Assets\\weather-title-icon.jpg");
        setIconImage(icon.getImage());

        
    }
    
    //Creates images in our GUI Components
    private ImageIcon loadImage (String resourcePath, int width, int height){
    try {

        //Reads the image file from the given path
        BufferedImage image = ImageIO.read(new File(resourcePath));

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image.getScaledInstance(width, height, image.SCALE_SMOOTH), 0, 0, width, height, null);
        g2d.dispose();
        
        //Returns an image icon so that our component can render it
        return new ImageIcon(resizedImage);

    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("Could not find resuource");
    return null;
    }


}
