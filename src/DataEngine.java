import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataEngine {
    private Object jsonArrayData;

    public DataEngine(InputStream dataConnection) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(
                    new InputStreamReader(dataConnection, "UTF-8"));
            String dataKey = "services";
            jsonArrayData = jsonObject.get(dataKey);
        }
        catch (Exception e) {
            System.out.println("Error Occurred: " + e);
        }
    }
    public Object dataReturner() {
        return jsonArrayData;
    }
}
