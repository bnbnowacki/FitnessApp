package com.example.marcin.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nowacki on 2015-03-02.
 */
public class DbHandler extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="baza_danych";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="cwiczenia";
    public static final String COLUMN_NAME_ID="_id";
    public static final String COLUMN_NAME_NAZWA="nazwa";
    public static final String COLUMN_NAME_KALORIE="kalorie";
    public final String CREATE_QUERY="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME_NAZWA+" TEXT, "
            +COLUMN_NAME_KALORIE+" INTEGER);";

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("BazaSQL", "Stworzono bazę danych");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("BazaSQL", "Stworzono tabelę");
    }

    public void dodajWpis(DbHandler dbHandler, String nazwa, int kalorie){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAZWA, nazwa);
        values.put(COLUMN_NAME_KALORIE, kalorie);
        db.insert(TABLE_NAME, null, values);
        Log.d("BazaSQL", "Dodano wpis");
    }

    public Cursor WyswietlWszystkie(DbHandler dbHandler){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String[] kolumny = {COLUMN_NAME_ID, COLUMN_NAME_NAZWA, COLUMN_NAME_KALORIE};
        Cursor c = db.query(TABLE_NAME, kolumny, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
