package com.example.omarapp.classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;

import static com.example.omarapp.database.TablesString.ProductTable.*;


public class place implements SqlInterface
{

    private String Name;
    private String Place;



    private String date;

    private String HourOfStart;
    private double TimeOfTour;

    private double Price;

    private int VisitNum;
    private int Pid;
    private int MaxVisit;
    private int CurrentVisit;


    private String  Tools;


    protected byte[] imageByte;

    @Override
    public String toString() {
        return Name;
    }

    public place(place p) {
        this.Pid = p.getPid();
        this.Name=p.getName();
        this.Place=p.getPlace();
        this.date=p.getDate();
        this.HourOfStart=p.getHourOfStart();
        this.TimeOfTour=p.getTimeOfTour();
        this.Price=p.getPrice();
        this.VisitNum=p.getVisitNum();
        this.MaxVisit=p.getMaxVisit();
        this.CurrentVisit=p.getCurrentVisit();
        this.Tools=p.getTools();
        this.imageByte=p.getImageByte();
    }
    public place(String Name,double TimeOfTour,String date,String HourOfStart,int MaxVisit,String Place,double Price,String Tools
    ,byte[]imageByte) {

        this.Name=Name;
        this.TimeOfTour=TimeOfTour;
        this.date=date;
        this.HourOfStart=HourOfStart;
        this.MaxVisit=MaxVisit;
        this.Place=Place;
        this.Price=Price;
        this.Tools=Tools;
        this.imageByte=imageByte;


    }
    public place(int id,String Name,double TimeOfTour,String date,String HourOfStart,int MaxVisit,String Place,double Price,String Tools
            ,byte[]imageByte) {

        this.Name=Name;
        this.TimeOfTour=TimeOfTour;
        this.date=date;
        this.HourOfStart=HourOfStart;
        this.MaxVisit=MaxVisit;
        this.Place=Place;
        this.Price=Price;
        this.Tools=Tools;
        this.imageByte=imageByte;
        this.Pid =id;
    }

    public place() {

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
        values.put(TIEOFTOUR, TimeOfTour);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_HOUROFSTART, HourOfStart);
        values.put(COLUMN_TOOLS, Tools);



// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_PLACE, null, values);

    }

    @Override

    public int Delete(SQLiteDatabase db, String id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id};
// Issue SQL statement.
        return db.delete(TABLE_PLACE, selection, selectionArgs);

    }

    @Override
    public int Update(SQLiteDatabase db, String id) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLACE_NAME, Name);
        values.put(COLUMN_PLACE_DESCRIPTION, Place);
        values.put(COLUMN_PRICE, Price);
        values.put(COLUMN_VISITS, VisitNum);
        values.put(COLUMN_MAXVISITS, MaxVisit);
        values.put(COLUMN_PLACE_IMAGE, imageByte);
        values.put(COLUMN_CURRENT_VISITS, CurrentVisit);
        values.put(TIEOFTOUR, TimeOfTour);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_HOUROFSTART, HourOfStart);
        values.put(COLUMN_TOOLS, Tools);

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
    //select product with this specific id
    public Cursor SelectById(SQLiteDatabase db, String id) {
        String[] projection = {
                BaseColumns._ID,
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
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = {id};

        Cursor c = db.query(
                TABLE_PLACE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null  );
        return c;
    }
    public void setName(String name) {
        Name = name;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public void setTimeOfTour(double timeOfTour) {
        TimeOfTour = timeOfTour;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setVisitNum(int visitNum) {
        VisitNum = visitNum;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public void setMaxVisit(int maxVisit) {
        MaxVisit = maxVisit;
    }

    public void setCurrentVisit(int currentVisit) {
        CurrentVisit = currentVisit;
    }

    public void setTools(String tools) {
        Tools = tools;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }



    public String getName() {
        return Name;
    }

    public double getTimeOfTour() {
        return TimeOfTour;
    }


    public int getVisitNum() {
        return VisitNum;
    }

    public int getPid() {
        return Pid;
    }

    public int getMaxVisit() {
        return MaxVisit;
    }

    public int getCurrentVisit() {
        return CurrentVisit;
    }

    public String getPlace() {
        return Place;
    }

    public double getPrice() {
        return Price;
    }

    public String getTools() {
        return Tools;
    }

    public byte[] getImageByte() {
        return imageByte;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHourOfStart() {
        return HourOfStart;
    }

    public void setHourOfStart(String hourOfStart) {
        HourOfStart = hourOfStart;
    }


}
