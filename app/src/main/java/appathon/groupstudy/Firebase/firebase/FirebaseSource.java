package appathon.groupstudy.Firebase.firebase;

import com.firebase.client.Firebase;
import appathon.groupstudy.activities.IUpdateActivity;
import appathon.groupstudy.models.Post;
import appathon.groupstudy.models.User;
/**
 * Created by Jinming on 4/17/2015.
 */
public class FirebaseSource implements IFirebaseSource{
    private Firebase root;
    private Firebase postsRef;
    private Firebase usersRef;
    private IUpdateActivity updateableActivity;
    private final String FIREBASE_URL = "http://groupstudy.firebaseio.com";



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
