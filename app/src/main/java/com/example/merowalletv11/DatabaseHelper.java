package com.example.merowalletv11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="MW.db";
    public static final String TABLE_NAME ="SIGN_UP";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="FIRSTNAME";
    public static final String COL_3 ="LASTNAME";
    public static final String COL_4 ="USERNAME";
    public static final String COL_5 ="PASSWORD";
    public static final String COL_6 ="CONFIRMPASSWORD";
    public static final String COL_7 ="PHONENUMBER";
    public static final String COL_8 ="ADDRESS";
    public static final String COL_9 ="EMAIL";


    //whenever this construtor is called the database is created.
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT NOT NULL,LASTNAME TEXT NOT NULL,USERNAME TEXT NOT NULL UNIQUE ,PASSWORD TEXT NOT NULL,CONFIRMPASSWORD TEXT NOT NULL,PHONENUMBER TEXT,ADDRESS TEXT,EMAIL TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String  firstname ,String lastname ,String username, String password ,String confirmpassword,String phonenumber,String address,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, firstname);
//        String fname= contentValues.getAsString(COL_2);
////        if(fname == null)
////        {
////            throw new IllegalArgumentException("First name can't be null");
////        }
        contentValues.put(COL_3, lastname);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5,password);
        contentValues.put(COL_6,confirmpassword);
        contentValues.put(COL_7,phonenumber);
        contentValues.put(COL_8,address);
        contentValues.put(COL_9,email);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    //retrieve data from database

    public Cursor getAllData()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }



}
