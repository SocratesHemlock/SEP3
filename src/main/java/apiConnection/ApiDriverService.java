package apiConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import models.Driver;
import models.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDriverService implements IApiDriverService
{
    public static final String API_URL = "https://localhost:5003/Drivers";//"https://localhost:5003/Customers"
    public static final String API_URL_ORDERS = "https://localhost:5003/Orders";
    Gson gson = new Gson();
    HttpClient client;

    public ApiDriverService()
    {
        client = HttpClient.newHttpClient();
    }

    @Override
    public synchronized String Register(Driver driver) throws IOException, InterruptedException
    {
        String driverGson = gson.toJson(driver);
        System.out.println(driverGson);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(driverGson))
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        return String.valueOf(response.statusCode());
    }

    @Override
    public String Login(Driver driver) throws IOException, InterruptedException
    {
        String driverJson = gson.toJson(driver);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL+"/Login"))
                .POST(HttpRequest.BodyPublishers.ofString(driverJson))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        JSONObject object = new JSONObject(response.body().toString());
        System.out.println(object.getJSONObject("result"));

        return String.valueOf(object.getJSONObject("result"));
    }

    @Override
    public synchronized JsonArray GetAllDrivers() throws IOException, InterruptedException
    {
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
        return jsonArray;
    }

    @Override
    public Driver GetDriverByUsername(String username) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(String.format(API_URL + "/GetDriverInfo/%s", username)))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        JSONObject object = new JSONObject(response.body().toString());
        Driver driver = gson.fromJson(object.getJSONObject("result").toString(), Driver.class);
        return driver;
    }

    @Override
    public String EditDriver(Driver driver) throws IOException, InterruptedException
    {
        String driverJson = gson.toJson(driver);

        System.out.println(driverJson);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL+"/EditCustomer"))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(driverJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        JSONObject object = new JSONObject(response.body().toString());

        return String.valueOf(object.getJSONObject("result"));
    }

    @Override
    public String Logout(Driver driver) throws IOException, InterruptedException
    {
        String driverJson = gson.toJson(driver);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL+"/Logout"))
                .POST(HttpRequest.BodyPublishers.ofString(driverJson))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());

        return String.valueOf(response.body());
    }

    @Override
    public String GetAllPendingRequests() throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL_ORDERS + "/GetPendingOrders"))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        JSONArray object = new JSONArray(response.body().toString());
        System.out.println(object.toString());
        return response.body().toString();
    }

    @Override
    public String AcceptOrder(Order order) throws IOException, InterruptedException
    {
        String orderJson = gson.toJson(order);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL_ORDERS+"/AcceptOrder"))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(orderJson))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        JSONObject object = new JSONObject(response.body().toString());

        return String.valueOf(object.getJSONObject("result"));
    }

    @Override
    public String CompleteOrder(Order order) throws IOException, InterruptedException
    {
        String orderJson = gson.toJson(order);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL_ORDERS+"/CompleteOrder"))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(orderJson))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        JSONObject object = new JSONObject(response.body().toString());

        return String.valueOf(object.getJSONObject("result"));
    }
}