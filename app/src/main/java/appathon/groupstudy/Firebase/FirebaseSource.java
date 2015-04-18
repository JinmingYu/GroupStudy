package appathon.groupstudy.Firebase;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import appathon.groupstudy.activities.IUpdateActivity;
import appathon.groupstudy.models.Post;
import appathon.groupstudy.models.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseSource implements IFirebaseSource {

    private Firebase root;
    private Firebase postsRef;
    private Firebase usersRef;
    private final String FIREBASE_URL = "http://groupstudy.firebaseio.com";

    public FirebaseSource(final IUpdateActivity updateableActivity)
    {
        root = new Firebase(FIREBASE_URL);
        postsRef = root.child("posts");
        usersRef = root.child("users");
    }

    @Override
    public void AddPost(Post post)
    {
        Firebase newPostRef = postsRef.push();
        post.setId(newPostRef.getKey());
        newPostRef.setValue(post);

    }

    @Override
    public Firebase getPost(String post_id) {
        return postsRef.child(post_id);
    }

    @Override
    public Firebase getUser(String user_id) {
        return usersRef.child(user_id);
    }
}
