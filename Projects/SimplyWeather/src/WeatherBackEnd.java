import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherBackEnd {

    //Fetching weather data from the API method.
    @SuppressWarnings("unchecked")
    public static JSONObject getWeatherData(String locationName){
        //Fetching location Co-ordinates using the Geolocation API.
        JSONArray locationData = getLocationData(locationName);
        if (locationData == null) {
            System.out.println("Location data could not be fetched");
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
                StringBuilder resultJSONString = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                //Readind and Storing the API result into StringBuilder.
                while(scanner.hasNext()){
                    resultJSONString.append(scanner.nextLine());
                }

                //Closing the scanner.
                scanner.close();

                //Closing url connection.
                conn.disconnect();

                //Parsing the JSON String to JSON Object.
                JSONParser parser = new JSONParser();
                JSONObject resultsJSONObject = (JSONObject) parser.parse(resultJSONString.toString());


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

                //Today
                weatherData.put("temperature", temperature);
                weatherData.put("weatherCondition", weatherCondition);
                weatherData.put("humidity", humidity);
                weatherData.put("windSpeed", windSpeed);

                //Next 5 days Temps
                weatherData.put("sideTemperature1", getTemperatureForDay(hourlyData,0));
                weatherData.put("sideTemperature2", getTemperatureForDay(hourlyData,1));
                weatherData.put("sideTemperature3", getTemperatureForDay(hourlyData,2));
                weatherData.put("sideTemperature4", getTemperatureForDay(hourlyData,3));
                weatherData.put("sideTemperature5", getTemperatureForDay(hourlyData,4));

                //Next 5 days Condition
                weatherData.put("sideWeatherConditionkey1", (convertSideWeatherCodetoCondition(getSideWeatherCode(hourlyData, 0))));
                weatherData.put("sideWeatherConditionkey2", (convertSideWeatherCodetoCondition(getSideWeatherCode(hourlyData, 1))));
                weatherData.put("sideWeatherConditionkey3", (convertSideWeatherCodetoCondition(getSideWeatherCode(hourlyData, 2))));
                weatherData.put("sideWeatherConditionkey4", (convertSideWeatherCodetoCondition(getSideWeatherCode(hourlyData, 3))));
                weatherData.put("sideWeatherConditionkey5", (convertSideWeatherCodetoCondition(getSideWeatherCode(hourlyData, 4))));

                
                //Printing the Hashmap
                Map<String, Integer> newMap = new HashMap<>(findIndexofFiveDays(getNextFiveDaysTime(), timeList)); 
                
                //Converting Integer[] Array to int[] Array
                Collection<Integer> values = newMap.values();
                Integer[] integerArray = values.toArray(new Integer[0]);
                
                //Converting the Integer array to int array
                int[] intArray = new int[integerArray.length];
                for(int i = 0;i<integerArray.length;i++){
                    intArray[i] = integerArray[i];
                }
                int[] ArrayIndex = intArray;;

                //Bubble Sorting the Arary
                for (int i = 0; i < ArrayIndex.length - 1; i++) {
                    for (int j = 0; j < ArrayIndex.length - i - 1;  j++){
                        if (ArrayIndex[j] > ArrayIndex[j + 1]) {
                            int temp = ArrayIndex[j];
                            ArrayIndex[j] = ArrayIndex[j + 1];
                            ArrayIndex[j + 1] = temp;
                        }
                    }
                }

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
        else{
            weatherCondition = "Unknown";
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

    //Getting the next 5 Day time
    public static List<String> getNextFiveDaysTime(){
        //List to hold the future times
        List<String> nextFiveDaysTime = new ArrayList<>();

        //Defining the numbers of hours to add
        int[] hours = {24,48,72,96,120};

        //Getting the current time
        LocalDateTime curDateTime = LocalDateTime.now();

        //Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");  

        //Calculating the future time
        for(int i :hours){
            LocalDateTime future24hr = curDateTime.plusHours(i);
            nextFiveDaysTime.add(future24hr.format(formatter));
        }


        return nextFiveDaysTime;
    }

    public static Map<String, Integer> findIndexofFiveDays(List<String> nextFiveDaysTime, JSONArray timeList){
        Map<String, Integer> indexMap = new HashMap<>();
        for(String i : nextFiveDaysTime){
            for(int j = 0; j < timeList.size(); j++){
                String jsontime = (String) timeList.get(j);
                if(i.equalsIgnoreCase(jsontime)){
                    indexMap.put(i,j);
                    break;
                }
            }
        }
        return indexMap;
    }

    public static void printIndexMap(Map<String, Integer> indexMap) {
        for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
            String time = entry.getKey();
            Integer index = entry.getValue();
            if (index != -1) {
                System.out.println("Time: " + time + " is at index: " + index);
            } else {
                System.out.println("Time: " + time + " is not found in timeList.");
            }
        }
    }

    public static int getTemperatureForDay(JSONObject hourlyData, int sideCurrentIndex) {
        JSONArray sideTemperatureData = (JSONArray) hourlyData.get("temperature_2m");   
        double sideTempwithdec = (double) sideTemperatureData.get(sideCurrentIndex);
        int sideTemp = (int) Math.round(sideTempwithdec);
        return sideTemp;
    }

    public static long getSideWeatherCode(JSONObject hourlyData, int sideCurrentIndex){
        JSONArray sideWeatherCodelist = (JSONArray) hourlyData.get("weather_code");
        long sideWeatherConditionCode = (long)sideWeatherCodelist.get(sideCurrentIndex);
        return sideWeatherConditionCode;
    }

    public static String convertSideWeatherCodetoCondition(long sideWeatherCode){
        String sideWeatherCondition = "";
        if (sideWeatherCode == 0L) {
            sideWeatherCondition = "Clear";
        }

        else if(sideWeatherCode <= 3L && sideWeatherCode > 0L ){
            sideWeatherCondition = "Cloudy";
        }

        else if ((sideWeatherCode >= 51L && sideWeatherCode <= 67L) || (sideWeatherCode >= 80 && sideWeatherCode <=99)){
            sideWeatherCondition = "Rainy";
        }
        
        else if(sideWeatherCode >= 71L && sideWeatherCode <= 77L ){
            sideWeatherCondition = "Snow";
        }
        else{
            sideWeatherCondition = "Unknown";
        }
        return sideWeatherCondition;
    }

    public static String[] getnextDay(List<String> nextFiveDaysTime){

        //Converting list<String> to String[] array
        String[] nextFiveDayTimeArr = nextFiveDaysTime.toArray(new String[0]);

        //Holder Array for Days names
        String[] daysOfWeek = new String[nextFiveDayTimeArr.length];

        //Formatter for parsing
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        for (int i = 0; i < nextFiveDayTimeArr.length; i++){
            //Parsing the date string
            LocalDateTime dateTime = LocalDateTime.parse(nextFiveDayTimeArr[i], formatter);
            //Getting the day of the week
            DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

            // Convert the day of the week to a string and capitalize the first letter only
            String dayOfWeekStr = dayOfWeek.toString();
            String formattedDayOfWeek = dayOfWeekStr.substring(0, 1).toUpperCase() + dayOfWeekStr.substring(1).toLowerCase();
            //Converting to String and storing into the array
            daysOfWeek[i] = formattedDayOfWeek.toString();
        }


        return daysOfWeek;
    }

    public static String printName(String locationName) {
        JSONArray locationData = getLocationData(locationName);
        if (locationData == null) {
            System.out.println("Location not entered.");
        }
        JSONObject location = (JSONObject) locationData.get(0);
        String name = (String) location.get("name");

        return name;

    }        
}
