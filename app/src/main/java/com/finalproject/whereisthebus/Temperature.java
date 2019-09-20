package com.finalproject.whereisthebus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Temperature extends AppCompatActivity {
    private TextView txtTemp, txtStatus;
    private ImageView imgDisplay;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        txtTemp = findViewById(R.id.txtTemp);
        txtStatus = findViewById(R.id.txtStatus);
        imgDisplay = findViewById(R.id.imgDisplay);


        reference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double temp = Double.parseDouble(dataSnapshot.child("temperature").getValue().toString());
                showUser(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUser(double temp) {
        if (temp == 0.0) {
            txtTemp.setTextColor(getResources().getColor(R.color.noColor));
            txtStatus.setTextColor(getResources().getColor(R.color.noColor));
            txtTemp.setText("Couldn't");
            txtStatus.setText("Get Data");
            imgDisplay.setImageResource(R.drawable.oops);
        }
        else if(temp>0.0 && temp<20.00){
            txtTemp.setTextColor(getResources().getColor(R.color.coldColor));
            txtStatus.setTextColor(getResources().getColor(R.color.coldColor));
            txtTemp.setText(""+temp+"\u2103");
            txtStatus.setText("It's Cold");
            imgDisplay.setImageResource(R.drawable.itscold);
        }
        else if(temp>=20.0 && temp<30.00){
            txtTemp.setTextColor(getResources().getColor(R.color.coolColor));
            txtStatus.setTextColor(getResources().getColor(R.color.coolColor));
            txtTemp.setText(""+temp+"\u2103");
            txtStatus.setText("It's Cool");
            imgDisplay.setImageResource(R.drawable.itscool);
        }
        else {
            txtTemp.setTextColor(getResources().getColor(R.color.hotColor));
            txtStatus.setTextColor(getResources().getColor(R.color.hotColor));
            txtTemp.setText(""+temp+"\u2103");
            txtStatus.setText("It's Hot");
            imgDisplay.setImageResource(R.drawable.itshot);
            }
        }
    }
