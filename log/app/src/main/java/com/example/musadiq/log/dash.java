package com.example.musadiq.log;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class dash extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button appointment;
    private Button gall;
    private Button abt;
    private Button notification;
    private Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);


        Button abt = (Button) findViewById(R.id.b1);
        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dash.this, about.class));
            }
        });

        Button notification = (Button) findViewById(R.id.b2);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dash.this, MainActivity1.class));
            }
        });

        Button gall = (Button) findViewById(R.id.b3);
        gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dash.this, MainActivity.class));
            }
        });

        Button appointment = (Button) findViewById(R.id.b4);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dash.this, Youtube.class));
            }
        });

        Button log=(Button)findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dash.this, first.class));
                kill_activity();

            }
        });

        Toast.makeText(dash.this, "Welcome To Glaucoma", Toast.LENGTH_LONG).show();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    void kill_activity()
    {
          dash.this.finish();
        Toast.makeText(dash.this, "Logout Sucessfull", Toast.LENGTH_LONG).show();
    }
}