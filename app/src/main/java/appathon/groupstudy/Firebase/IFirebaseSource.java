package appathon.groupstudy.Firebase;

import android.widget.SimpleAdapter;

import com.firebase.client.Firebase;
import com.firebase.client.Firebase;

import appathon.groupstudy.models.Post;
import appathon.groupstudy.models.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jinming on 4/17/2015.
 */
public interface IFirebaseSource
{
    void AddPost(Post post);
    Firebase getPost(String post_id);

    Firebase getUser(String user_id);

    void bindToList(List<HashMap<String, String>> fillMaps, SimpleAdapter adapter);

}