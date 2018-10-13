package com.example.rahul.srmhackandroid.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    /*Name of database*/
    public static final String DATABASE_NAME = "Dyslec_Database.db";
    /*database version*/
    public static final int DATABASE_VERSION = 1;

    /*Public Constructor*/
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create the table
       String SQL_CREATE_TABLE = "CREATE TABLE " + DyslecContract.DyslecEntry.TABLE_NAME + " ( "
                + DyslecContract.DyslecEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DyslecContract.DyslecEntry.TEXT + " TEXT NOT NULL, "
                + DyslecContract.DyslecEntry.SRC + " TEXT, "
                + DyslecContract.DyslecEntry.DATE + " TEXT, "
                + DyslecContract.DyslecEntry.TIME + " TEXT, "
               + DyslecContract.DyslecEntry.UPDATE + " INTEGER);";
       db.execSQL(SQL_CREATE_TABLE);
       }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
