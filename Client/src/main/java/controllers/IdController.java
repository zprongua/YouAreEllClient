package controllers;

import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.Id;

public class IdController {
    String rootURL = "http://zipcode.rocks:8085";
    String fullUrl;
    private HashMap<String, Id> allIds;

    public ArrayList<Id> getIds() {
        return null;
    }

    public Id postId(Id id) {
        try {
            fullUrl = rootURL + "/ids";
            // create json from id
            Id myId = id;
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(myId);

            // call server, get json result Or error
            HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(fullUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            // result json to Id obj
            Id nid = gson.fromJson((Reader) httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString()), Id.class);
            return nid;
        } catch (Exception e) {
            return null;
        }
        // result json to Id obj
    }

    public Id putId(Id id) {
        return null;
    }
 
}