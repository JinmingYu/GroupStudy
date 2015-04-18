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

import appathon.groupstudy.R;

public class addGroup extends ActionBarActivity {

    private Spinner spinner;
    private EditText title;
    private EditText class_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        addListenerOnSpinnnerItemSeletion();
    }

    public void addListenerOnSpinnnerItemSeletion(){
        spinner = (Spinner)findViewById(R.id.spinner);
        //Waits for selection of items
        title = (EditText)findViewById(R.id.title_textbox);
        class_text = (EditText)findViewById(R.id.class_textbox);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener :" + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_group, menu);
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

            //then close the app
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}