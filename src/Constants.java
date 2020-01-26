public class Constants {
    private static String password = "e0cd5f82551838bbe3ecd1ee3a375dc501d6c34d";
    private static String userName = "rttapi_spalmer";
    private static int numberOfTrains = 4;
    private static String url = "https://api.rtt.io/api/v1/json/search/";
    private static String station = "MZH";
    private static int refreshTimeSecs = 10;

    public static String getUserName() {
        return userName;
    }
    public static String getPassword() {
        return password;
    }
    public static String getURL() {
        return url;
    }
    public static int getNumberOfTrains() {
        return numberOfTrains;
    }
    public static String getStation() {
        return station;
    }
    public static int getRefreshTimeSecs() {
        return refreshTimeSecs;
    }
}
