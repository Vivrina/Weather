public class Day {
    private String day;
    private double temperature = -100;
    private double relativeHumidity = 0;
    private double windSpeed = 0;

    public Day() {
    }

    public Day(String day, double temperature, double relativeHumidity, double windSpeed) {
        this.day = day;
        this.temperature = temperature;
        this.relativeHumidity = relativeHumidity;
        this.windSpeed = windSpeed;
    }




    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
