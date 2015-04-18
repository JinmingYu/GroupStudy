package appathon.groupstudy.activities;

import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.firebase.client.Firebase;

import appathon.groupstudy.Firebase.firebase.IFirebaseSource;
import appathon.groupstudy.R;


public class MainActivity extends ActionBarActivity {
    //Firebase rootRef = new Firebase("https://groupstudy.firebaseio.com/web/data");
    private IFirebaseSource firebaseSource;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        // create the grid item mapping
        String[] from = new String[] {"title", "class", "location", "additional_info"};
        int[] to = new int[] { R.id.title, R.id.class_name, R.id.location, R.id.additional_info};

        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < 10; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", "Title " + i);
            map.put("class", "CS " + i);
            map.put("location", "Klaus");
            map.put("additional_info", "Study group in Klaus 1234. Come join us. We have pizza!");
            fillMaps.add(map);
        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.study_group_list_item, from, to);
        listView.setAdapter(adapter);

        //Firebase.setAndroidContext(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
