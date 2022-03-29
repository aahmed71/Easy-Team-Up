package com.example.easy_team_up;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Invitations.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Events (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, userId INT, place TEXT)");
        DB.execSQL("insert into Events (title, userId, place) VALUES ('study', 3, 'house'), ('party', 1, 'beach'), ('hang out', 2, 'library')");
        DB.execSQL("create Table Invitations (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, eventId INT, userId INT)");
        DB.execSQL("insert into Invitations (title, eventId, userId) VALUES ('study', 1, 1), ('party', 2, 1), ('hang out', 3, 1)");
        DB.execSQL("create Table RSVPs (id INTEGER PRIMARY KEY AUTOINCREMENT, eventId INT, userId INT)");
        DB.execSQL("create Table Users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, email TEXT)");
        DB.execSQL("insert into Users (name, email, password) VALUES ('Belle', 'belle@usc.edu', '123'), ('Bob', 'bob@usc.edu', '123'), ('Cora', 'cora@usc.edu', '123')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Invitations");
        DB.execSQL("drop Table if exists Events");
        DB.execSQL("drop Table if exists RSVPs");
        DB.execSQL("drop Table if exists Users");
    }
    public Boolean acceptInvitation (Invite invite)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.print("Deleting invitation");
        Cursor cursor = DB.rawQuery("Select * from Invitations", null);
        if (cursor.getCount() > 0) {
            //delete invitation and add to rsvp
            long deleteResult = DB.delete("Invitations", "id=?", new String[]{String.valueOf(invite.inviteId)});
            ContentValues contentValues = new ContentValues();
            contentValues.put("userId", invite.userId);
            contentValues.put("eventId", invite.eventId);
            long addResult=DB.insert("RSVPs", null, contentValues);
            if (deleteResult != -1 && addResult != -1) {
                System.out.print("delete success");
                return true;
            } else {
                System.out.print("delete fail");
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteInvitation (Invite invite)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.print("Deleting invitation");
        Cursor cursor = DB.rawQuery("Select * from Invitations", null);
        if (cursor.getCount() > 0) {
            //delete invitation and add to rsvp
            long deleteResult = DB.delete("Invitations", "id=?", new String[]{String.valueOf(invite.inviteId)});
            if (deleteResult != -1) {
                System.out.print("delete success");
                return true;
            } else {
                System.out.print("delete fail");
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteRSVP (Invite rsvp)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.print("Deleting invitation");
        Cursor cursor = DB.rawQuery("Select * from RSVPs", null);
        if (cursor.getCount() > 0) {
            //delete invitation and add to rsvp
            long deleteResult = DB.delete("RSVPs", "id=?", new String[]{String.valueOf(rsvp.inviteId)});
            if (deleteResult != -1) {
                System.out.print("delete rsvp success");
                return true;
            } else {
                System.out.print("delete rsvp fail");
                return false;
            }
        } else {
            return false;
        }
    }
    public Cursor getNameFromUserId(Integer userId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users WHERE id = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public Cursor getInvitations(Integer userId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Invitations WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public Cursor getRSVPs(Integer userId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from RSVPs WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public Cursor getEventById(Integer eventId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Events WHERE id = ?", new String[]{String.valueOf(eventId)});
        return cursor;
    }
}