package models;

import java.time.LocalDateTime;

/*
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
public class Message implements Comparable {

    private String sequence = "-";
    private String timestamp = (LocalDateTime.now()+"Z");
    private String fromid = "";
    private String toid = "";
    private String message = "";

    public Message (String message, String fromid, String toid) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toid;
    }

    public Message (String message, String fromid) {
        this.message = message;
        this.fromid = fromid;
        this.toid = "";
    }

    @Override
    public String toString() {
        return "to: " + this.toid + "\nfrom: "+ this.fromid + "\n" + this.message + "\n----\n";
    }

    public int compareTo(Object o) {
        return this.sequence.compareTo(((Message) o).getSequence());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSequence() {
        return sequence;
    }
}