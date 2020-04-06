package com.example.smd_assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String>mImageNames=new ArrayList<>();
    private int [] mImages =  new int[]{R.drawable.uzi, R.drawable.meghan};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<ContactEntity> contactEntities = null;
        setContentView(R.layout.activity_contact_list);
        FloatingActionButton AddContact= findViewById(R.id.addContact);
        recyclerView = findViewById(R.id.recyclerview);
       contactEntities = initdata(contactEntities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(contactEntities,this);
        recyclerView.setAdapter(mAdapter);
        AddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(ContactList.this,Add_Contact.class);
                startActivity(i);
            }
        });

    }
    public List<ContactEntity> initdata(List<ContactEntity>list)
    {
        list = MainActivity.db.userDao().getAll();
        return list;
    }
}
