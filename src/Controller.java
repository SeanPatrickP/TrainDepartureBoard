import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;

public class Controller {

    public static void main(String [] args) {
        String station = Constants.getStation();
        int statusCode = 200;
        int trainsToShow = Constants.getNumberOfTrains();

        while(statusCode == 200) {
            Connector connection = new Connector(station);
            statusCode = connection.getStatusCode();
            InputStream obtainData = connection.getStream();
            DataEngine dEngine = new DataEngine(obtainData);
            Object jsonArrayData = dEngine.dataReturner();
            JSONArray jArray = (JSONArray) jsonArrayData;
            OutputHandler oHandler = new OutputHandler(jArray, trainsToShow);
            ArrayList<Departure> departureList = oHandler.getDataList();
            printTrains(departureList);
            try {
                TimeUnit.SECONDS.sleep(Constants.getRefreshTimeSecs());
            }
            catch (Exception e){
                System.out.println(e);
            }
            System.out.print("\033[H\033[2J");
        }
    }
    public static void printTrains(ArrayList<Departure> departureList){
        int serviceNumber = 1;
        for (Departure toPrint: departureList) {
            if(toPrint.isPassengerService()) {

                String DepartureTime = toPrint.getDepartureTime();
                if(DepartureTime.equals(toPrint.getBookedDepartureTime()))
                    DepartureTime = "On Time";

                System.out.println(ordinal(serviceNumber) + " " + toPrint.getBookedDepartureTime() + "  " + toPrint.getPlatform() + " " + toPrint.getDestination() + " (" + toPrint.getArrivalTimeAtDestination() + ") " + DepartureTime);
                System.out.println(toPrint.getOperator() + " Service From " + toPrint.getStartOrigin() + "\n");
                serviceNumber ++;
            }
        }
    }
    public static String ordinal(int i) {
        String[] sufixes = new String[] {"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }
}
