package com.acmegrade.majorproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    //database name

    private static final String DATABASE_NAME="UserManager.db";
    //User table name

    private static final  String TABLE_USER="user";
    //user table column name

    private static final String COLUMN_USER_ID="user_id";

    private static final String COLUMN_USER_NAME="user_name";

    private static final String COLUMN_USER_EMAIL="user_email";

    private static final String COLUMN_USER_PASSWORD="user_password";


//creating a query table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";


    // for dropping table sql query

    private String DROP_USER_TABLE="DROP TABLE IF EXISTS "+TABLE_USER;

    public databasehelper( Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop user table if it is there
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);

    }

    public void adduser( user User)
    {
        SQLiteDatabase datab=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USER_NAME, User.getName());
        values.put(COLUMN_USER_EMAIL, User.getEmail());
        values.put(COLUMN_USER_PASSWORD, User.getPassword());
        datab.insert(TABLE_USER, null, values);
        datab.close();

    }

    public boolean checkUser(String email)
    {
        String[] vertcol={COLUMN_USER_ID};

        SQLiteDatabase datab=this.getReadableDatabase();
        String select=COLUMN_USER_EMAIL+"= ?";
        String selectArgs[]={email};
        Cursor cursor=datab.query(TABLE_USER, vertcol, select, selectArgs, null, null, null);
        int cursorcount= cursor.getCount();
        cursor.close();
        datab.close();
        if(cursorcount > 0){
            return true;
        }
        return false;


    }
    public boolean checkUser(String email,String password)
    {
        String[] vertcol={COLUMN_USER_ID};

        SQLiteDatabase datab=this.getReadableDatabase();
        String select=COLUMN_USER_EMAIL+"= ?"+" AND "+COLUMN_USER_PASSWORD+" = ?";
        String selectArgs[]={email,password};
        Cursor cursor=datab.query(TABLE_USER, vertcol, select, selectArgs, null, null, null);
        int cursorcount= cursor.getCount();
        cursor.close();
        datab.close();
        if(cursorcount > 0){
            return true;
        }
        return false;


    }

    public user getUserData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        user User= new user();
        String[] columns = {COLUMN_USER_NAME, COLUMN_USER_EMAIL};
        String selection = COLUMN_USER_EMAIL + "=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_USER_NAME);
            int emailColumnIndex = cursor.getColumnIndex(COLUMN_USER_EMAIL);

            if (nameColumnIndex != -2 && emailColumnIndex != -2) {
                User.setName(cursor.getString(nameColumnIndex));
                User.setEmail(cursor.getString(emailColumnIndex));
            } else {
                // Handle the case where columns are not found
                User.setName("N/A");
                User.setEmail("N/A");
            }
        } else {
            // Handle the case where the email is not found
            User.setName("N/A");
            User.setEmail("N/A");
        }

        cursor.close();
        db.close();
        return User;
    }

//    public user getUserData(String email) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        user User  = new user();
//        String[] columns = {COLUMN_USER_NAME, COLUMN_USER_EMAIL};
//        String selection = COLUMN_USER_EMAIL + "=?";
//        String[] selectionArgs = {email};
//        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
//        if (cursor.moveToFirst()) {
//            User.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
//            User.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
//        }
//        else{
//            User.setName("N/A");
//            User.setEmail("N/A");
//        }
//        cursor.close();
//        db.close();
//        return User;
//
//    }
//





}
