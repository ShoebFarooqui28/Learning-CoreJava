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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

        JLabel locationName = new JLabel();
        locationName.setBounds(45, 90, 400, 60);
        locationName.setFont(new Font("Space Grotesk", Font.BOLD, 40)); 
        locationName.setText("Location");
        locationName.setHorizontalAlignment(JLabel.CENTER);
        locationName.setVerticalAlignment(JLabel.CENTER);
        add(locationName);

        JLabel weather_ConditionImage = new JLabel(loadImage("src\\Assets\\cloudy-icon.png", 256, 256));
        weather_ConditionImage.setBounds(115, 170, 256, 256);
        add(weather_ConditionImage);

        JLabel tempText = new JLabel();
        tempText.setText("-° C");
        tempText.setBounds(120, 410, 250, 100);
        tempText.setForeground(new Color(0x363b4e));
        tempText.setHorizontalAlignment(JLabel.CENTER);
        tempText.setVerticalAlignment(JLabel.CENTER);
        tempText.setFont(new Font("Space Grotesk", Font.BOLD, 60));
        add(tempText);

        JLabel weatherConditionDesc = new JLabel();
        weatherConditionDesc.setText("-");
        weatherConditionDesc.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        weatherConditionDesc.setBounds(145, 470, 200, 100);
        weatherConditionDesc.setForeground(new Color(0x363b4e));
        weatherConditionDesc.setHorizontalAlignment(JLabel.CENTER);
        weatherConditionDesc.setVerticalAlignment(JLabel.CENTER);
        add(weatherConditionDesc);

        JLabel humidityIcon = new JLabel(loadImage("src\\Assets\\humidity.png", 60, 60));
        humidityIcon.setBounds(110, 580, 60, 60);
        add(humidityIcon);

        JLabel humidityText = new JLabel();
        humidityText.setText("<html><b>Humidity</b> : -%</html>");
        humidityText.setBounds(180, 580, 200, 60);
        humidityText.setFont(new Font("Space Grotesk", Font.PLAIN, 20));
        humidityText.setForeground(new Color(0x363b4e));
        humidityText.setHorizontalAlignment(JLabel.CENTER);
        humidityText.setVerticalAlignment(JLabel.CENTER);
        add(humidityText);

        JLabel windIcon = new JLabel(loadImage("src\\Assets\\wind.png", 50, 50));
        windIcon.setBounds(110, 680, 60, 60);
        add(windIcon);

        JLabel windText = new JLabel();
        windText.setText("<html><b>Wind</b> : - km/hr</html>");
        windText.setBounds(180, 680, 200, 60);
        windText.setFont(new Font("Space Grotesk", Font.PLAIN, 20));
        windText.setForeground(new Color(0x363b4e));
        windText.setHorizontalAlignment(JLabel.CENTER);
        windText.setVerticalAlignment(JLabel.CENTER);
        add(windText);

        //Parent Panel
        RoundedPanel sidePanel = new RoundedPanel(15,15);
        sidePanel.setLayout(null);
        sidePanel.setBounds(515,15, 452,730);
        sidePanel.setBackground(new Color(0x927fbf));

        //Top Panel
        RoundedPanel topPanel = new RoundedPanel(15,15);
        topPanel.setLayout(null);
        topPanel.setBounds(15,15,422,100);
        topPanel.setBackground(new Color(0x363b4e));

        JLabel heading = new JLabel();
        heading.setText("5-Day Forecast");
        heading.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        heading.setBounds(59, 25, 300, 50);
        heading.setForeground(new Color(0xc4bbf0));
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setVerticalAlignment(JLabel.CENTER);
        topPanel.add(heading);

        
        //Child Panel 1
        RoundedPanel childPanel1 = new RoundedPanel(15,15);
        childPanel1.setLayout(null);
        childPanel1.setBounds(15,125+30,422,100);
        childPanel1.setBackground(new Color(0xc4bbf0));
        JLabel sideWeatherIcon1 = new JLabel(loadImage("src/Assets/cloudy-icon.png", 64,64));
        sideWeatherIcon1.setBounds(+15, 18, 64, 64);
        sideWeatherIcon1.setHorizontalAlignment(JLabel.CENTER);
        sideWeatherIcon1.setVerticalAlignment(JLabel.CENTER);
        JLabel sideDate1 = new JLabel();
        sideDate1.setBounds(94, 18, 235, 64);
        sideDate1.setText("-");
        sideDate1.setHorizontalAlignment(JLabel.CENTER);
        sideDate1.setVerticalAlignment(JLabel.CENTER);
        //sideDate1.setBackground(Color.RED);
        //sideDate1.setOpaque(true);
        sideDate1.setFont(new Font("Space Grotesk", Font.BOLD, 30));
        sideDate1.setForeground(new Color(0x363b4e));
        JLabel sideTemp1 = new JLabel();
        sideTemp1.setBounds(330, 18, 90, 64);
        sideTemp1.setText("-°");
        sideTemp1.setHorizontalAlignment(JLabel.CENTER);
        sideTemp1.setVerticalAlignment(JLabel.CENTER);
        //sideTemp1.setBackground(Color.RED);
        //sideTemp1.setOpaque(true);
        sideTemp1.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        sideTemp1.setForeground(new Color(0x363b4e));
        childPanel1.add(sideTemp1);
        

        
        //Child Panel 2
        RoundedPanel childPanel2 = new RoundedPanel(15,15);
        childPanel2.setLayout(null);
        childPanel2.setBounds(15,240+30,422,100);
        childPanel2.setBackground(new Color(0xc4bbf0));
        JLabel sideWeatherIcon2 = new JLabel(loadImage("src/Assets/cloudy-icon.png", 64,64));
        sideWeatherIcon2.setBounds(+15, 18, 64, 64);
        sideWeatherIcon2.setHorizontalAlignment(JLabel.CENTER);
        sideWeatherIcon2.setVerticalAlignment(JLabel.CENTER);
        JLabel sideDate2 = new JLabel();
        sideDate2.setBounds(94, 18, 235, 64);
        sideDate2.setText("-");
        sideDate2.setHorizontalAlignment(JLabel.CENTER);
        sideDate2.setVerticalAlignment(JLabel.CENTER);
        //sideDate2.setBackground(Color.RED);
        //sideDate2.setOpaque(true);
        sideDate2.setFont(new Font("Space Grotesk", Font.BOLD, 30));
        sideDate2.setForeground(new Color(0x363b4e));
        JLabel sideTemp2 = new JLabel();
        sideTemp2.setBounds(330, 18, 90, 64);
        sideTemp2.setText("-°");
        sideTemp2.setHorizontalAlignment(JLabel.CENTER);
        sideTemp2.setVerticalAlignment(JLabel.CENTER);
        //sideTemp2.setBackground(Color.RED);
        //sideTemp2.setOpaque(true);
        sideTemp2.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        sideTemp2.setForeground(new Color(0x363b4e));
      


        //Child Panel 3
        RoundedPanel childPanel3 = new RoundedPanel(15,15);
        childPanel3.setLayout(null);
        childPanel3.setBounds(15,355+30,422,100);
        childPanel3.setBackground(new Color(0xc4bbf0));
        JLabel sideWeatherIcon3 = new JLabel(loadImage("src/Assets/cloudy-icon.png", 64,64));
        sideWeatherIcon3.setBounds(+15, 18, 64, 64);
        sideWeatherIcon3.setHorizontalAlignment(JLabel.CENTER);
        sideWeatherIcon3.setVerticalAlignment(JLabel.CENTER);
        JLabel sideDate3 = new JLabel();
        sideDate3.setBounds(94, 18, 235, 64);
        sideDate3.setText("-");
        sideDate3.setHorizontalAlignment(JLabel.CENTER);
        sideDate3.setVerticalAlignment(JLabel.CENTER);
        //sideDate3.setBackground(Color.RED);
        //sideDate3.setOpaque(true);
        sideDate3.setFont(new Font("Space Grotesk", Font.BOLD, 30));
        sideDate3.setForeground(new Color(0x363b4e));
        JLabel sideTemp3 = new JLabel();
        sideTemp3.setBounds(330, 18, 90, 64);
        sideTemp3.setText("-°");
        sideTemp3.setHorizontalAlignment(JLabel.CENTER);
        sideTemp3.setVerticalAlignment(JLabel.CENTER);
        //sideTemp3.setBackground(Color.RED);
        //sideTemp3.setOpaque(true);
        sideTemp3.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        sideTemp3.setForeground(new Color(0x363b4e));


        //Child Panel 4
        RoundedPanel childPanel4 = new RoundedPanel(15,15);
        childPanel4.setLayout(null);
        childPanel4.setBounds(15,470+30,422,100);
        childPanel4.setBackground(new Color(0xc4bbf0));
        JLabel sideWeatherIcon4 = new JLabel(loadImage("src/Assets/cloudy-icon.png", 64,64));
        sideWeatherIcon4.setBounds(+15, 18, 64, 64);
        sideWeatherIcon4.setHorizontalAlignment(JLabel.CENTER);
        sideWeatherIcon4.setVerticalAlignment(JLabel.CENTER);
        JLabel sideDate4 = new JLabel();
        sideDate4.setBounds(94, 18, 235, 64);
        sideDate4.setText("-");
        sideDate4.setHorizontalAlignment(JLabel.CENTER);
        sideDate4.setVerticalAlignment(JLabel.CENTER);
        //sideDate4.setBackground(Color.RED);
        //sideDate4.setOpaque(true);
        sideDate4.setFont(new Font("Space Grotesk", Font.BOLD, 30));
        sideDate4.setForeground(new Color(0x363b4e));
        JLabel sideTemp4 = new JLabel();
        sideTemp4.setBounds(330, 18, 90, 64);
        sideTemp4.setText("-°");
        sideTemp4.setHorizontalAlignment(JLabel.CENTER);
        sideTemp4.setVerticalAlignment(JLabel.CENTER);
        //sideTemp4.setBackground(Color.RED);
        //sideTemp4.setOpaque(true);
        sideTemp4.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        sideTemp4.setForeground(new Color(0x363b4e));


        //Child Panel 5
        RoundedPanel childPanel5 = new RoundedPanel(15,15);
        childPanel5.setLayout(null);
        childPanel5.setBounds(15,585+30,422,100);
        childPanel5.setBackground(new Color(0xc4bbf0));
        JLabel sideWeatherIcon5 = new JLabel(loadImage("src/Assets/cloudy-icon.png", 64,64));
        sideWeatherIcon5.setBounds(+15, 18, 64, 64);
        sideWeatherIcon5.setHorizontalAlignment(JLabel.CENTER);
        sideWeatherIcon5.setVerticalAlignment(JLabel.CENTER);
        JLabel sideDate5 = new JLabel();
        sideDate5.setBounds(94, 18, 235, 64);
        sideDate5.setText("-");
        sideDate5.setHorizontalAlignment(JLabel.CENTER);
        sideDate5.setVerticalAlignment(JLabel.CENTER);
        //sideDate5.setBackground(Color.RED);
        //sideDate5.setOpaque(true);
        sideDate5.setFont(new Font("Space Grotesk", Font.BOLD, 30));
        sideDate5.setForeground(new Color(0x363b4e));
        JLabel sideTemp5 = new JLabel();
        sideTemp5.setBounds(330, 18, 90, 64);
        sideTemp5.setText("-    °");
        sideTemp5.setHorizontalAlignment(JLabel.CENTER);
        sideTemp5.setVerticalAlignment(JLabel.CENTER);
        //sideTemp5.setBackground(Color.RED);
        //sideTemp5.setOpaque(true);
        sideTemp5.setFont(new Font("Space Grotesk", Font.BOLD, 40));
        sideTemp5.setForeground(new Color(0x363b4e));


        childPanel1.add(sideTemp1);
        childPanel2.add(sideTemp2);
        childPanel3.add(sideTemp3);
        childPanel4.add(sideTemp4);
        childPanel5.add(sideTemp5);

        childPanel1.add(sideDate1);
        childPanel2.add(sideDate2);
        childPanel3.add(sideDate3);
        childPanel4.add(sideDate4);
        childPanel5.add(sideDate5);

        childPanel1.add(sideWeatherIcon1);
        childPanel2.add(sideWeatherIcon2);
        childPanel3.add(sideWeatherIcon3);
        childPanel4.add(sideWeatherIcon4);
        childPanel5.add(sideWeatherIcon5);

        sidePanel.add(topPanel);
        sidePanel.add(childPanel1);
        sidePanel.add(childPanel2);
        sidePanel.add(childPanel3);
        sidePanel.add(childPanel4);
        sidePanel.add(childPanel5);
        add(sidePanel);

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
                tempText.setText(temperature+" °C");

                weatherConditionDesc.setText(weatherCondition);

                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b> : " + humidity + "%</html>");

                double wind = (double) weatherData.get("windSpeed");
                windText.setText("<html><b>Wind</b> : " + wind + " km/hr</html>");

                //SideData 1
                int tempday1 = (int) weatherData.get("sideTemperature1");
                sideTemp1.setText(tempday1+"°");
                
                //SideData 2
                int tempday2 = (int) weatherData.get("sideTemperature2");
                sideTemp2.setText(tempday2+"°");

                //SideData 3
                int tempday3 = (int) weatherData.get("sideTemperature3");
                sideTemp3.setText(tempday3+"°");

                //SideData 4
                int tempday4 = (int) weatherData.get("sideTemperature4");
                sideTemp4.setText(tempday4+"°");

                //SideData 3
                int tempday5 = (int) weatherData.get("sideTemperature5");
                sideTemp5.setText(tempday5+"°");

                //Condition day 1
                String sideWeatherCondition1 = (String) weatherData.get("sideWeatherConditionkey1");
                if (sideWeatherCondition1 == null) {
                    System.out.println("Error: Weather condition is null.");
                    return;
                }
                switch (sideWeatherCondition1) {
                    case "Clear":
                    sideWeatherIcon1.setIcon(loadImage("src\\Assets\\clear-icon.png", 64, 64));
                        break;
                    case "Cloudy":
                    sideWeatherIcon1.setIcon(loadImage("src\\Assets\\cloudy-icon.png", 64, 64));
                        break;
                    case "Rainy":
                    sideWeatherIcon1.setIcon(loadImage("src\\Assets\\rainy-icon.png", 64, 64));
                        break;
                    case "Snow":
                    sideWeatherIcon1.setIcon(loadImage("src\\Assets\\snow-icon.png", 64, 64));
                        break;
                    default:
                    sideWeatherIcon1.setIcon(loadImage("src\\Assets\\unknown-icon.png", 64, 64));
                        break;
                }

                //Condition day 2
                String sideWeatherCondition2 = (String) weatherData.get("sideWeatherConditionkey2");
                if (sideWeatherCondition2 == null) {
                    System.out.println("Error: Weather condition is null.");
                    return;
                }
                switch (sideWeatherCondition2) {
                    case "Clear":
                    sideWeatherIcon2.setIcon(loadImage("src\\Assets\\clear-icon.png", 64, 64));
                        break;
                    case "Cloudy":
                    sideWeatherIcon2.setIcon(loadImage("src\\Assets\\cloudy-icon.png", 64, 64));
                        break;
                    case "Rainy":
                    sideWeatherIcon2.setIcon(loadImage("src\\Assets\\rainy-icon.png", 64, 64));
                        break;
                    case "Snow":
                    sideWeatherIcon2.setIcon(loadImage("src\\Assets\\snow-icon.png", 64, 64));
                        break;
                    default:
                    sideWeatherIcon2.setIcon(loadImage("src\\Assets\\unknown-icon.png", 64, 64));
                        break;
                }

                //Condition day 3
                String sideWeatherCondition3 = (String) weatherData.get("sideWeatherConditionkey3");
                if (sideWeatherCondition3 == null) {
                    System.out.println("Error: Weather condition is null.");
                    return;
                }
                switch (sideWeatherCondition3) {
                    case "Clear":
                    sideWeatherIcon3.setIcon(loadImage("src\\Assets\\clear-icon.png", 64, 64));
                        break;
                    case "Cloudy":
                    sideWeatherIcon3.setIcon(loadImage("src\\Assets\\cloudy-icon.png", 64, 64));
                        break;
                    case "Rainy":
                    sideWeatherIcon3.setIcon(loadImage("src\\Assets\\rainy-icon.png", 64, 64));
                        break;
                    case "Snow":
                    sideWeatherIcon3.setIcon(loadImage("src\\Assets\\snow-icon.png", 64, 64));
                        break;
                    default:
                    sideWeatherIcon3.setIcon(loadImage("src\\Assets\\unknown-icon.png", 64, 64));
                        break;
                }

                //Condition day 4
                String sideWeatherCondition4 = (String) weatherData.get("sideWeatherConditionkey4");
                if (sideWeatherCondition4 == null) {
                    System.out.println("Error: Weather condition is null.");
                    return;
                }
                switch (sideWeatherCondition4) {
                    case "Clear":
                    sideWeatherIcon4.setIcon(loadImage("src\\Assets\\clear-icon.png", 64, 64));
                        break;
                    case "Cloudy":
                    sideWeatherIcon4.setIcon(loadImage("src\\Assets\\cloudy-icon.png", 64, 64));
                        break;
                    case "Rainy":
                    sideWeatherIcon4.setIcon(loadImage("src\\Assets\\rainy-icon.png", 64, 64));
                        break;
                    case "Snow":
                    sideWeatherIcon4.setIcon(loadImage("src\\Assets\\snow-icon.png", 64, 64));
                        break;
                    default:
                    sideWeatherIcon4.setIcon(loadImage("src\\Assets\\unknown-icon.png", 64, 64));
                        break;
                }

                //Condition day 5
                String sideWeatherCondition5 = (String) weatherData.get("sideWeatherConditionkey5");
                if (sideWeatherCondition5 == null) {
                    System.out.println("Error: Weather condition is null.");
                    return;
                }
                switch (sideWeatherCondition5) {
                    case "Clear":
                    sideWeatherIcon5.setIcon(loadImage("src\\Assets\\clear-icon.png", 64, 64));
                        break;
                    case "Cloudy":
                    sideWeatherIcon5.setIcon(loadImage("src\\Assets\\cloudy-icon.png", 64, 64));
                        break;
                    case "Rainy":
                    sideWeatherIcon5.setIcon(loadImage("src\\Assets\\rainy-icon.png", 64, 64));
                        break;
                    case "Snow":
                    sideWeatherIcon5.setIcon(loadImage("src\\Assets\\snow-icon.png", 64, 64));
                        break;
                    default:
                    sideWeatherIcon5.setIcon(loadImage("src\\Assets\\unknown-icon.png", 64, 64));
                        break;
                }

                String[] daysOfWeek = WeatherBackEnd.getnextDay(WeatherBackEnd.getNextFiveDaysTime());
                sideDate1.setText(daysOfWeek[0]);
                sideDate2.setText(daysOfWeek[1]);
                sideDate3.setText(daysOfWeek[2]);
                sideDate4.setText(daysOfWeek[3]);
                sideDate5.setText(daysOfWeek[4]);

                locationName.setText(WeatherBackEnd.printName(userInput));

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
