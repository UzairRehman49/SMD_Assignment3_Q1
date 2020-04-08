package com.example.smd_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context=this;
        setContentView(R.layout.activity_contact_detail);
        Gson gson = new Gson();
        ContactEntity contact =  gson.fromJson(getIntent().getStringExtra("myjson"), ContactEntity.class);
//        Type numberList = new TypeToken<List<ContactNum>>() {}.getType();
//        List<ContactNum> Numbers= gson.fromJson("Numbers",numberList );
        List <ContactNum> Numbers = MainActivity.db.NumDao().getAll(contact.uid);
        Toast.makeText(this, contact.firstName, Toast.LENGTH_LONG).show();
        TextView contName = findViewById(R.id.contact_name);
        TextView Emaill = findViewById(R.id.emailID);
        LinearLayout layout = findViewById(R.id.linearlayout);
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
        for(int i=0;i<Numbers.size();i++)
        {
            if(i==0)
                contnum.setText(Numbers.get(i).Number);
            else
            {
                LinearLayout.LayoutParams add= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView editText = new TextView(ContactDetail.this);
                editText.setText(Numbers.get(i).Number);
                layout.addView(editText,add);
            }
        }
    }
}
