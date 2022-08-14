package com.example.app424;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(useremail TEXT primary Key,password TEXT,phon TEXT)");

        MyDB.execSQL("create Table Notes(title TEXT primary Key,discription TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("drop Table if exists users");
    }


    public Boolean insertData(String useremail ,String password,String phon){

         SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
         ContentValues contentValues =new ContentValues();
         contentValues.put("useremail ",useremail);
         contentValues.put("password",password);
         contentValues.put("phon",phon);
         long result =sqLiteDatabase.insert("users",null,contentValues);
         if(result==-1)
             return false;
         else
             return true;
    }

    public Boolean checkuseremail(String useremail){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from users where useremail = ? ",new String[]{useremail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean checkuserpassword(String useremail,String password   ){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from users where useremail = ? and password=? ",new String[]{useremail,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean addNots(String title ,String discription){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("title",title);
        contentValues.put("discription",discription);
        long result =sqLiteDatabase.insert("Notes",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }

    public Boolean DeletNotes(String title){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Delete from Notes where title = ?  ",new String[]{title});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }


}
