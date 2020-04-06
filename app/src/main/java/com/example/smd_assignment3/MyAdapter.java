package com.example.smd_assignment3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<ContactEntity>contacts = null;
    private Context context;

    public MyAdapter(List<ContactEntity> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CircleImageView image;
        public TextView textView;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView ) {
            super(itemView);
            textView = itemView.findViewById(R.id.textrow);
            image = itemView.findViewById(R.id.image);
            relativeLayout=itemView.findViewById(R.id.relative_layout);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
       View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contact, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

//
        holder.textView.setText(contacts.get(position).firstName);
      //  Bitmap bitmap = BitmapFactory.decodeByteArray(contacts.get(position).image, 0, contacts.get(position).image.length);
        Glide.with(context)
                .asBitmap()
                .load(contacts.get(position).image)
                .into(holder.image);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (context,ContactDetail.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(contacts.get(position));
                i.putExtra("myjson", myJson);
              //  Toast.makeText(context,"Recycler View Pressed "+mImageNames.get(position),Toast.LENGTH_LONG).show();
             context.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
