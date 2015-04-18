package appathon.groupstudy.models;

import java.util.ArrayList;

/**
 * Created by Jinming on 4/17/2015.
 */
public class User {

    private int id;
    private ArrayList<Post> posts;

    public User(int id) {
        this.id = id;
        this.posts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
