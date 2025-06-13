package Service;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import models.DataExchange;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiExchange {

    private final HttpClient httpClient;
    private final Gson gson;
    private final Dotenv dotenv;
    private final String url_base = "https://v6.exchangerate-api.com/v6/";
    private final String query = "/latest/USD";


    public ApiExchange()
    {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.dotenv = Dotenv.load();
    }
    public DataExchange GetData()
    {
        String url = url_base + dotenv.get("API_KEY") + query;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            HttpResponse<String> response;
            response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), DataExchange.class);

        } catch (IOException | InterruptedException e) {
            System.err.println("Error de conexión o interrupción: " + e.getMessage());
        }
        return null;
    }
}
