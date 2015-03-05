package com.example.marcin.fitnessapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class DodajDoWybranychActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_do_wybranych);
        final ListView listView = (ListView)findViewById(R.id.listView);
        final DbHandler dbHandler = new DbHandler(getApplicationContext());
        Cursor c = dbHandler.WyswietlWszystkie(dbHandler);
        String[] kolumny = {dbHandler.COLUMN1_NAME_NAZWA, dbHandler.COLUMN1_NAME_KALORIE};
        int[] widoki={R.id.textViewNazwa, R.id.textViewKalorie};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, c, kolumny, widoki, 0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                SQLiteDatabase db = dbHandler.getWritableDatabase();
                String[] columns = {DbHandler.COLUMN2_NAME_NAZWA, DbHandler.COLUMN2_NAME_ILOSC};
                Cursor c = (Cursor)parent.getItemAtPosition(position);
                String where = DbHandler.COLUMN2_NAME_NAZWA+" = ?";
                String[] whereArgs = {c.getString(1)};
                Cursor c2 = db.query(DbHandler.TABLE2_NAME, columns, where, whereArgs, null, null, null);
                ContentValues values = new ContentValues();
                values.put(DbHandler.COLUMN2_NAME_ILOSC, c2.getString(1)+1);
                db.update(DbHandler.TABLE2_NAME, values, where, whereArgs);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_do_wybranych, menu);
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
