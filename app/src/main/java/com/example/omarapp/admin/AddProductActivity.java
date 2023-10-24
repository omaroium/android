package com.example.omarapp.admin;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.omarapp.classes.place;
import com.example.omarapp.database.DBHelper;
import com.example.omarapp.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etname,etPlace,etprice,ettools,etHourOfStart,etMinuteOfStart,etTimeofTour,etMaxVisit;
    ImageButton imageButton;
    CalendarView cvdate;
    Button btadd;
    place p;
    Uri selectedImageUri;
    DBHelper dbHelper;

    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etname = findViewById(R.id.etProdName);
        etPlace = findViewById(R.id.etPlace);
        cvdate = findViewById(R.id.cvDate);
        cvdate.setOnDateChangeListener(this);
        etHourOfStart = findViewById(R.id.etHour);
        etMinuteOfStart = findViewById(R.id.etMinute);
        etprice = findViewById(R.id.etPrice);
        ettools = findViewById(R.id.etTools);
        etTimeofTour=findViewById(R.id.TimeofTour);
        imageButton = findViewById(R.id.imageButton);
        etMaxVisit=findViewById(R.id.MaxVisit);
        btadd = findViewById(R.id.addButton);
        btadd.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        dbHelper.OpenWriteAble();

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addButton){
            dbHelper = new DBHelper(this);
            Time time=new Time(Integer.parseInt(etHourOfStart.getText().toString()),
                    Integer.parseInt(etMinuteOfStart.getText().toString()),0);
            byte[] data  = imageViewToByte();
            p=new place(etname.getText().toString(),
                    Double.parseDouble(etTimeofTour.getText().toString()),
                    date,time,
                    Integer.parseInt(etMaxVisit.getText().toString()),
                    etPlace.getText().toString(),
                    Double.parseDouble(etprice.getText().toString()),
                    ettools.getText().toString(),data);
            dbHelper.OpenWriteAble();
            if(p.Add(dbHelper.getDb())>-1){
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                dbHelper.Close();
            }

        }
        if(view.getId()==R.id.imageButton){
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        }
    }
    public byte[] imageViewToByte() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() ,selectedImageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            selectedImageUri = data.getData();
            imageButton.setImageURI(selectedImageUri);
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        date = new Date(year,month,day);
    }
}