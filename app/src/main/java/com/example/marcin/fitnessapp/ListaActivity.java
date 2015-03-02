package com.example.marcin.fitnessapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ListView lista=(ListView)findViewById(R.id.listView);
        DbHandler dbHandler=new DbHandler(getApplicationContext());
        String kolumny[]= {DbHandler.COLUMN_NAME_NAZWA, DbHandler.COLUMN_NAME_KALORIE};
        int[] widoki={R.id.textViewNazwa, R.id.textViewKalorie};
        Cursor c = dbHandler.WyswietlWszystkie(dbHandler);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, c, kolumny, widoki, 0);
        lista.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
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
