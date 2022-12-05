package models;

/* 
 * POJO for an Id object
 *  {
        "userid": "-", // gets filled w id
        "name": "Kris",
        "github": "xt0fer"
    }
 */
public class Id {
    private String userid = "-";
    private String name = "";
    private String github = "";

    public Id (String name, String githubId) {
        this.userid = "-";
        this.name = name;
        this.github = githubId;
    }

    public Id (String githubId) {
        this.github = githubId;
    }

    public Id () {
    }

    public String getUid() {
        return userid;
    }

    public void setUid(String uid) {
        this.userid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}