package com.example.musadiq.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String[] fruitNames = {"apracloni","betaxolol","bimatoprost","brimoni","brinzolamide","dorzolamaide","latanaprost","levobunolol","pilocrapine","prostaglandin","timolol","travaprost","unoprost"};
    int[] fruitImages = {R.drawable.apracloni,R.drawable.betaxolol,R.drawable.bimatoprost,R.drawable.brimoni,R.drawable.brinzolamide,R.drawable.dorzolamide,R.drawable.latanaprost,R.drawable.levobunolol,R.drawable.pilocrapine,R.drawable.prostaglandin,R.drawable.timolol,R.drawable.travaprost,R.drawable.unoprost};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView= findViewById(R.id.gridview);
        CustomAdapter customAdapter=new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),activity_grid_item.class);
                intent.putExtra("name",fruitNames[i]);
                intent.putExtra("image",fruitImages[i]);
                startActivity(intent);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return fruitImages.length ;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1=getLayoutInflater().inflate(R.layout.activity_row_data,null);
            TextView name=view1.findViewById(R.id.fruits);
            ImageView image=view1.findViewById(R.id.images);

            name.setText(fruitNames[i]);
            image.setImageResource(fruitImages[i]);
            return view1;
        }
    }

}
