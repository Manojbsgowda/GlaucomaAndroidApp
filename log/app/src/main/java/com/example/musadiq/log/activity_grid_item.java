package com.example.musadiq.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_grid_item extends AppCompatActivity {

    TextView name;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        name=findViewById(R.id.griddata);
        image=findViewById(R.id.imageView);
        Intent intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image",0));
    }
}
