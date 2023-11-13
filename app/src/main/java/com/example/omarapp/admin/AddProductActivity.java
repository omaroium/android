package com.example.omarapp.admin;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import static com.example.omarapp.database.TablesString.ProductTable.TIEOFTOUR;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etname,etPlace,etprice,ettools,etHourOfStart,etMinuteOfStart,etTimeofTour,etMaxVisit;
    ImageButton imageButton;
    CalendarView cvdate;
    Button btadd,btupdate,btdelete;
    place p;
    byte[] image;
    boolean SelectedNewImage;
    String selectedId;
    Uri selectedImageUri;
    DBHelper dbHelper;

    Date date = new Date(0);
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
        SelectedNewImage =false;
        btupdate = findViewById(R.id.btUpdate);
        btupdate.setOnClickListener(this);
        btdelete = findViewById(R.id.btDelete);
        btdelete.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    
        Intent i = getIntent();
        if(i.getStringExtra("Selected_Id")==null) {
            btdelete.setVisibility(View.GONE);
            btupdate.setVisibility(View.GONE);
        }
        else {
            btadd.setVisibility(View.GONE);
            selectedId = i.getStringExtra("Selected_Id");
            setProduct();
        }

    }



    private void setProduct() {

        dbHelper.OpenReadAble();
        p=new place();
        Cursor c = p.SelectById(dbHelper.getDb(),selectedId);
        if(c!=null){

            c.moveToFirst();
            etname.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PLACE_NAME)));
            etPlace.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PLACE_DESCRIPTION)));
            etprice.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRICE)));
            etMaxVisit.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_MAXVISITS)));
            etTimeofTour.setText(c.getString(c.getColumnIndexOrThrow(TIEOFTOUR)));
            cvdate.setDate(c.getLong(c.getColumnIndexOrThrow(COLUMN_DATE)));
            etHourOfStart.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_HOUROFSTART)));
            ettools.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_TOOLS)));

            image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PLACE_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageButton.setImageBitmap(bm);
        }
        dbHelper.Close();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addButton){

            dbHelper.OpenWriteAble();
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
                Intent i = new Intent(this,ShowPlace.class);
                startActivity(i);
            }
        }
        if(view.getId()==R.id.btUpdate){
            Log.d("hello", "hi");
            p.setPid(Integer.parseInt(selectedId));
            p.setName(etname.getText().toString());
            Log.d("hello", etname.getText().toString());
            p.setPlace(etPlace.getText().toString());
            p.setPrice(Double.parseDouble(etprice.getText().toString()));
            p.setDate(new Date(cvdate.getDate()));
            Log.d("hello", etHourOfStart.getText().toString());
            Log.d("hello", etMinuteOfStart.getText().toString());

            p.setHourOfStart(new Time(Integer.parseInt(etHourOfStart.getText().toString()),Integer.parseInt(etMinuteOfStart.getText().toString()),0));
            p.setTools(ettools.getText().toString());
            p.setTimeOfTour(Double.parseDouble(etTimeofTour.getText().toString()));
            p.setMaxVisit(Integer.parseInt(etMaxVisit.getText().toString()));
            p.setImageByte(image);

            if(SelectedNewImage) {
                Log.d("kdsl","psadholih");
                p.setImageByte(imageViewToByte());
            }
            dbHelper.OpenWriteAble();
            p.Update(dbHelper.getDb(),selectedId);
            dbHelper.Close();
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AddProductActivity.this,ShowPlace.class);
            startActivity(i);
        }
        if(view.getId()==R.id.btDelete){
            dbHelper.OpenWriteAble();
            p.Delete(dbHelper.getDb(),selectedId);
            dbHelper.Close();
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ShowPlace.class);
            startActivity(i);
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
            SelectedNewImage =true;
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        date = new Date(year,month,day);
    }
}