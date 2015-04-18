package appathon.groupstudy.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import appathon.groupstudy.R;

public class filterActivity extends ActionBarActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        listView = (ListView)findViewById(R.id.list);
        //Should load the list of strings into arrry
        String[]classes = getResources().getStringArray(R.array.classes_array);
        //Puts the choices into a multiple choice list
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,classes);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id== R.id.confirm){
            SparseBooleanArray checked = listView.getCheckedItemPositions();
            ArrayList<String> selectedItems = new ArrayList<String>();

            //TODO:Edit this code for Firebase updating if possible
            for(int i=0;i<checked.size();i++){
                //Item position in adapter
                int position = checked.keyAt(i);

                if(checked.valueAt(i)){
                    selectedItems.add(adapter.getItem(position));
                }
            }
            String[] outputStrArr = new String[selectedItems.size()];
            for(int i =0; i< selectedItems.size();i++){
                outputStrArr[i] = selectedItems.get(i);
            }
            //TODO:could posibily turn in to post item and reload
            //Takes us back to the main intent and closes this one
            Intent myIntent = new Intent(this,MainActivity.class);
            startActivity(myIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
