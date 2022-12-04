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

    public List<Id> getIds() {
        return idCtrl.getIds();
    }

    public String postId(String name, String githubName) {
        Id tid = new Id(name, githubName);
        tid = idCtrl.postId(tid);
        return tid.toString();
    }

    public List<Message> getMessages() {
        return msgCtrl.getMessages();
    }

    public ArrayList<Message> getMessagesForId(Id id) {
        return msgCtrl.getMessagesForId(id);
    }

    public String makecall(String s, String get, String s1) {
        if (s.equals("ids") && get.equals("GET")) {
            StringBuilder sb = new StringBuilder();
            for (Id k : getIds()) {
                sb.append(k + "\n");
            }
            return sb.toString();
        } else if (s.equals("ids")) {
            return postId(get, s1);
        } else if (s.equalsIgnoreCase("messages") && get.equalsIgnoreCase("GET")) {
            StringBuilder sb = new StringBuilder();
            for (Message m : getMessages()) {
                sb.append(m);
            }
            return sb.toString();
        } else if (s.equalsIgnoreCase("messages") && (!get.equals(""))) {
            Id mid = new Id();
            mid.setGithub(get);
            StringBuilder sb = new StringBuilder();
            for (Message m : getMessagesForId(mid)) {
                sb.append(m);
            }
            return sb.toString();
        }
        return null;
    }
}