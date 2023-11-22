package com.example.omarapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omarapp.classes.ProductAdapter;
import com.example.omarapp.classes.place;
import com.example.omarapp.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_DATE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_HOUROFSTART;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_MAXVISITS;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_DESCRIPTION;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_IMAGE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PLACE_NAME;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_PRICE;
import static com.example.omarapp.database.TablesString.ProductTable.COLUMN_TOOLS;
import static com.example.omarapp.database.TablesString.ProductTable.TIEOFTOUR;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {



    TextView name;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    List<place> productList;
    RecyclerView recyclerView;
    ProductAdapter mAdapter;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        dbHelper = new DBHelper(inflater.getContext());
        dbHelper = dbHelper.OpenReadAble();
        place p = new place(),p2=new place();
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while(!c.isAfterLast()){
            p2 = new place(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)),
          c.getString(c.getColumnIndexOrThrow(COLUMN_PLACE_NAME)),
            c.getDouble(c.getColumnIndexOrThrow(TIEOFTOUR)),
            c.getString(c.getColumnIndexOrThrow(COLUMN_DATE)),
             c.getString(c.getColumnIndexOrThrow(COLUMN_HOUROFSTART)),
            c.getInt(c.getColumnIndexOrThrow(COLUMN_MAXVISITS)),
            c.getString(c.getColumnIndexOrThrow(COLUMN_PLACE_DESCRIPTION)),
           c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRICE)),
           c.getString(c.getColumnIndexOrThrow(COLUMN_TOOLS)),
          c.getBlob(c.getColumnIndexOrThrow(COLUMN_PLACE_IMAGE)));
            productList.add(p2);
            c.moveToNext();
        }
        // adapter
        mAdapter = new ProductAdapter(inflater.getContext(), productList);
        recyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return  view;
    }
}