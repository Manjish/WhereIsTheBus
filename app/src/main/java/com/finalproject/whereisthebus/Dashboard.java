package com.finalproject.whereisthebus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard extends AppCompatActivity {
CardView tempCard,cardBusStatus,cardETA,cardSpeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tempCard = findViewById(R.id.cardTemp);
        cardBusStatus = findViewById(R.id.cardBusStatus);
        cardETA = findViewById(R.id.cardEta);
        cardSpeed = findViewById(R.id.cardSpeed);

        tempCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Temperature.class);
                startActivity(intent);
            }
        });

        cardBusStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,BusStatus.class);
                startActivity(intent);
            }
        });

        cardETA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,ETA.class);
                startActivity(intent);
            }
        });

        cardSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Speed.class);
                startActivity(intent);
            }
        });
    }
}
