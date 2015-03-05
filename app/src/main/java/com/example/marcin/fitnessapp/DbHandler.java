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

    //tabela ze wszystkimi cwiczeniami
    public static final String TABLE1_NAME="cwiczenia";
    public static final String COLUMN1_NAME_ID="_id";
    public static final String COLUMN1_NAME_NAZWA="nazwa";
    public static final String COLUMN1_NAME_KALORIE="kalorie";

    //tabela z wybranymi cwiczeniami
    public static final String TABLE2_NAME="wybrane";
    public static final String COLUMN2_NAME_ID="_id";
    public static final String COLUMN2_NAME_NAZWA="nazwa";
    public static final String COLUMN2_NAME_KALORIE="kalorie";
    public static final String COLUMN2_NAME_ILOSC="ilosc";

    public final String CREATE_QUERY1="CREATE TABLE "+TABLE1_NAME+"("+COLUMN1_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN1_NAME_NAZWA+" TEXT, "
            +COLUMN1_NAME_KALORIE+" INTEGER);";
    public final String CREATE_QUERY2="CREATE TABLE "+TABLE2_NAME+"("+COLUMN2_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN2_NAME_NAZWA+" TEXT, "
            +COLUMN2_NAME_KALORIE+" INTEGER, "+COLUMN2_NAME_ILOSC+" INTEGER);";

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("BazaSQL", "Stworzono bazę danych");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY1);
        Log.d("BazaSQL", "Stworzono tabelę cwiczenia");
        db.execSQL(CREATE_QUERY2);
        Log.d("BasaSQL", "Stworzono tabele wybrane");
    }

    public void dodajWpis(DbHandler dbHandler, String nazwa, int kalorie){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1_NAME_NAZWA, nazwa);
        values.put(COLUMN1_NAME_KALORIE, kalorie);
        db.insert(TABLE1_NAME, null, values);
        Log.d("BazaSQL", "Dodano wpis");
    }

    public Cursor WyswietlWszystkie(DbHandler dbHandler){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String[] kolumny = {COLUMN1_NAME_ID, COLUMN1_NAME_NAZWA, COLUMN1_NAME_KALORIE};
        Cursor c = db.query(TABLE1_NAME, kolumny, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public void WypelnijTabeleWybrane(DbHandler dbHandler){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor c = WyswietlWszystkie(dbHandler);
        c.moveToFirst();
        do{
            ContentValues values = new ContentValues();
            values.put(COLUMN2_NAME_NAZWA, c.getString(1));
            values.put(COLUMN2_NAME_KALORIE, c.getString(2));
            values.put(COLUMN2_NAME_ILOSC, 0);
            db.insert(TABLE2_NAME, null, values);
            Log.d("BazaSQL", "Dodano "+c.getString(1)+" do tabeli wybrane");
        }while (c.moveToNext());
    }

    public Cursor WyswietlWybrane(DbHandler dbHandler){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String[] kolumny= {COLUMN2_NAME_ID, COLUMN2_NAME_NAZWA, COLUMN2_NAME_KALORIE, COLUMN2_NAME_ILOSC};
        Cursor cursor = db.query(TABLE2_NAME, kolumny, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
