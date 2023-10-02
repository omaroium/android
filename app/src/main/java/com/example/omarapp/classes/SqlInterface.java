package com.example.omarapp.classes;

        import android.database.Cursor;

public interface SqlInterface {

     boolean Add();
     boolean Delete();
     boolean Update();
     Cursor Select();
}
