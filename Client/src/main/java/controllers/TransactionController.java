package controllers;

import models.Id;
import models.Message;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    //Gets
    public List<Id> getIds() {
        return idCtrl.getIds();
    }
    public List<Message> getMessages() {
        return msgCtrl.getMessages();
    }
    public List<Message> getMessagesForId(Id id) {
        return msgCtrl.getMessagesForId(id);
    }
    public List<Message> getMessagesFrom(Id id, Id friendId) {
        return msgCtrl.getMessagesFromFriend(id, friendId);
    }
    public Message getMessageBySeq(String github, String seq) {
        return msgCtrl.getMessageForSequence(new Id(github), seq);
    }

    //Sets
    public String postId(String name, String githubName) {
        Id tid = new Id(name, githubName);
        tid = idCtrl.postId(tid);
        return tid.toString();
    }
    public Message postMessage(String github, String message, String github2) {
        Message nm = new Message(message, github, github2);
        nm = msgCtrl.postMessage(new Id(github), new Id(github2), nm);
        return nm;
    }

    public String makecall(String s1, String s2) {
        //get all ids
        if (s1.equals("ids") && s2.equals("GET")) {
            StringBuilder sb = new StringBuilder();
            for (Id k : getIds()) {
                sb.append(k + "\n");
            }
            return sb.toString();
        }
        //get last 20 messages
        if (s1.equals("messages") && s2.equals("GET")) {
            StringBuilder sb = new StringBuilder();
            for (Message m : getMessages()) {
                sb.append(m);
            }
            return sb.toString();
        }
        //get last 20 messages for a given user
        if (s1.equals("messages") && (!s2.equals(""))) {
            Id mid = new Id();
            mid.setGithub(s2);
            StringBuilder sb = new StringBuilder();
            for (Message m : getMessagesForId(mid)) {
                sb.append(m);
            }
            return sb.toString();
        }
        return null;
    }

    public String makecall(String s1, String s2, String s3) {
        if (s1.equals("get")) {
            StringBuilder sb = new StringBuilder();
            for (Message m : getMessagesFrom(new Id(s2), new Id(s2))) {
                sb.append(m);
            }
            return sb.toString();
        }
        return null;
    }

    public String makecall(String s1, String s2, String s3, String s4) {
        //post new user
        if (s1.equals("ids")) return postId(s2, s3);
        //post message to global
        else if (s1.equals("send")) {
            return postMessage(s2, s3, s4).toString();
        }
        if (s1.equals("get")) {
            return getMessageBySeq(s3, s4).toString();
        }
        return null;
    }
}