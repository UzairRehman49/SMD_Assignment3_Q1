package com.example.smd_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context=this;
        setContentView(R.layout.activity_contact_detail);
        Gson gson = new Gson();
        ContactEntity contact =  gson.fromJson(getIntent().getStringExtra("myjson"), ContactEntity.class);
        Toast.makeText(this, contact.firstName, Toast.LENGTH_LONG).show();
        TextView contName = findViewById(R.id.contact_name);
        TextView Emaill = findViewById(R.id.emailID);
        TextView contnum = findViewById(R.id.mobile_num);
        CircleImageView mImage=findViewById(R.id.circle);
        contName.setText(contact.firstName);
        Glide.with(context)
                .asBitmap()
                .load(contact.image)
                .into(mImage);
        CircleImageView mainImage=findViewById(R.id.circleImageView);
        Glide.with(context)
                .asBitmap()
                .load(contact.image)
                .into(mainImage);
        Emaill.setText(contact.Email);
        contnum.setText("03319182340");
    }
}
