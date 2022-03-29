package com.example.easyteamup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBEvent extends SQLiteOpenHelper {

    public static final String DBNAME = "Event.db";

    public DBEvent(Context context) {
        super(context, "Event.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table events(columnNum INTEGER PRIMARY KEY AUTOINCREMENT, eventCreator TEXT, eventName TEXT, eventType TEXT, eventStartTime INT, eventEndTime INT, eventMonth TEXT, eventDate INT, eventYear INT, signupDueMonth TEXT,signupDueDate INT,  signupDueYear INT, signupDueTime INT, privateOrPublic TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists events");
    }

    public void insertNewEventData(String username, String eventName,
                                   String eventType, int eventStartTime,
                                   int eventEndTime, String eventMonth,
                                   int eventDate, int eventYear,
                                   String signupDueMonth, int signupDueDate,
                                   int signupDueYear, int signupDueTime, String privateOrPublic) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventCreator", username);
        contentValues.put("eventType", eventType);
        contentValues.put("eventName", eventName);
        contentValues.put("eventStartTime", eventStartTime);
        contentValues.put("eventEndTime", eventEndTime);
        contentValues.put("eventMonth", eventMonth);
        contentValues.put("eventDate", eventDate);
        contentValues.put("eventYear", eventYear);
        contentValues.put("signupDueMonth", signupDueMonth);
        contentValues.put("signupDueDate", signupDueDate);
        contentValues.put("signupDueYear", signupDueYear);
        contentValues.put("signupDueTime", signupDueTime);
        contentValues.put("privateOrPublic", privateOrPublic);


        MyDB.insert("events", null, contentValues);
    }
}
