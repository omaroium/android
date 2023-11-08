package com.example.omarapp.database;
import com.example.omarapp.database.TablesString.*;
public class QueryString {


    //region Create Tables
    public static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE " + ProductTable.TABLE_PLACE + " (" +
                    ProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProductTable.COLUMN_PLACE_NAME + " TEXT," +
                    ProductTable.COLUMN_PLACE_DESCRIPTION + " TEXT," +
                    ProductTable.COLUMN_MAXVISITS + " INTEGER," +
                    ProductTable.COLUMN_PLACE_IMAGE + " BLOB,"+
                    ProductTable.COLUMN_CURRENT_VISITS+" INTEGER," +
                    ProductTable.TIEOFTOUR+" STRING,"
                    +ProductTable.COLUMN_DATE+" DATE,"+
                    ProductTable.COLUMN_HOUROFSTART+" STRING,"+
                    ProductTable.COLUMN_PRICE+" DOUBLE,"+
                    ProductTable.COLUMN_TOOLS+" STRING,"+
                    ProductTable.COLUMN_VISITS+" INTEGER);";

    public static final String SQL_CREATE_CART =
            "CREATE TABLE " + CartTable.TABLE_CART + " (" +
                    CartTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CartTable.COLUMN_PRODUCT_ID + " TEXT," +
                    CartTable.COLUMN_USER_ID + " TEXT);";

    public static final String SQL_CREATE_SALE =
            "CREATE TABLE " + SaleTable.TABLE_SALE + " (" +
                    SaleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SaleTable.COLUMN_SALE_PROD_ID + " INTEGER," +
                    SaleTable.COLUMN_SALE_USER_ID + " TEXT,"+
                    SaleTable.COLUMN_SALE_PRICE + " DOUBLE,"+
                    SaleTable.COLUMN_BUY_PRICE + " DOUBLE);";

    //endregions

    //region Delete Tables

    public static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductTable.TABLE_PLACE;

    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + CartTable.TABLE_CART;

    public static final String SQL_DELETE_SALE =
            "DROP TABLE IF EXISTS " + SaleTable.TABLE_SALE;

    //endregion
}
