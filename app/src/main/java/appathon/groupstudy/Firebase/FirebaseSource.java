package appathon.groupstudy.Firebase;

import android.util.Log;
import android.widget.SimpleAdapter;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import appathon.groupstudy.activities.IUpdateActivity;
import appathon.groupstudy.models.Post;
import appathon.groupstudy.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseSource implements IFirebaseSource {

    private Firebase root;
    private Firebase postsRef;
    private Firebase usersRef;
    private final String FIREBASE_URL = "http://groupstudy.firebaseio.com";

    public FirebaseSource()
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

    public void bindToList(final List<HashMap<String, String>> fillMaps, final SimpleAdapter adapter) {
        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Map<String, String>> posts = (Map) dataSnapshot.getValue();
                fillMaps.clear();

                for(String postId : posts.keySet()) {

                    Map<String, String> postInfo = posts.get(postId);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("title", postInfo.get("title"));
                    map.put("class", postInfo.get("className"));
                    map.put("location", postInfo.get("location"));
                    map.put("additional_info", postInfo.get("additionalInformation"));
                    fillMaps.add(map);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
