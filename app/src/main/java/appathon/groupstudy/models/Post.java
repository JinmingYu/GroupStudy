package appathon.groupstudy.models;

import java.io.Serializable;

/**
 * Created by Jinming on 4/17/2015.
 */
public class Post implements Comparable<Post>, Serializable {

    private String id;
    private String title;
    private String className;
    private String location;
    private String additionalInformation;

    private long timePosted;

    public Post(String title, String className, String location, String additionalInformation)
    {
        this.title = title;
        this.className = className;
        this.location = location;
        this.additionalInformation = additionalInformation;

        this.id = null;
        timePosted = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}


    public long getTimePosted() {
        return timePosted;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
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