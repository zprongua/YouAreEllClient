package controllers;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Id;
import models.Message;

public class MessageController {
    String rootURL = "http://zipcode.rocks:8085";
    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        String fullURL = rootURL + "/messages";
        try {
            Gson gson = new Gson();
            ArrayList<Message> theMessages = new ArrayList<>();
            // get all messages
            // call server, get json result Or error
            HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(fullURL)).build();

            // result json to Message obj
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            Type messageList = new TypeToken<ArrayList<Message>>(){}.getType();
            System.out.println(getResponse.body());
            theMessages = gson.fromJson(getResponse.body(), messageList);
            return theMessages;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}