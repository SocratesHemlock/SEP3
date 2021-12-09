package apiConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import models.Location;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocationService implements  ILocationService
{
    private final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&key=AIzaSyCSEWcCV-DTiIsJU2NsMHM41RlbU5w6AQM";
    private HttpClient client;

    public LocationService()
    {
        client = HttpClient.newHttpClient();
    }

    @Override
    public double GetDistance(Location loc1, Location loc2) throws IOException, InterruptedException {
        String formatedURL = String.format(API_URL, loc1.getLat(), loc2.getLat());
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response.body());

        System.out.println(jsonArray.toString());
        return 0;
    }

    @Override
    public String GetStreetName(Location location)
    {

        return null;
    }

    @Override
    public String GetEstimatedTime(Location loc1, Location loc2)
    {
        return null;
    }
}
