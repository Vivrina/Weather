import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Parsing {

    private HashMap<String, Day> answer = new HashMap<>();
    ArrayList<Day> days = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String way = "C:/Users/Админ/IdeaProjects/Homework/src/Homework3/dataexport_20210320T064822.csv"; //путь к файлу
        FileReader fileReader = new FileReader(way);
        int n = 0;
        int w = 0;
        int e = 0;
        int s = 0;
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Parsing parsing = new Parsing();
        String line;
        while ((line = bufferedReader.readLine())!= null) {
            char symbol = line.charAt(0);
            if (Character.isLetter(symbol)){
            } else {
                String[] string = line.split(",");
                Day day = new Day(string[0], Double.parseDouble(string[1]), Double.parseDouble(string[2]), Double.parseDouble(string[3]));
                parsing.days.add(day);
                double direction = Double.parseDouble(string[4]);
                if (direction >= 45 && direction < 135) {
                    e++;
                } else if (direction >= 135 && direction < 225) {
                    s++;
                } else if (direction >= 225 && direction < 315) {
                    w++;
                } else n++;
            }
        }

        parsing.answer.put("Temperature", parsing.days.get(0));
        parsing.answer.put("Relative Humidity", parsing.days.get(0));
        parsing.answer.put("Wind Speed", parsing.days.get(0));
        parsing.bestTemperature();
        parsing.lowHumidity();
        parsing.strongWind();
        parsing.fileCreation(n,w,s,e);
    }

    public void fileCreation(int n, int w, int s, int e) throws Exception{
        File file = new File("Weather.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Highest temperature ("+ answer.get("Temperature").getTemperature() +" °C) was " + splitDate("Temperature") +
                "\nThe lowest humidity ("+ answer.get("Relative Humidity").getRelativeHumidity() +" %) was " +  splitDate("Relative Humidity") +
                "\nThe highest wind speed (" + answer.get("Wind Speed").getWindSpeed() + " km/h) was " + splitDate("Wind Speed") +
                "\nAverage air temperature: "+ averageTemperature() +" °C " +
                "\nAverage humidity: "+ averageHumidity() + " % " +
                "\nAverage wind speed: " + averageSpeed() + " km/h " +
                "\nMost frequent destination: "+ oneDirection(n,w,s,e));
        fileWriter.flush();
        fileWriter.close();
    }


    public String splitDate (String key){
        String date = "";
        date = date + answer.get(key).getDay().substring(0, 4);
        date = date + "." + answer.get(key).getDay().substring(4, 6);
        date = date + "." + answer.get(key).getDay().substring(6, 8);
        date = date + " in ";
        date = date + answer.get(key).getDay().substring(9, 11);
        date = date + ":" + answer.get(key).getDay().substring(11, 13);
        return date;
    }

    public HashMap<String, Day> bestTemperature(){
        for (int i = 1; i<days.size(); i++){
            if (answer.get("Temperature").getTemperature()<days.get(i).getTemperature()){
                answer.put("Temperature", days.get(i));
            }
        }

        return answer;
    }

    public HashMap<String, Day> lowHumidity(){
        for (int i = 1; i<days.size(); i++){
            if (answer.get("Relative Humidity").getRelativeHumidity()>days.get(i).getRelativeHumidity()){
                answer.put("Relative Humidity", days.get(i));
            }
        }

        return answer;
    }

    public HashMap<String, Day> strongWind(){
        for (int i = 1; i<days.size(); i++){
            if (answer.get("Wind Speed").getWindSpeed()<days.get(i).getWindSpeed()){
                answer.put("Wind Speed", days.get(i));
            }
        }

        return answer;
    }

    public double averageTemperature(){
        double answer = 0;
        for (int i=0; i<days.size(); i++){
            answer = answer + days.get(i).getTemperature();
        }
        return answer/days.size();
    }

    public double averageHumidity(){
        double answer = 0;
        for (int i=0; i<days.size(); i++){
            answer = answer + days.get(i).getRelativeHumidity();
        }
        return answer/days.size();
    }

    public double averageSpeed(){
        double answer = 0;
        for (int i=0; i<days.size(); i++){
            answer = answer + days.get(i).getWindSpeed();
        }
        return answer/days.size();
    }

    public String oneDirection(int n, int w, int s, int e){
        String answer = "";
        if (n>=w && n>=s && n>=e){
            answer = "North";
        }
        if (w>=n && w>=s && w>=e){
            answer = "West";
        }
        if (s>=w && s>=n && s>=e){
            answer = "South";
        }
        if (e>=w && e>=s && e>=n){
            answer = "East";
        }

        return answer;
    }
}
