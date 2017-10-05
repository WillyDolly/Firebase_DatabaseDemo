package com.popland.pop.firebase_databasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RetrieveData extends AppCompatActivity {
    TextView tvMap, tvObject;
    Button btnGetmap, btnGetobject;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);
        tvMap = (TextView)findViewById(R.id.TVmap);
        tvObject = (TextView)findViewById(R.id.TVobject);
        btnGetmap = (Button)findViewById(R.id.BTNgetMap);
        btnGetobject= (Button)findViewById(R.id.BTNgetObject);
        mData = FirebaseDatabase.getInstance().getReference();

        Map<String,String> map = new HashMap<String,String>();
        map.put("water", "lionsea");
        //mData.child("animal").push().setValue(map);
        btnGetmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.child("animal").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {//triggered again when scanning new node
                        Map<String, String> map = new HashMap<String, String>();
                        map = (Map<String, String>) dataSnapshot.getValue();
                        tvMap.setText(map.get("water"));//setText to retrieve most recently added node
                                                        //append to retrieve all nodes
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        Object object = new Object("delegent",231);
        //mData.child("user").push().setValue(object);
        btnGetobject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.child("user").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {//dataSnapshot is returned data
                         Object ob = dataSnapshot.getValue(Object.class);
                        tvObject.append(ob.ten.toString()+"\n");
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}