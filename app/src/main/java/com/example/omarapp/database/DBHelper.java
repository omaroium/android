package com.example.omarapp.database;



import static com.example.omarapp.database.QueryString.*;

import android.content.Context;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//controls with database
public  class DBHelper {
    //define database name and its version
    private static final String DATABASE_NAME = "OmarDb.db";

    private static final int DATABASE_VERSION = 1;


    private Context mContext;
    private DataBaseHelper dbhelper;
    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        return db;
    }

    private class DataBaseHelper extends SQLiteOpenHelper {
        DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(SQL_DELETE_PRODUCT);
            onCreate(sqLiteDatabase);
        }

    }
    public void Reset(){

        dbhelper.onUpgrade(db,1,1);
    }
    public DBHelper(Context context){
        mContext = context;
        dbhelper = new DataBaseHelper(mContext);
    }
    // open the database to write in it
    public DBHelper OpenWriteAble() throws SQLException{
        db = dbhelper.getWritableDatabase();
        return this;
    }
    //open the database to read from it
    public DBHelper OpenReadAble() throws SQLException{
        db = dbhelper.getReadableDatabase();
        return this;
    }
// closes the database
    public void Close(){
        dbhelper.close();
    }

}