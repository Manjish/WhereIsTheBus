package com.finalproject.whereisthebus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.droidsonroids.gif.GifImageView;

public class Speed extends AppCompatActivity {
    private TextView txtSpeed;
    DatabaseReference reference;
    GifImageView imgSpeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        txtSpeed = findViewById(R.id.txtSpeed);
        imgSpeed = findViewById(R.id.imgSpeed);

        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float speed = Float.parseFloat(dataSnapshot.child("speed").getValue().toString());
                speed = speed*30;
                showUser(speed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
    private void showUser(Float speed) {
        if(speed<60){
            txtSpeed.setTextColor(getResources().getColor(R.color.coolColor));
            txtSpeed.setText(speed+" km/hr");
            imgSpeed.setImageResource(R.drawable.normalspeed);
        }
        else {
            txtSpeed.setTextColor(getResources().getColor(R.color.hotColor));
            txtSpeed.setText(speed+" km/hr");
            imgSpeed.setImageResource(R.drawable.overspeed);
        }
    }
}

