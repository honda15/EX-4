package com.example.ex_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    private ListView ListViewfood;
    private ImageView Image_city;
    private TextView itemfood;
    private String cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        actbar();
        initview();
        list_view();
        list_listener();
    }

    private void list_listener() {
        ListViewfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fooddata = parent.getItemAtPosition(position).toString();
                Toast.makeText(DisplayActivity.this,
                        "Select: " + fooddata,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void list_view() {
        itemfood = findViewById(R.id.txt_fooditem);
        ListViewfood = findViewById(R.id.display_listView_fooddata);
        ArrayAdapter<String> adapterfood = null; // 声明在 switch 外部

        switch (cityname) {
            case "新北市":
                adapterfood = new ArrayAdapter<>(
                        DisplayActivity.this,
                        R.layout.simple_expandable_list_item_1,
                        getResources().getStringArray(R.array.newtaipei_food)
                );
                break;
            case "台北市":
                adapterfood = new ArrayAdapter<>(
                        DisplayActivity.this,
                        R.layout.simple_expandable_list_item_1,
                        getResources().getStringArray(R.array.taipei_food)
                );
                break;
            case "桃園市":
                adapterfood = new ArrayAdapter<>(
                        DisplayActivity.this,
                        R.layout.simple_expandable_list_item_1,
                        getResources().getStringArray(R.array.taoyuan_food)
                );
                break;
        }

        ListViewfood.setAdapter(adapterfood); // 放在 switch 外部
    }


    private void initview() {
        Image_city = findViewById(R.id.display_imgeView_city);
        Intent intent = getIntent();
        cityname = intent.getStringExtra("cityname");
        Log.v("lee",cityname);
        int picId = intent.getIntExtra("citypicture",R.drawable.newtaipei_1);
        setTitle(cityname);
        Image_city.setImageResource(picId);
    }

    private void actbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}