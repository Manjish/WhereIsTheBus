package com.finalproject.whereisthebus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.droidsonroids.gif.GifImageView;

public class ETA extends AppCompatActivity {
private TextView txtETA;
DatabaseReference reference;
GifImageView imgStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et);

        txtETA = findViewById(R.id.txtETA);
        reference = FirebaseDatabase.getInstance().getReference();
        imgStop = findViewById(R.id.imgStop);
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float eta = Float.parseFloat(dataSnapshot.child("time").getValue().toString());
                int bustStop = Integer.parseInt(dataSnapshot.child("busStop").getValue().toString());
                int towards = Integer.parseInt(dataSnapshot.child("towards").getValue().toString());
                if (bustStop==0){
                    txtETA.setText("Bus will arrive at bus stop no."+towards+" in "+eta+" s");
                    imgStop.setImageResource(R.drawable.busmoving);
                }
                else {
                    txtETA.setText("The bus is currently at bus stop no."+bustStop);

                    imgStop.setImageResource(R.drawable.busstop);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
}
