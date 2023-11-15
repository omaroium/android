package com.example.omarapp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.omarapp.R;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import static com.example.omarapp.database.TablesString.ProductTable.*;

import com.example.omarapp.classes.ListAdapter;
import com.example.omarapp.classes.*;
import com.example.omarapp.database.DBHelper;

import com.example.omarapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class ShowPlace extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
        ListView productListview;
        Button addnew;
        String[] product_string;
        DBHelper db;
        place p;
        place[] product_info;
        place selected_product;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_place);
            productListview = findViewById(R.id.lvproduct);
            productListview.setOnItemClickListener(this);
            addnew = findViewById(R.id.btAddNewProd);
            addnew.setOnClickListener(this);
            db = new DBHelper(getApplicationContext());
            p = new place();
            getProductToArray();
            ListAdapter adapter = new ListAdapter(this, product_info);
            productListview.setAdapter(adapter);

        }

        public void getProductToArray() {
            db.OpenReadAble();
            Cursor c = p.Select(db.getDb());
            if (c.getCount() > 0) {
                product_string = new String[c.getCount()];
                product_info = new place[c.getCount()];
                int i = 0;
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    p.setPid(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)));
                    p.setName(c.getString(c.getColumnIndexOrThrow(COLUMN_PLACE_NAME)));
                    p.setPlace(c.getString(c.getColumnIndexOrThrow(COLUMN_PLACE_DESCRIPTION)));
                    p.setPrice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRICE)));
                    p.setMaxVisit(c.getInt(c.getColumnIndexOrThrow(COLUMN_MAXVISITS)));
                    p.setTimeOfTour(c.getDouble(c.getColumnIndexOrThrow(TIEOFTOUR)));
                    p.setDate(c.getString(c.getColumnIndexOrThrow(COLUMN_DATE)));
                    p.setHourOfStart(c.getString(c.getColumnIndexOrThrow(COLUMN_HOUROFSTART)));
                    p.setTools((c.getString(c.getColumnIndexOrThrow(COLUMN_TOOLS))));
                    p.setImageByte(c.getBlob(c.getColumnIndexOrThrow(COLUMN_PLACE_IMAGE)));

               /* if(cat.equals("PC"))
                    product_info[i]=new PC(p);
                else if(cat.equals("LabTop"))
                    product_info[i]=new LabTop(p);
                else if(cat.equals("Printer"))
                    product_info[i]=new Printer(p);
                else
                    product_info[i]=new Other(p);*/
                    product_info[i] = new place(p);
                    product_string[i++] = p.toString();
                    c.moveToNext();
                }
            }
            db.Close();
        }


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selected_product = product_info[i];
            Intent in = new Intent(this, AddProductActivity.class);
            in.putExtra("Selected_Id", selected_product.getPid() + "");
            startActivity(in);
        }

        @Override
        public void onClick(View view) {
            Intent in = new Intent(this, AddProductActivity.class);
            startActivity(in);
        }

        @Override
        public void onStop() {
            FirebaseAuth fauth = FirebaseAuth.getInstance();
            fauth.signOut();
            super.onStop();
        }
    }
