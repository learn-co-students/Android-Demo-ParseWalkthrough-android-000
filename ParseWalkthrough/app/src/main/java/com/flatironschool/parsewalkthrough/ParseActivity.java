package com.flatironschool.parsewalkthrough;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;



public class ParseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);

        Parse.initialize(this, "Wzc1f4Av7RmhGFcXopNnZFj9nZW6EDQYxqgd8Dv9", "F6Go8rvz5ogMjaVTd1h8ICK9OHo9OxkRXNOTUDmL");
        Parse.enableLocalDatastore(this);


        ParseObject alObject = new ParseObject("TestObject");
        alObject.put("name", "Al");
        alObject.put("favorite_animal", "dog");
        alObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(ParseActivity.this, "Save completed", Toast.LENGTH_SHORT).show();
            }
        });

        ParseObject brandonObject = new ParseObject("TestObject");
        brandonObject.put("name", "Brandon");
        brandonObject.put("favorite_animal", "cat");
        brandonObject.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        query.whereEqualTo("name", "Al");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null){
                    for (ParseObject alObject: parseObjects){
                        alObject.put("name", "John");
                        alObject.pinInBackground();
                        alObject.saveInBackground();

                    }
                }
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject>query1 = ParseQuery.getQuery("StockData");
        query1.whereEqualTo("user", currentUser);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse, menu);
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
