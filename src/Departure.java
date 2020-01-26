public class Departure {
    private String destination;
    private String departureTime;
    private String bookedDepartureTime;
    private String platform;
    private String arrivalTimeAtDestination;
    private String operator;
    private String startOrigin;
    private boolean passengerService;

    public Departure(String dest, String dTime, String plat, String arriveDest, String toc, String origin, boolean pService, String bookedDeparture) {
        destination = dest;
        departureTime = dTime;
        platform = plat;
        arrivalTimeAtDestination = arriveDest;
        operator = toc;
        startOrigin = origin;
        passengerService = pService;
        bookedDepartureTime = bookedDeparture;
    }

    public String getArrivalTimeAtDestination() {
        return arrivalTimeAtDestination;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public boolean isPassengerService() {
        return passengerService;
    }
    public String getDestination() {
        return destination;
    }
    public String getOperator() {
        return operator;
    }
    public String getPlatform() {
        return platform;
    }
    public String getStartOrigin() {
        return startOrigin;
    }
    public String getBookedDepartureTime() {
        return bookedDepartureTime;
    }
}

