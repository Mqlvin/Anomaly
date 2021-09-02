package json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static http.HTTPClient.requestJson;

public class AnomalyAPI {
    public static void getInfo() {
        String response = requestJson("https://mqlvin.github.io/api/main.html");
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(response).getAsJsonObject();
    }
}
