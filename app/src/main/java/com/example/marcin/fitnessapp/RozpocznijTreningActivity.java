package com.example.marcin.fitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class RozpocznijTreningActivity extends ActionBarActivity {
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozpocznij_trening);
        text1=(TextView)findViewById(R.id.textViewTresc);
        text2=(TextView)findViewById(R.id.textKalorie);
        int w=0;
        DbHandler dbHandler = new DbHandler(getApplicationContext());
        Cursor c = dbHandler.WyswietlWybrane(dbHandler);
        c.moveToFirst();
        do{
            w=w+(Integer.parseInt(c.getString(2))*Integer.parseInt(c.getString(3)));
            text1.setText(text1.getText()+"Ćwiczenie: "+c.getString(1)+"  Ilość: "+c.getString(3)+"\n");
        }while (c.moveToNext());
        text2.setText("Spalono kalorii łącznie: "+w);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rozpocznij_trening, menu);
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

    public void ClickZakonczTrening(View view) {
        Intent intent = new Intent(RozpocznijTreningActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
