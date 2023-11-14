package com.example.omarapp.admin;



import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.omarapp.classes.place;
import com.example.omarapp.database.DBHelper;
import com.example.omarapp.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;



import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_DATE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_HOUROFSTART;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_MAXVISITS;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_DESCRIPTION;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_IMAGE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_NAME;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PRICE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_TOOLS;
import static com.example.omarapp.database.QueryString.SQL_CREATE_PRODUCT;
import static com.example.omarapp.database.TablesString.ProductTable.TIEOFTOUR;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etname,etPlace,etprice,ettools,etHourOfStart,etMinuteOfStart,etTimeofTour,etMaxVisit;
    ImageButton imageButton;
    Button btadd,btupdate,btdelete,btshowcalnder,btshowtime;
    place p;
    byte[] image;
    boolean SelectedNewImage;
    String selectedId;
    Uri selectedImageUri;
    DBHelper dbHelper;
    TextView tvdate,tvtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etname = findViewById(R.id.etProdName);
        etPlace = findViewById(R.id.etPlace);
        btshowcalnder = findViewById(R.id.btShowCalender);
        btshowtime = findViewById(R.id.btShowTime);
        btshowtime.setOnClickListener(this);
        btshowcalnder.setOnClickListener(this);
        tvdate = findViewById(R.id.tvDate);
        tvtime = findViewById(R.id.tvShowTime);
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
            tvdate.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_DATE)));
            tvtime.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_HOUROFSTART)));
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
            byte[] data  = imageViewToByte();
            p=new place(etname.getText().toString(),
                    Double.parseDouble(etTimeofTour.getText().toString()),
                    tvdate.getText().toString(),tvtime.getText().toString(),
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

            p.setPid(Integer.parseInt(selectedId));
            p.setName(etname.getText().toString());
            p.setPlace(etPlace.getText().toString());
            p.setPrice(Double.parseDouble(etprice.getText().toString()));
            p.setDate(tvdate.getText().toString());
            p.setHourOfStart(tvtime.getText().toString());
            p.setTools(ettools.getText().toString());
            
            p.setTimeOfTour(Double.parseDouble(etTimeofTour.getText().toString()));
            p.setMaxVisit(Integer.parseInt(etMaxVisit.getText().toString()));
            p.setImageByte(image);

            if(SelectedNewImage) {
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
        if(view.getId()==R.id.btShowCalender){
            openDatePicker();
        }
        if(view.getId()==R.id.btShowTime){
             openTimePicker();
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

    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                tvdate.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 11, 14);

        datePickerDialog.show();
    }


    private void openTimePicker(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {


                //Showing the picked value in the textView
                tvtime.setText(String.valueOf(hour)+ ":"+String.valueOf(minute));

            }
        }, 15, 30, false);

        timePickerDialog.show();
    }
}