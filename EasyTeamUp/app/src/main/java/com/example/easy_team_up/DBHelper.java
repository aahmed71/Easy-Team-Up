package com.example.easy_team_up;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Invitations (id INTEGER PRIMARY KEY AUTOINCREMENT, eventId INT, userId INT)");
        DB.execSQL("create Table RSVPs (id INTEGER PRIMARY KEY AUTOINCREMENT, eventId INT, userId INT)");
        DB.execSQL("create Table Notifications (id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, userId INT)");
        //change to startTimestamp, endTimestamp string
        DB.execSQL("create Table Events(id INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, eventName TEXT, eventType TEXT, eventStartTime INT, " +
                "eventEndTime INT, eventMonth TEXT, eventDate INT, eventYear INT, signupDueMonth TEXT,signupDueDate INT,  signupDueYear INT, signupDueTime INT, " +
                "privateOrPublic TEXT, eventDescription TEXT, location TEXT)");
        DB.execSQL("create Table Users(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT, age INT)");
        DB.execSQL("insert into Users (username, password) VALUES ('belle', '123'), ('Bob', '123'), ('Cora', '123'), " +
                "('testUser3', '123'), ('testUser4', '123')");
        DB.execSQL("insert into Events (id, userId, eventName, eventType, eventStartTime, eventEndTime, eventMonth, eventDate, " +
                "EventYear, signupDueMonth, signupDueDate, signupDueYear, signupDueTime, privateOrPublic, eventDescription, location) " +
                "VALUES (0, 4, 'Event 1', 'study', 6, 7, 1, 1, 2022, 2, 2, 2022, 5, 'public', 'asdf', 'USC'), " +
                "(1, 4, 'Event 2', 'study', 6, 7, 1, 1, 2022, 2, 2, 2022, 5, 'public', 'Group Study session', '3115 Orchard Ave., Los Angeles, CA 90007')," +
                "(2, 1, 'Event 3', 'study', 6, 7, 1, 1, 2022, 2, 2, 2022, 5, 'public', 'Working on project', '651 W 35th St, Los Angeles, CA 90089')");
        DB.execSQL("insert into Invitations (eventId, userId) values (0, 4), (1, 4), (2, 5)");
        DB.execSQL("insert into RSVPs (eventId, userId) values (2, 4)");
        DB.execSQL("insert into Notifications (description, userId) values ('Test Notification', 4)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Invitations");
        DB.execSQL("drop Table if exists Events");
        DB.execSQL("drop Table if exists RSVPs");
        DB.execSQL("drop Table if exists Users");
        DB.execSQL("drop Table if exists Notifications");
    }

    public void addEventToRSVP(Event2 event){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", event.getUserID());
        contentValues.put("eventId", event.getEventID());
        DB.insert("RSVPs", null, contentValues);
    }
    public Boolean acceptInvitation (Invite invite)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.print("Deleting invitation");
        Cursor cursor = DB.rawQuery("Select * from Invitations", null);
        if (cursor.getCount() > 0) {
            cursor.close();
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
// just random changes so i can push this gain
    public Boolean deleteInvitation (Invite invite)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.print("Deleting invitation");
        Cursor cursor = DB.rawQuery("Select * from Invitations", null);
        if (cursor.getCount() > 0) {
            cursor.close();
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
    public Boolean deleteNotification (Integer id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.print("Deleting notification");
        Cursor cursor = DB.rawQuery("Select * from Notifications", null);
        if (cursor.getCount() > 0) {
            //delete invitation and add to rsvp
            long deleteResult = DB.delete("Notifications", "id=?", new String[]{String.valueOf(id)});
            if (deleteResult != -1) {
                System.out.print("delete notification success");
                return true;
            } else {
                System.out.print("delete notification fail");
                return false;
            }
        } else {
            return false;
        }
    }

    // insert into events database
    public void insertNewEventData(Integer userId, String eventName,
            String eventType, int eventStartTime,
    int eventEndTime, String eventMonth,
    int eventDate, int eventYear,
    String signupDueMonth, int signupDueDate,
    int signupDueYear, int signupDueTime, String privateOrPublic, String eventDescription) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //create new event date, start timestamp, end timestamp, signup due date, signup due time
        //create new event date
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String d = eventDate + "/" + eventMonth + "/" + eventYear;
//        Date date = dateFormat.parse(d);
//        long time = date.getTime();
//        Timestamp eventDate = new Timestamp(time);
        contentValues.put("userId", userId);
        contentValues.put("eventType", eventType);
        contentValues.put("eventName", eventName);
        contentValues.put("eventStartTime", eventStartTime);
        contentValues.put("eventEndTime", eventEndTime);
        contentValues.put("eventMonth", eventMonth);
//        contentValues.put("eventDate", eventDate);
        contentValues.put("eventYear", eventYear);
        contentValues.put("signupDueMonth", signupDueMonth);
        contentValues.put("signupDueDate", signupDueDate);
        contentValues.put("signupDueYear", signupDueYear);
        contentValues.put("signupDueTime", signupDueTime);
        contentValues.put("privateOrPublic", privateOrPublic);
        contentValues.put("eventDescription", eventDescription);

        MyDB.insert("Events", null, contentValues);
    }

    /*****************/
    public void addToInvitations (Integer eventId, Integer userId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", eventId);
        contentValues.put("userId", userId);
        MyDB.insert("Invitations", null, contentValues);
    }

    // Insert into  login database
    public Integer insertNewUserData(String username, String password, String email, String age) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("age", age);
        long result = MyDB.insert("users", null, contentValues);
        Cursor userId = MyDB.rawQuery("Select id from users where username = ? and password = ?", new String[] {username, password});
        if (result == -1) return -1;
        else{
            userId.moveToFirst();
            return userId.getInt(0);
        }
    }

    public Boolean insertNotification(String description, Integer userId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("description", description);
        long result = MyDB.insert("Notifications", null, contentValues);
        if (result == -1){
            return false;
        }
        return true;
    }

    public Boolean checkUserName(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0) {
            return true;
        }
        else return false;
    }

    public Cursor checkUserNamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0) {
            return cursor;
        }
        else return null;
    }
    /*************************************/
    public Cursor getNameFromUserId(Integer userId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users WHERE id = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public Cursor returnUserInfo (Integer id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where id = ?", new String[] {String.valueOf(id)});
        return cursor;
    }

    public Cursor getIdfromUsername (String userName){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users WHERE username = ?", new String[]{String.valueOf(userName)});
        return cursor;
    }

    public Cursor getInvitations(Integer userId) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Invitations WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }
    public Cursor getNotifications(Integer userId) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notifications WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }
    public Cursor getMyEvents(Integer userId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Events WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public Cursor getPublicEvents(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Events WHERE privateOrPublic = ?", new String[]{"public"});
        return cursor;
    }

    public Boolean updateMyEvent(Integer eventId, String eventName, String eventType, Integer startTime,
                                 Integer endTime, String month, Integer date, Integer year, String location, String privateEvent, String description)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventName", eventName);
        contentValues.put("eventType", eventType);
        contentValues.put("eventStartTime", startTime);
        contentValues.put("eventEndTime", endTime);
        contentValues.put("eventMonth", month);
        contentValues.put("eventDate", date);
        contentValues.put("eventYear", year);
        contentValues.put("location", location);
        contentValues.put("privateOrPublic", privateEvent);
        contentValues.put("eventDescription", description);
        Cursor cursor = DB.rawQuery("Select * from Events where id = ?", new String[]{String.valueOf(eventId)});
        if (cursor.getCount() > 0) {
            long result = DB.update("Events", contentValues, "id=?", new String[]{String.valueOf(eventId)});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getRSVPs(Integer userId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from RSVPs WHERE userId = ?", new String[]{String.valueOf(userId)});
        return cursor;
    }
    public Cursor getRSVPsByEventId(Integer eventId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select userId from RSVPs WHERE eventId = ?", new String[]{String.valueOf(eventId)});
        return cursor;
    }

    public Cursor getEventById(Integer eventId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Events WHERE id = ?", new String[]{String.valueOf(eventId)});
        return cursor;
    }
}