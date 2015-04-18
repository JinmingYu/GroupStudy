package appathon.groupstudy.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import appathon.groupstudy.Firebase.FirebaseSource;
import appathon.groupstudy.Firebase.IFirebaseSource;
import appathon.groupstudy.R;
import appathon.groupstudy.models.Post;

public class addGroup extends ActionBarActivity {

    private Spinner spinner;
    private EditText title;
    private EditText class_text;
    private Spinner location;
    private EditText addInfo;
    private IFirebaseSource mFirebaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        addListenerOnSpinnnerItemSeletion();
        title = (EditText)findViewById(R.id.title_textbox);
        class_text = (EditText)findViewById(R.id.class_textbox);
        location = (Spinner)findViewById(R.id.spinner);
        addInfo = (EditText)findViewById(R.id.additional_info_textbox);
        Firebase.setAndroidContext(this);
        mFirebaseSource = new FirebaseSource(null);

    }

    public void addListenerOnSpinnnerItemSeletion(){

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(),
//                        "OnItemSelectedListener :" + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Submit button was pressed
        if(id == R.id.submit_check){
            //Need to send things to Firebase
            String postTitle = title.getText().toString();
            String content = class_text.getText().toString();
            String userLocation = location.getSelectedItem().toString();
            String additionalInfo = addInfo.getText().toString();
            Post post = new Post(postTitle, content, userLocation, additionalInfo);
            mFirebaseSource.AddPost(post);
            //post now has id set.
            Toast.makeText(addGroup.this, "Submit Clicked!", Toast.LENGTH_SHORT).show();
            //then close the app
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
