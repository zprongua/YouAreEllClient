package youareell;

import controllers.*;
import models.Id;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.tt = new TransactionController(messageController, idController);
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new TransactionController(new MessageController(), new IdController()));
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }



    public String MakeURLCall(String s, String get, String s1) {
        return tt.makecall(s, get, s1, "");
    }

    public String get_ids() {
        return tt.makecall("ids", "GET");
    }

    public String get_messages() {
        return tt.makecall("messages", "GET");
    }

    public String get_messages_id(String github) {
        return tt.makecall("messages", github);
    }

    public String postId(String name, String github) {
        return tt.makecall("ids", name, github, "");
    }

    public String post_message(String github, String message) {
        return tt.makecall("send", github, message, "");
    }

    public String post_message_id(String github, String message, String github2) {
        return tt.makecall("send", github, message, github2);
    }

    public String get_messages_from(String fromid, String toid) {
        return tt.makecall("get", fromid, toid);
    }

    public String get_message_seq(String github, String command) {
        return tt.makecall("get", "message", github, command);
    }
}
