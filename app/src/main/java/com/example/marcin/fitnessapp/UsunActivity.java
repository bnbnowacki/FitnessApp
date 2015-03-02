package com.example.marcin.fitnessapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class UsunActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usun);
        ListView lista1 = (ListView)findViewById(R.id.ListView);
        final DbHandler dbHelp=new DbHandler(getApplicationContext());
        String kolumny[]= {DbHandler.COLUMN_NAME_NAZWA, DbHandler.COLUMN_NAME_KALORIE};
        int[] widoki={R.id.textViewNazwa, R.id.textViewKalorie};
        Cursor c = dbHelp.WyswietlWszystkie(dbHelp);
        SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, c, kolumny, widoki, 0);
        lista1.setAdapter(adapter1);
        lista1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView listView, View view, int position, long id){
                Cursor cursor =(Cursor)listView.getItemAtPosition(position);
                String nazwa = cursor.getString(1);
                Toast.makeText(getApplicationContext(), "Usunięto: "+nazwa, Toast.LENGTH_LONG).show();
                SQLiteDatabase db = dbHelp.getWritableDatabase();
                String where = DbHandler.COLUMN_NAME_NAZWA+" = ?";
                String[] whereArgs={cursor.getString(1)};
                db.delete(DbHandler.TABLE_NAME, where, whereArgs);
                finish();
            }
        });

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
