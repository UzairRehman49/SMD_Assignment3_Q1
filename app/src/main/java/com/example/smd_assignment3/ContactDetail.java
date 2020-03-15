package com.example.smd_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
String name;
        setContentView(R.layout.activity_contact_detail);
        name = getIntent().getStringExtra("arrlist");
      // int image =  getIntent().getIntExtra("Imagelist");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
        TextView contName = findViewById(R.id.contact_name);
        TextView Emaill = findViewById(R.id.Email);
        TextView contnum = findViewById(R.id.mobile_num);
        CircleImageView mImage=findViewById(R.id.circle);
        contName.setText(name);
       // mImage.setImageDrawable(image);
        Emaill.setText(name+"@sample.com");
        contnum.setText("03319182340");
    }
}
