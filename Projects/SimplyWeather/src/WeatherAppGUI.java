// WeatherAppGUI.java
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

public class WeatherAppGUI extends JFrame {
    private JSONObject weatherData;

    public WeatherAppGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setTitle("Simply Weather!");
        getContentPane().setBackground(new Color(0xc4bbf0));

        iconTitle();
        addGuiComponents();
    }

    public void addGuiComponents() {
        RoundedTextField searchBar = new RoundedTextField(15);
        searchBar.setText("Enter Location");
        searchBar.setBounds(15, 15, 380, 60);
        searchBar.setBackground(new Color(0x927fbf));
        searchBar.setFont(new Font("Space Grotesk", Font.BOLD, 28));
        searchBar.setForeground(new Color(0x4f3b78));
        searchBar.setBorder(null);
        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (searchBar.getText().equals("Enter Location")) {
                    searchBar.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (searchBar.getText().equals("")) {
                    searchBar.setText("Enter Location");
                }
            }
        });
        add(searchBar);

        JLabel weather_ConditionImage = new JLabel(loadImage("src\\Assets\\cloudy-icon.png", 256, 256));
        weather_ConditionImage.setBounds(115, 160, 256, 256);
        add(weather_ConditionImage);

        JLabel tempText = new JLabel();
        tempText.setText("10° C");
        tempText.setBounds(145, 400, 200, 100);
        tempText.setForeground(new Color(0x363b4e));
        tempText.setHorizontalAlignment(JLabel.CENTER);
        tempText.setVerticalAlignment(JLabel.CENTER);
        tempText.setFont(new Font("Space Grotesk", Font.BOLD, 60));
        add(tempText);

        JLabel weatherConditionDesc = new JLabel();
        weatherConditionDesc.setText("Cloudy");
        weatherConditionDesc.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        weatherConditionDesc.setBounds(145, 460, 200, 100);
        weatherConditionDesc.setForeground(new Color(0x363b4e));
        weatherConditionDesc.setHorizontalAlignment(JLabel.CENTER);
        weatherConditionDesc.setVerticalAlignment(JLabel.CENTER);
        add(weatherConditionDesc);

        JLabel humidityIcon = new JLabel(loadImage("src\\Assets\\humidity.png", 60, 60));
        humidityIcon.setBounds(110, 580, 60, 60);
        add(humidityIcon);

        JLabel humidityText = new JLabel();
        humidityText.setText("<html><b>Humidity</b> : 100%</html>");
        humidityText.setBounds(180, 580, 200, 60);
        humidityText.setFont(new Font("Space Grotesk", Font.PLAIN, 20));
        humidityText.setHorizontalAlignment(JLabel.CENTER);
        humidityText.setVerticalAlignment(JLabel.CENTER);
        add(humidityText);

        JLabel windIcon = new JLabel(loadImage("src\\Assets\\wind.png", 50, 50));
        windIcon.setBounds(110, 680, 60, 60);
        add(windIcon);

        JLabel windText = new JLabel();
        windText.setText("<html><b>Wind</b> : 100 km/hr</html>");
        windText.setBounds(180, 680, 200, 60);
        windText.setFont(new Font("Space Grotesk", Font.PLAIN, 20));
        windText.setHorizontalAlignment(JLabel.CENTER);
        windText.setVerticalAlignment(JLabel.CENTER);
        add(windText);

        JPanel sidepanel = new JPanel();
        sidepanel.add(day1)

        JButton searchButton = new JButton(loadImage("src\\Assets\\search-icon.png", 60, 60));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(410, 15, 60, 60);
        searchButton.setBackground(new Color(0xc4bbf0));
        searchButton.setBorder(null);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchBar.getText();
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                weatherData = WeatherBackEnd.getWeatherData(userInput);
                if (weatherData == null) {
                    System.out.println("Error: Unable to fetch weather data.");
                    return;
                }

                String weatherCondition = (String) weatherData.get("weatherCondition");
                if (weatherCondition == null) {
                    System.out.println("Error: Weather condition is null.");
                    return;
                }

                switch (weatherCondition) {
                    case "Clear":
                        weather_ConditionImage.setIcon(loadImage("src\\Assets\\clear-icon.png", 256, 256));
                        break;
                    case "Cloudy":
                        weather_ConditionImage.setIcon(loadImage("src\\Assets\\cloudy-icon.png", 256, 256));
                        break;
                    case "Rainy":
                        weather_ConditionImage.setIcon(loadImage("src\\Assets\\rainy-icon.png", 256, 256));
                        break;
                    case "Snow":
                        weather_ConditionImage.setIcon(loadImage("src\\Assets\\snow-icon.png", 256, 256));
                        break;
                    default:
                        weather_ConditionImage.setIcon(loadImage("src\\Assets\\unknown-icon.png", 256, 256));
                        break;
                }

                double temperature = (double) weatherData.get("temperature");
                tempText.setText(temperature+" C");

                weatherConditionDesc.setText(weatherCondition);

                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b> : " + humidity + "%</html>");

                double wind = (double) weatherData.get("windSpeed");
                windText.setText("<html><b>Wind</b> : " + wind + " km/hr</html>");
            }
        });
        add(searchButton);
    }

    private void iconTitle() {
        ImageIcon icon = new ImageIcon("src\\Assets\\weather-title-icon.jpg");
        setIconImage(icon.getImage());
    }

    private ImageIcon loadImage(String resourcePath, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);
            g2d.dispose();
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not find resource");
        return null;
    }

}
