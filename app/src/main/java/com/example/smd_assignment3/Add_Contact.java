package com.example.smd_assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.allyants.notifyme.NotifyMe;



import com.esafirm.imagepicker.model.Image;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Add_Contact extends AppCompatActivity  {

    public static final int PICK_IMAGE = 1;
    final ContactEntity contact=new ContactEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);
        final ContactNum Numbers= new ContactNum();
        final LinearLayout layout=findViewById(R.id.linearlayout);
        final   EditText first=findViewById(R.id.firstName);
        final   EditText last=findViewById(R.id.lastName);
        final   EditText phone=findViewById(R.id.contactNum);
        final   EditText email=findViewById(R.id.emailID);

        final int count=1;

         final ArrayList<EditText>editTextList = new ArrayList<EditText>();
        editTextList.add(phone);
        Button Image= findViewById( R.id.button2);
        final Button save = findViewById(R.id.Save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.getText().toString() == null || last.getText().toString() == null || email.getText().toString() == null || phone.getText().toString() == null) {
                    Toast.makeText(Add_Contact.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    contact.firstName = first.getText().toString();
                    contact.lastName = last.getText().toString();
                    contact.Email = email.getText().toString();
                    contact.uid = MainActivity.db.userDao().insertAll(contact);
                    for (int i = 0; i < editTextList.size(); i++) {
                        if (i == 0) {
                            Numbers.Number = phone.getText().toString();
                            Numbers.numId = contact.uid;
                            MainActivity.db.NumDao().insertAll(Numbers);

                        } else {
                            Toast.makeText(Add_Contact.this, "Hi,You added a list", Toast.LENGTH_LONG).show();
                            Numbers.Number = editTextList.get(i).getText().toString();
                            Numbers.numId = contact.uid;
                            MainActivity.db.NumDao().insertAll(Numbers);


                        }

                    }
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Add_Contact.this);
                    mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
                    mBuilder.setContentTitle("Contact Added");
                    mBuilder.setContentText("You Contact: " + contact.firstName + " has been added");
                    NotificationManager mNotifyMgr =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationChannel notificationChannel = new NotificationChannel(String.valueOf(001), "NOTIFICATION_CHANNEL_NAME", importance);

                        mBuilder.setChannelId(String.valueOf(001));
                        mNotifyMgr.createNotificationChannel(notificationChannel);
                    }
                    mNotifyMgr.notify(001, mBuilder.build());
                    Intent i = new Intent(Add_Contact.this, ContactList.class);
                    contact.uid = 0;
                    startActivity(i);


                }
            }
        });

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        FloatingActionButton contactadd= findViewById(R.id.contactadd);
        contactadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams add= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                EditText editText = new EditText(Add_Contact.this);
                editText.setHint("Enter phone Number");
                editTextList.add(editText);
                layout.addView(editText,add);


                Toast.makeText(getApplicationContext(),"I am here" ,Toast.LENGTH_LONG).show();

            }
        });
    }
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                if(inputStream!=null)
                {
                    Toast.makeText(Add_Contact.this,"Image Picked",Toast.LENGTH_LONG).show();
                    BufferedInputStream bis = new BufferedInputStream(inputStream, 8192);
                   Bitmap bm = BitmapFactory.decodeStream(bis);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG,80,stream);
                    contact.image = stream.toByteArray();


                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }
    public void sendNotification() {

        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        // Gets an instance of the NotificationManager service//
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(001, mBuilder.build());
    }

}
