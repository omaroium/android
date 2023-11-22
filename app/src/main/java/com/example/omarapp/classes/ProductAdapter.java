package com.example.omarapp.classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omarapp.ProductInfo;
import com.example.omarapp.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {
    List<place> productList;
    Context context;

    public ProductAdapter(Context context, List<place> productList) {
        this.context = context;
        this.productList = productList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.each_travel, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String nameofProduct = productList.get(position).getName();
        String descriptionofproduct = productList.get(position).getPlace();
        String price = String.valueOf(productList.get(position).getPrice());
        byte[] images = productList.get(position).getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);

        holder.tvNameOfProduct.setText(nameofProduct);
        holder.tvDescriptionOfProduct.setText(descriptionofproduct);
        holder.imageOfProduct.setImageBitmap(bm);

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvNameOfProduct, tvDescriptionOfProduct,eachprice;
        ImageView imageOfProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNameOfProduct = itemView.findViewById(R.id.tvNameOfProduct);
            tvDescriptionOfProduct = itemView.findViewById(R.id.tvDescriptionOfProduct);
            eachprice = itemView.findViewById(R.id.eachPriceTv);
            imageOfProduct = itemView.findViewById(R.id.eachplaceIv);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), ProductInfo.class);
            intent.putExtra("id", productList.get(getLayoutPosition()).getPid() + "");
            v.getContext().startActivity(intent);
        }
    }



    }
