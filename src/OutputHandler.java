import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OutputHandler {
    private ArrayList<Departure> dataList = new ArrayList<>();

    public OutputHandler(JSONArray jsonArray, int trainsToShow){
        // The below is set to obtain data for first 4 train departures
        if(trainsToShow > jsonArray.size())
            trainsToShow = jsonArray.size();

        for(int i = 0 ; i < trainsToShow ; i++) {
            Object data = jsonArray.get(i);
            JSONObject jData = (JSONObject) data;

            String passengerSevice = jData.get("isPassenger").toString();
            boolean pService;
            if(passengerSevice.equals("true"))
                pService = true;
            else
                pService = false;

            String toc = jData.get("atocName").toString();

            Object locataionD = jData.get("locationDetail");
            JSONObject jLocationDetail = (JSONObject) locataionD;
            Object destinationObject = jLocationDetail.get("destination");
            JSONArray desinationArray = (JSONArray) destinationObject;
            JSONObject destinationJObject = (JSONObject) desinationArray.get(0);

            String destination = destinationJObject.get("description").toString();

            String arrivalTime = destinationJObject.get("publicTime").toString();

            Object originObject = jLocationDetail.get("origin");
            JSONArray originArray = (JSONArray) originObject;
            JSONObject originJObject = (JSONObject) originArray.get(0);

            String origin = originJObject.get("description").toString();

            String departureTime = jLocationDetail.get("gbttBookedDeparture").toString();
            if(!jData.get("serviceType").toString().equals("bus"))
                departureTime = jLocationDetail.get("realtimeDeparture").toString();

            if (jLocationDetail.get("displayAs").toString().equals("CANCELLED_CALL"))
                departureTime = "Cancelled";

            String platform = "";
            try {
                platform = jLocationDetail.get("platform").toString();
            }
            catch (Exception e) {
                platform = "-";
                if(jData.get("serviceType").toString().equals("bus"))
                    platform = "BUS";
            }

            String bookedDepartureTime = jLocationDetail.get("gbttBookedDeparture").toString();

            dataList.add(new Departure(destination, departureTime, platform, arrivalTime, toc, origin, pService, bookedDepartureTime));
        }
    }
    public ArrayList<Departure> getDataList() {
        return dataList;
    }
}
