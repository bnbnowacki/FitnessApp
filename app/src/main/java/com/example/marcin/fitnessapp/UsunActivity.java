package com.example.marcin.fitnessapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class UsunActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usun);
        ListView lista1 = (ListView)findViewById(R.id.listView);
        DbHandler dbHelp=new DbHandler(getApplicationContext());
        String kolumny[]= {DbHandler.COLUMN_NAME_NAZWA, DbHandler.COLUMN_NAME_KALORIE};
        int[] widoki={R.id.textViewNazwa, R.id.textViewKalorie};
        Cursor c = dbHelp.WyswietlWszystkie(dbHelp);
        SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, c, kolumny, widoki, 0);
        lista1.setAdapter(adapter1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usun, menu);
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
