package com.example.marcin.fitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class RozplanujActivity extends ActionBarActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozplanuj);
        listView=(ListView)findViewById(R.id.listView);
        DbHandler dbHandler = new DbHandler(getApplicationContext());
        Cursor cursor = dbHandler.WyswietlWybrane(dbHandler);
        String kolumny[] = {DbHandler.COLUMN2_NAME_NAZWA, DbHandler.COLUMN2_NAME_ILOSC};
        int widoki[]={R.id.textViewNazwa, R.id.textViewIlosc};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item2, cursor, kolumny, widoki, 0);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rozplanuj, menu);
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

    public void ClickDodajRozplanuj(View view) {
        Intent intent = new Intent(RozplanujActivity.this, DodajDoWybranychActivity.class);
        startActivity(intent);
    }

    public void ClickRozpocznijRozplanuj(View view) {
        Intent intent = new Intent(RozplanujActivity.this, RozpocznijTreningActivity.class);
        startActivity(intent);
    }
}
