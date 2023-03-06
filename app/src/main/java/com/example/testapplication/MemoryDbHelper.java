package com.example.testapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoryDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "memories.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MemoryContract.MemoryEntry.TABLE_NAME + " (" +
                    MemoryContract.MemoryEntry._ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_IMAGE + TEXT_TYPE + " )";
    private static final String SQL_INSERT_ENTRIES =
            "INSERT INTO " + MemoryContract.MemoryEntry.TABLE_NAME +" ( " +
            MemoryContract.MemoryEntry.COLUMN_TITLE + COMMA_SEP +
            MemoryContract.MemoryEntry.COLUMN_IMAGE + " ) " +
            "VALUES ( ?, ? )";


    public MemoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }


  // private boolean checkDatabase() {
  //        SQLiteDatabase checkDB = null;

  //        try {
  //            checkDB = SQLiteDatabase.openDatabase(DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
  //            checkDB.close();
  //        } catch (SQLiteException e) {
  //            // database doesn't exist yet
  //        }

  //        return checkDB != null;
  //    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //This method has been intentionally left empty. There is only one version of the database.
    }

    public Cursor readAllMemories() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                MemoryContract.MemoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public boolean addMemory(Memory memory) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MemoryContract.MemoryEntry.COLUMN_TITLE, memory.getTitle());
        values.put(MemoryContract.MemoryEntry.COLUMN_IMAGE, memory.getImageAsString());

        return db.insert(MemoryContract.MemoryEntry.TABLE_NAME, null, values) != -1;
    }
}
