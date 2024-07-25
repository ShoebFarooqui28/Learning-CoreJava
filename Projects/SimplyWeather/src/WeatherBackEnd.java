import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpConnectTimeoutException;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherBackEnd {

    //Fetching weather data from the API method.
    public static JSONObject getWeatherData(String locationName){
        //Fetching location Co-ordinates using the Geolocation API.
        JSONArray locationData = getLocationData(locationName);
        if (locationData == null) {
            System.out.println("Location not entered.");
        }
        
        //Extracting latitude and longitude data.
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        //Building Weather API request url with location co-ordinates
        String urlString = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&timezone=GMT";
        try {

            //Calling API and getting a response.
            HttpURLConnection conn = fetchAPIResponse(urlString);

            //Checking response status.
            //200 means SUCCESSFULL.
            if(conn.getResponseCode() != 200){
                System.out.println("Error : Could not connect the Weather API");
                return null;
            }
            else{
                //Storing the API results.
                StringBuilder resultJSON = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                //Readind and Storing the API result into StringBuilder.
                while(scanner.hasNext()){
                    resultJSON.append(scanner.nextLine());
                }

                //Closing the scanner.
                scanner.close();

                //Closing url connection.
                conn.disconnect();

                //Parsing the JSON String to JSON Object.
                JSONParser parser = new JSONParser();
                JSONObject resultsJSONObject = (JSONObject) parser.parse(resultJSON.toString());


                //Retrieving Hourly Data.
                JSONObject hourlyData = (JSONObject) resultsJSONObject.get("hourly");

                //Getting the index of the current hour.
                JSONArray timeList = (JSONArray) hourlyData.get("time");
                int currentIndex = findIndex(timeList);

                //Getting Temperature
                JSONArray temperatureData = (JSONArray) hourlyData.get("temperature_2m");
                double temperature = (double) temperatureData.get(currentIndex);

                //Getting Weather Code
                JSONArray weatherCode = (JSONArray) hourlyData.get("weather_code");
                String weatherCondition = convertweatherCode((long)weatherCode.get(currentIndex));

                //Getting Humidity
                JSONArray humidityData = (JSONArray) hourlyData.get("relative_humidity_2m");
                long humidity = (long) humidityData.get(currentIndex);

                //Getting Wind Speed
                JSONArray windData = (JSONArray) hourlyData.get("wind_speed_10m");
                double windSpeed = (double) windData.get(currentIndex);

                //Building the weather JSON Data Object that will used to access these datas in the front end.
                JSONObject weatherData = new JSONObject();
                weatherData.put("temperature", temperature);
                weatherData.put("weatherCondition", weatherCondition);
                weatherData.put("humidity", humidity);
                weatherData.put("windSpeed", windSpeed);
                
                return weatherData;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Conversion of Weather Code 
    private static String convertweatherCode(long weatherCode) {
        String weatherCondition = "";
        if (weatherCode == 0L) {
            weatherCondition = "Clear";
        }

        else if(weatherCode <= 3L && weatherCode > 0L ){
            weatherCondition = "Cloudy";
        }

        else if ((weatherCode >= 51L && weatherCode <= 67L) || (weatherCode >= 80 && weatherCode <=99)){
            weatherCondition = "Rainy";
        }
        
        else if(weatherCode >= 71L && weatherCode <= 77L ){
            weatherCondition = "Snow";
        }

        return weatherCondition;
    }

    //Retrieving geographic co-ordinates from the given location method.
    public static JSONArray getLocationData(String locationName) {
        
        //Replacing whitespaces to "+" as format of API url.
        locationName = locationName.replaceAll(" ", "+");

        //Building Geo API Url with the location parameter.
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name="+locationName+"&count=10&language=en&format=json";
        try {

            //Calling API and getting the Co-ordiantes.
            HttpURLConnection conn = fetchAPIResponse(urlString);

            //Checking response status.
            //200 means SUCCESSFULL.
            if(conn.getResponseCode() != 200){
                System.out.println("Error : Could not connect the GeoLocation API");
                return null;
            }
            else{
                //Storing the API results.
                StringBuilder resultJSON = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                //Readind and Storing the API result into StringBuilder.
                while(scanner.hasNext()){
                    resultJSON.append(scanner.nextLine());
                }

                //Closing the scanner.
                scanner.close();

                //Closing url connection.
                conn.disconnect();

                //Parsing the JSON String to JSON Object.
                JSONParser parser = new JSONParser();
                JSONObject resultsJSONObject = (JSONObject) parser.parse(String.valueOf(resultJSON));

                //Getting the list of location data that the API Generated from the given name.
                JSONArray locData = (JSONArray) resultsJSONObject.get("results");

                return locData;

            }

        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }
    
    //Fetching API response method.
    public static HttpURLConnection fetchAPIResponse(String urlString){
        try {

            //Attempting to create connections.
            URL url = URI.create(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //Setting the request method to get the data.
            conn.setRequestMethod("GET");

            //Connecting to our API
            conn.connect();
            return conn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        //If the connection is not made
        return null;

    }

    //Finding Current index of the Current Date and Time method.
    public static int findIndex(JSONArray timeList){
        String currentTime = getCurrentTime();

        //Iterating through the Time List and matching our time to the one in time list
        for(int i = 0; i < timeList.size(); i++){
            String time = (String) timeList.get(i);
            if (time.equalsIgnoreCase(currentTime)) {
                //Return the index
                return i;
            }
        }
        return 0;
    }

    //Getting the Current Time
    public static String getCurrentTime() {
        //Getting Current Date and Time
        LocalDateTime currentDateTime = LocalDateTime.now();

        //Formatting date and time to (Year-Month-DayTtime)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");  

        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }
}
