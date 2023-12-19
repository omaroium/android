package com.example.omarapp.classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import static com.example.omarapp.database.TablesString.CartTable.*;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_CURRENT_VISITS;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_DATE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_HOUROFSTART;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_MAXVISITS;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_DESCRIPTION;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_IMAGE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_NAME;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PRICE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_TOOLS;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_VISITS;
import static com.example.omarapp.database.TablesString.ProductTable.TABLE_PLACE;
import static com.example.omarapp.database.TablesString.ProductTable.TIEOFTOUR;


public class Cart implements SqlInterface{
    private String uid;
    private int pid;
    private int quantitiy;

    public Cart(String  uid, int pid, int quantitiy) {
        this.uid = uid;
        this.pid = pid;
        this.quantitiy = quantitiy;
    }

    @Override
    public long Add(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, pid);
        values.put(COLUMN_USER_ID, uid);
        values.put(COLUMN_PRODUCT_QUANTITY, quantitiy);
// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_CART, null, values);
    }

    @Override
    public int Delete(SQLiteDatabase db, String id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id};
// Issue SQL statement.
        return db.delete(TABLE_CART, selection, selectionArgs);
    }

    @Override
    public int Update(SQLiteDatabase db, String id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, pid);
        values.put(COLUMN_USER_ID, uid);
        values.put(COLUMN_PRODUCT_QUANTITY, quantitiy);

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
                COLUMN_PRODUCT_ID,
                COLUMN_USER_ID,
                COLUMN_PRODUCT_QUANTITY,
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_CART,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }
}
