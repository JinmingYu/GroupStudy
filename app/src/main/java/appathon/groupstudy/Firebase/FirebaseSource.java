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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseSource implements IFirebaseSource {

    private Firebase root;
    private Firebase postsRef;
    private Firebase usersRef;
    private final String FIREBASE_URL = "http://groupstudy.firebaseio.com";
    List<HashMap<String, String>> fillMaps;
    SimpleAdapter listViewAdapter;
    private DataSnapshot dataSnapshot;
    private List<String> filterOptions;

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
        this.listViewAdapter = adapter;
        this.fillMaps = fillMaps;

        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseSource.this.dataSnapshot = dataSnapshot;

                updateListView();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void updateListView() {
        List<Post> posts = parseDataSnapshot(dataSnapshot);

        fillMaps.clear();

        // Filter the list
        posts = filterPosts(posts);

        // Add the posts to the listview's model
        for (Post post : posts) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", post.getTitle());
            map.put("class", post.getClassName());
            map.put("location", post.getLocation());
            map.put("additional_info", post.getAdditionalInformation());
            fillMaps.add(map);
        }

        // Redraw the listview
        listViewAdapter.notifyDataSetChanged();
    }

    private List<Post> parseDataSnapshot(DataSnapshot dataSnapshot) {
        Map<String, Map<String, String>> postsJson = (Map) dataSnapshot.getValue();
        List<Post> posts = new ArrayList<>();

        // Create the Posts list from the json
        for(String postId : postsJson.keySet()) {

            Map<String, String> postInfo = postsJson.get(postId);
            posts.add(new Post(
                    postInfo.get("title"),
                    postInfo.get("className"),
                    postInfo.get("location"),
                    postInfo.get("additionalInformation")
            ));
        }

        return posts;
    }

    private List<Post> filterPosts(List<Post> originalPosts) {
        if (filterOptions == null || filterOptions.size() == 0) {
            return originalPosts;
        }

        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : originalPosts) {
            if (filterOptions.contains(post.getClassName())) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    public List<String> getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(List<String> filterOptions) {
        this.filterOptions = filterOptions;
    }
}
