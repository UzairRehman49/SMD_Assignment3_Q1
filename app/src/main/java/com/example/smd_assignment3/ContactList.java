package com.example.smd_assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String>mImageNames=new ArrayList<>();
    private int [] mImages =  new int[]{R.drawable.uzi, R.drawable.meghan};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        recyclerView = findViewById(R.id.recyclerview);
        initdata();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         mAdapter = new MyAdapter(mImages,mImageNames,this);
        recyclerView.setAdapter(mAdapter);
    }
    public void initdata()
    {
        mImageNames.add("Uzair");
        mImageNames.add("Meghan");


    }
}
