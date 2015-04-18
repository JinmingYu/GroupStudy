package appathon.groupstudy.activities;

/**
 * Created by Jinming on 4/17/2015.
 */

import appathon.groupstudy.models.Post;

import java.util.List;


public interface IUpdateActivity
{
    public void addPost(Post post);

    public void updatePost(Post post);

    public void removePost(Post removed_post);


}
