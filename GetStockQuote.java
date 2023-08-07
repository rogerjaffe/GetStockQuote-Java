import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

/**
 * GetStockQuote requests the current stock price for a 
 * ticker symbol of your choice.
 */
public class GetStockQuote
{

    /**
     * Retrieve stock data from marketdata.app API
     * 
     * @param ticker Ticker symbol
     * @return  JSON data with the stock information
     */
    public static double Get(String ticker) throws IOException, Exception {
        try {
            // Create a neat value object to hold the URL
            URL url = new URL("https://api.marketdata.app/v1/stocks/quotes/"+ticker+"/");

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("accept", "application/json");

            // This line makes the request
            InputStream responseStream = connection.getInputStream();

            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            Object obj = new JSONParser().parse(result);
            JSONObject jo = (JSONObject) obj;
            JSONArray arr = (JSONArray) jo.get("last");
            Double last = ((Double)arr.get(0)).doubleValue();
            return last;
        } catch (IOException err) {
            System.out.println("Error getting stock information");
            throw err;
        } catch (Exception err) {
            System.out.println("Parsing error");
            throw err;
        }
    }
}
