import java.util.List;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run(){
                new WeatherAppGUI().setVisible(true);
                //System.out.println(WeatherBackEnd.getWeatherData("mumbai"));
                //System.out.println(WeatherBackEnd.getCurrentTimePlus24Hours());
                //String jsonArrayString = "[\"2024-07-25T15:00:00\", \"2024-07-26T15:00:00\", \"2024-07-28T15:00:00\", \"2024-07-30T15:00:00\"]";
                //JSONArray timeList = parseJsonArray(jsonArrayString);
                //WeatherBackEnd.findIndexofFiveDays(WeatherBackEnd.getNextFiveDaysTime(), timeList);
            } 
        });
    }
}
