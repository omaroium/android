package com.example.omarapp.classes;

        import android.database.Cursor;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

public interface SqlInterface {

    long Add(SQLiteDatabase db);

    int Delete(SQLiteDatabase db, String id);

    int Update(SQLiteDatabase db, String id);
    Cursor Select(SQLiteDatabase db);
}
