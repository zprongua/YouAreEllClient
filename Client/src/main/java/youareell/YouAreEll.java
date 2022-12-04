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

    public String postId(String name, String github) {
        return tt.makecall("ids", name, github);
    }

    private boolean MakeURLCall(String s, String get, String s1) {
        return true;
    }

    public String get_ids() {
        return tt.makecall("ids", "GET", "");
    }

    public String get_messages() {
        return tt.makecall("messages", "GET", "");
    }


}
