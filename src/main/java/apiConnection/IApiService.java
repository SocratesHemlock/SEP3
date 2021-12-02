package apiConnection;

import com.google.gson.JsonArray;
import models.Costumer;

import java.io.IOException;

public interface IApiService
{
    String Register(Costumer costumer) throws IOException, InterruptedException;
    JsonArray GetAllCostumers() throws IOException, InterruptedException;
}