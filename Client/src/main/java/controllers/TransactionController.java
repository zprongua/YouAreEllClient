package controllers;

import models.Id;

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

    public String makecall(String s, String get, String s1) {
        if (s.equals("ids") && get.equals("GET")) {
            StringBuilder sb = new StringBuilder();
            for (Id k : getIds()) {
                sb.append(k + "\n");
            }
            return sb.toString();
        }
        else if (s.equals("ids")) {
            return postId(get, s1);
        }
        if (s.equalsIgnoreCase("messages") && get.equalsIgnoreCase("GET")) {
            return null;
        }
        return null;
    }
}
