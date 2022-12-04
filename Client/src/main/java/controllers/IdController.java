package controllers;

import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Id;

public class IdController {
    String fullURL = "http://zipcode.rocks:8085/ids";
    private HashMap<String, Id> allIds;

    public ArrayList<Id> getIds() {
        try {
            Gson gson = new Gson();
            ArrayList<Id> theIds = new ArrayList<>();
            // get all ids
            // call server, get json result Or error
            HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(fullURL)).build();

            // result json to Id obj
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            Type idsList = new TypeToken<ArrayList<Id>>(){}.getType();
            theIds = gson.fromJson(postResponse.body(), idsList);
            return theIds;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Id postId(Id id) {
        try {
            // create json from id
            Id myId = id;
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(myId);

            // call server, get json result Or error
            HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(fullURL))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            // result json to Id obj
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            myId = gson.fromJson(postResponse.body(), Id.class);
            return myId;
        } catch (Exception e) {
            return null;
        }
    }

    public Id putId(Id id) {
        return null;
    }
 
}