package com.finalproject.whereisthebus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.droidsonroids.gif.GifImageView;

public class BusStatus extends AppCompatActivity {
    private TextView txtBusStatus;
    private GifImageView imgBusStatus;
    DatabaseReference reference;
    int towards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_status);

        txtBusStatus = findViewById(R.id.txtBusStatus);
        imgBusStatus = findViewById(R.id.imgStatus);

        reference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String status = dataSnapshot.child("status").getValue().toString();
                towards = Integer.parseInt(dataSnapshot.child("towards").getValue().toString());
                showUser(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUser(String status) {
        if(status.equals("true")){
            txtBusStatus.setTextColor(getResources().getColor(R.color.coolColor));
            txtBusStatus.setText("Bus is running fine");
            imgBusStatus.setImageResource(R.drawable.busmoving);
        }
        else {
            txtBusStatus.setTextColor(getResources().getColor(R.color.hotColor));
            txtBusStatus.setText("Crash was detected on route to bus stop no. "+towards);
            imgBusStatus.setImageResource(R.drawable.buscrash);
        }
    }

}
