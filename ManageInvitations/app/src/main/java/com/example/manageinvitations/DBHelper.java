package com.example.manageinvitations;
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
        DB.execSQL("create Table Invitations (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, userId INT)");
        DB.execSQL("insert into Invitations (title, userId) VALUES ('study', 1), ('hang out', 1), ('party', 2)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Invitations");
    }
//    public Boolean insertInvitation(String title)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("title", title);
//        long result=DB.insert("Invitations", null, contentValues);
//        if(result==-1){
//            return false;
//        }else{
//            return true;
//        }
//    }
//    public Boolean updateuserdata(String name, String contact, String dob)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("contact", contact);
//        contentValues.put("dob", dob);
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
//        if (cursor.getCount() > 0) {
//            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
    public Boolean deleteInvitation (Integer eventId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Invitations", null);
        if (cursor.getCount() > 0) {
            long result = DB.delete("Invitations", "id=?", null);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData(Integer userId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
//        String uId = userId.toString();
        Cursor cursor = DB.rawQuery("Select * from Invitations WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }
}