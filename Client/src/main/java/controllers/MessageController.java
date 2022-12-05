package controllers;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
            ArrayList<Message> theMessages;
            // get all messages
            // call server, get json result Or error
            HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(fullURL)).build();

            // result json to Message obj
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            Type messageList = new TypeToken<ArrayList<Message>>(){}.getType();

            theMessages = gson.fromJson(getResponse.body(), messageList);
            return theMessages;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Message> getMessagesForId(Id Id) {
        String fullURL = rootURL + "/ids/" + Id.getGithub() + "/messages";
        try {
            Gson gson = new Gson();
            ArrayList<Message> messagesForId;
            // get all messages
            // call server, get json result Or error
            HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(fullURL)).build();

            // result json to Message obj
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            Type messageList = new TypeToken<ArrayList<Message>>(){}.getType();

            messagesForId = gson.fromJson(getResponse.body(), messageList);
            return messagesForId;
        } catch (Exception e) {
            return null;
        }
    }

    public Message getMessageForSequence(Id id, String seq) {
        String fullURL = rootURL + "/ids/" + id.getGithub() + "/messages/" + seq;
        try {
            Gson gson = new Gson();
            // get message by seq
            // call server, get json result Or error
            HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(fullURL)).build();

            // result json to Message obj
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(getResponse.body(), Message.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Message> getMessagesFromFriend(Id myId, Id friendId) {
        String fullURL = rootURL + "/ids/" + myId.getGithub() + "/from/" + friendId.getGithub();
        try {
            Gson gson = new Gson();
            List<Message> messagesFrom;

            HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(fullURL))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

            Type messageType = new TypeToken<ArrayList<Message>>(){}.getType();
            messagesFrom = gson.fromJson(postResponse.body(), messageType);
            return messagesFrom;
        } catch (Exception e) {
            return null;
        }
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        String fullURL = rootURL + "/ids/" + myId.getGithub() + "/messages";
        try {
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(msg);

            HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(fullURL))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            msg = gson.fromJson(postResponse.body(), Message.class);
            return msg;
        } catch (Exception e) {
            return null;
        }
    }
}