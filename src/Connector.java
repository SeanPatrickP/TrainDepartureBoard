import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class Connector {
    private String urlConstant;
    private String userName;
    private String password;
    private InputStream content;
    private HttpsURLConnection connect;
    private int statusCode;

    public Connector(String station) {
        urlConstant = Constants.getURL();
        userName = Constants.getUserName();
        password = Constants.getPassword();
        try {
            String builtUrl = urlConstant + station;
            URL argURL = new URL(builtUrl);
            connect = getConnection(true, "rttapi_spalmer", "e0cd5f82551838bbe3ecd1ee3a375dc501d6c34d", argURL);
            content = (InputStream) connect.getInputStream();
            statusCode = connect.getResponseCode();
            System.out.println("Status Code: " + statusCode + "\n");
            if (statusCode != 200) {
                System.out.println("Welcome To " + Constants.getStation());
                System.exit(0);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error Occurred: " + e);
        }
    }
    public static HttpsURLConnection getConnection(boolean ignoreInvalidCertificate, String user, String pass, URL url) throws KeyManagementException, NoSuchAlgorithmException, IOException{
        SSLContext ctx = SSLContext.getInstance("TLS");
        if (ignoreInvalidCertificate){
            ctx.init(null, new TrustManager[] { new InvalidCertificateTrustManager() }, null);
        }
        SSLContext.setDefault(ctx);

        String authStr = user+":"+pass;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + authEncoded);

        if (ignoreInvalidCertificate){
            connection.setHostnameVerifier(new InvalidCertificateHostVerifier());
        }

        return connection;
    }
    public InputStream getStream() {
        return content;
    }
    public void refreshStream() {
        try {
            content = (InputStream) connect.getInputStream();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public int getStatusCode() {
        return statusCode;
    }
}
