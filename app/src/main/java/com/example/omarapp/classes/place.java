package com.example.omarapp.classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Arrays;

import static com.example.omarapp.database.TablesString.ProductTable.*;


public class place implements SqlInterface
{
    private String Name;

    private double TimeOfTour;
     private String Date;
     private String HourOfStart;
    private int VisitNum;

    private int Pid;
    private int MaxVisit;
    private int CurrentVisit;
    private String Place;

    private double Price;
    private String [] Tools;


    protected byte[] imageByte;
    public place(int visitnum ,String Name,int Pid,double TimeOfTour,String Date,String HourOfStart,int MaxVisit,int CurrentVisit,String Place,double Price,String[] Tools
    ,byte[]imageByte) {
        this.VisitNum = visitnum;
        this.Name=Name;
        this.Pid=Pid;
        this.TimeOfTour=TimeOfTour;
        this.Date=Date;
        this.HourOfStart=HourOfStart;
        this.MaxVisit=MaxVisit;
        this.CurrentVisit=CurrentVisit;
        this.Place=Place;
        this.Price=Price;
        this.Tools=Tools;
        this.imageByte=imageByte;


    }

    public long Add(SQLiteDatabase db) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLACE_NAME, Name);
        values.put(COLUMN_PLACE_DESCRIPTION, Place);
        values.put(COLUMN_PRICE, Price);
        values.put(COLUMN_VISITS, VisitNum);
        values.put(COLUMN_MAXVISITS, MaxVisit);
        values.put(COLUMN_PLACE_IMAGE, imageByte);
        values.put(COLUMN_CURRENT_VISITS, CurrentVisit);
        values.put(TIEOFTOUR, TIEOFTOUR);
        values.put(COLUMN_DATE, Date);
        values.put(COLUMN_HOUROFSTART, HourOfStart);
        values.put(COLUMN_TOOLS, Arrays.toString(Tools));



// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_PLACE, null, values);

    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_PLACE, selection, selectionArgs);

    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLACE_NAME, Name);
        values.put(COLUMN_PLACE_DESCRIPTION, Place);
        values.put(COLUMN_PRICE, Price);
        values.put(COLUMN_VISITS, VisitNum);
        values.put(COLUMN_MAXVISITS, MaxVisit);
        values.put(COLUMN_PLACE_IMAGE, imageByte);
        values.put(COLUMN_CURRENT_VISITS, CurrentVisit);
        values.put(TIEOFTOUR, TIEOFTOUR);
        values.put(COLUMN_DATE, Date);
        values.put(COLUMN_HOUROFSTART, HourOfStart);
        values.put(COLUMN_TOOLS, Arrays.toString(Tools));

// Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { id+"" };

        return  db.update(
                TABLE_PLACE,
                values,
                selection,
                selectionArgs);

    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
         TABLE_PLACE,
       COLUMN_PLACE_NAME,
         COLUMN_PLACE_DESCRIPTION,
        COLUMN_PLACE_IMAGE,
        COLUMN_MAXVISITS,
        COLUMN_CURRENT_VISITS,
         TIEOFTOUR,
        COLUMN_DATE,
         COLUMN_HOUROFSTART,
         COLUMN_TOOLS,
         COLUMN_PRICE,
        COLUMN_VISITS
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_PLACE,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }


}
