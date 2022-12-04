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
        return null;
    }
    public String postId(String name, String githubName) {
        Id tid = new Id(name, githubName);
        tid = idCtrl.postId(tid);
        return ("\nId registered.");
    }

    public String makecall(String s, String get, String s1) {
        return null;
    }
}
