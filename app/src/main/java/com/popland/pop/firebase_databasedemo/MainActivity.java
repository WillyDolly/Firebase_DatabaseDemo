package com.popland.pop.firebase_databasedemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private DatabaseReference mData;
    TextView tvCheck;
    Button btnAndroid, btnPhp;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvCheck = (TextView)findViewById(R.id.TVcheck);
        btnAndroid = (Button)findViewById(R.id.BTNandroid);
        btnPhp = (Button)findViewById(R.id.BTNphp);
        iv = (ImageView)findViewById(R.id.IV);
        //write to database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference();
//        myRef.setValue("Hello World");

        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("Mon Hoc").setValue("math");

        DoiTuong object1 = new DoiTuong("nguyen trai","nguyentrai@gmail.com");
        mData.child("object1").setValue(object1);

        Map<String,String> mapXe = new HashMap<String,String>();
        mapXe.put("ten","subway");
        mData.child("Vehicle").setValue(mapXe);

        DoiTuong object2 = new DoiTuong("nguyen tat thanh","tatthanh@icloud.com");
        mData.child("object1").push().setValue(object2);

        mData.child("Mon Hoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvCheck.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("Mon Hoc").setValue("android");
            }
        });

        btnPhp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("Mon Hoc").setValue("php", new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Toast.makeText(MainActivity.this, "data transfer succeeded", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "data transfer failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        mData.child("hinhanh").setValue("https://firebasestorage.googleapis.com/v0/b/mydemofirrebase.appspot.com/o/abcnews.png?alt=media&token=891e7b4a-8e9c-4d4e-ae99-1ff078ea1f10", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){
                    Toast.makeText(MainActivity.this,"thanhhcong",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"thatbai",Toast.LENGTH_LONG).show();
                }
            }
        });

        mData.child("hinhanh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Picasso.with(MainActivity.this).load(dataSnapshot.getValue().toString()).into(iv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
