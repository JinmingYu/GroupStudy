package appathon.groupstudy.models;

import com.firebase.client.Firebase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jinming on 4/17/2015.
 */
public class Post implements Comparable<Post>, Serializable {

    private String title;
    private String content;
    private String id;
    //    private String user_id;
    private long timePosted;
    public Post(String title, String content)
    {
        this.title = title;
        this.content = content;
        this.id = null;
//        this.user_id = user_id;
        timePosted = System.currentTimeMillis();
    }

//    public String getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(String user_id) {
//        this.user_id = user_id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}


    public long getTimePosted() {
        return timePosted;
    }

    @Override
    public int compareTo(Post another) {
        //later  == greater//
        return (int) (this.timePosted - another.timePosted);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Post))
        {
            return false;
        }
        Post p = (Post)o;
        return p.getId() == getId();
    }


}